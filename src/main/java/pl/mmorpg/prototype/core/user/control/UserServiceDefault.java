package pl.mmorpg.prototype.core.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mmorpg.prototype.core.configuration.Credentials;
import pl.mmorpg.prototype.core.user.boundary.UserService;
import pl.mmorpg.prototype.data.entities.User;
import pl.mmorpg.prototype.data.entities.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceDefault implements UserService
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceDefault(UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void registerUser(Credentials credentials)
	{
		User user = new User();
		user.setUsername(credentials.getUsername());
		user.setPassword(passwordEncoder.encode(credentials.getPassword()));
		userRepository.save(user);
	}

	@Override
	public Optional<User> findUser(String username)
	{
		return userRepository.findByUsername(username);
	}
}
