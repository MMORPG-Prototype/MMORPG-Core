package pl.mmorpg.prototype.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="pl.mmorpg.prototype.data.entities")
public class MmorpgCoreApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MmorpgCoreApplication.class, args);
	}
}
