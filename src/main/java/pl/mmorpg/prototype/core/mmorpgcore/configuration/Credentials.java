package pl.mmorpg.prototype.core.mmorpgcore.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Credentials
{
	private final String username;
	private final String password;

	@JsonCreator
	public Credentials(@JsonProperty("username") String username,
			@JsonProperty("password") String password)
	{
		this.username = username;
		this.password = password;
	}
}
