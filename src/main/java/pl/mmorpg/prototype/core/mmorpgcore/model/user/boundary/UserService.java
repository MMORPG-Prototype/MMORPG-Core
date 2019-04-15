package pl.mmorpg.prototype.core.mmorpgcore.model.user.boundary;

import pl.mmorpg.prototype.core.mmorpgcore.configuration.Credentials;

public interface UserService
{
	void registerUser(Credentials credentials);
}
