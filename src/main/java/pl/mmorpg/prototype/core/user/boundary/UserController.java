package pl.mmorpg.prototype.core.user.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mmorpg.prototype.core.configuration.Credentials;
import pl.mmorpg.prototype.data.entities.User;

@RestController
@RequestMapping("/users/")
public class UserController
{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping("/me")
	public User getUserInfo(Authentication authentication)
	{
		return userService.findUser(authentication.getCredentials().toString())
				.orElse(null);
	}

	@PostMapping("/register")
	public void register(@RequestBody Credentials credentials) {
		userService.registerUser(credentials);
	}
}
