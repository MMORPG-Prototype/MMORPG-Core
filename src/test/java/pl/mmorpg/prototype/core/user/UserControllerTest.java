package pl.mmorpg.prototype.core.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mmorpg.prototype.core.configuration.Credentials;
import pl.mmorpg.prototype.core.user.boundary.UserController;
import pl.mmorpg.prototype.data.entities.User;
import pl.mmorpg.prototype.data.entities.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
class UserControllerTest
{
	@Autowired
	private UserController userController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void registerUserAndEncodePassword() {
		String username = "user";
		String password = "password123";

		userController.register(new Credentials(username, password));

		Optional<User> savedUser = userRepository.findByUsername(username);
		assertThat(savedUser).isPresent();
		assertThat(savedUser.get().getPassword()).matches(savedPassword -> passwordEncoder.matches(password, savedPassword));
	}
}
