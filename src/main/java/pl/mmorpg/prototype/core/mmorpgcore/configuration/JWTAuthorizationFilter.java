package pl.mmorpg.prototype.core.mmorpgcore.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
	private final Key secretKey;

	public JWTAuthorizationFilter(AuthenticationManager authManager, Key secretKey)
	{
		super(authManager);
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain) throws IOException, ServletException
	{
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
	{
		return getUserFromAuthorizationHeader(request)
				.map(u -> new UsernamePasswordAuthenticationToken(u, null, Collections.emptyList()))
				.orElse(null);
	}

	private Optional<String> getUserFromAuthorizationHeader(HttpServletRequest request)
	{
		return Optional.ofNullable(request.getHeader("Authorization"))
				.filter(header -> header.startsWith("Bearer "))
				.map(header -> header.replaceFirst("Bearer ", ""))
				.map(this::parseJWT)
				.map(Jwt::getBody)
				.map(Claims::getSubject);
	}

	private Jws<Claims> parseJWT(String jwt)
	{
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(jwt);
	}
}