package pl.mmorpg.prototype.core.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	private final AuthenticationManager authenticationManager;
	private final Key secretKey;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Key secretKey)
	{
		this.authenticationManager = authenticationManager;
		this.secretKey = secretKey;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException
	{
		Credentials user = deserializeUser(req);
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList())
		);
	}

	private Credentials deserializeUser(HttpServletRequest req)
	{
		try
		{
			return new ObjectMapper()
					.readValue(req.getInputStream(), Credentials.class);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth)
	{
		String jsonWebToken = Jwts.builder()
				.setExpiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
				.setSubject(auth.getName())
				.signWith(secretKey)
				.compact();
		res.addHeader("Authorization", "Bearer " + jsonWebToken);
	}
}