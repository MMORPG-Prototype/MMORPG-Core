package pl.mmorpg.prototype.core.user.boundary;

import pl.mmorpg.prototype.core.configuration.Credentials;
import pl.mmorpg.prototype.data.entities.User;

import java.util.Optional;

public interface UserService
{
	void registerUser(Credentials credentials);

	Optional<User> findUser(String username);
}
