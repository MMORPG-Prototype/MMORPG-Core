package pl.mmorpg.prototype.core.user.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mmorpg.prototype.data.entities.User;

import java.security.Principal;
import java.util.Optional;

@RestController
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
}
