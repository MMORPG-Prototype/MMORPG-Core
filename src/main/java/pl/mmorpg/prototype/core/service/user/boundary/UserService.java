package pl.mmorpg.prototype.core.service.user.boundary;

import pl.mmorpg.prototype.core.configuration.Credentials;

public interface UserService
{
	void registerUser(Credentials credentials);
}
