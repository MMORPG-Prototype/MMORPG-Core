package pl.mmorpg.prototype.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.mmorpg.prototype.data.entities.repositories")
@EntityScan(basePackages = "pl.mmorpg.prototype.data.entities")
public class MmorpgCoreApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MmorpgCoreApplication.class, args);
	}
}
