package pl.mmorpg.prototype.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mmorpg.prototype.core.configuration.Credentials;
import pl.mmorpg.prototype.core.service.user.boundary.UserService;

@RestController
public class RegisterController
{
	private final UserService userService;

	@Autowired
	public RegisterController(UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping("/register")
	public void register(@RequestBody Credentials credentials) {
		userService.registerUser(credentials);
	}
}
