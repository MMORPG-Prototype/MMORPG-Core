package pl.mmorpg.prototype.core.mmorpgcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
	@GetMapping("/test")
	public String getSomething()
	{
		return "Hurray! It works!";
	}
}
