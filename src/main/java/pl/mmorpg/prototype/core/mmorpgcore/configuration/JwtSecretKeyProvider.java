package pl.mmorpg.prototype.core.mmorpgcore.configuration;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Key;

@Configuration
public class JwtSecretKeyProvider
{
	@Bean
	Key jwtSecretKey(@Value("classpath:.secret") Resource resourceFile) throws IOException
	{
		byte[] keyBytes = resourceFile.getInputStream().readAllBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
