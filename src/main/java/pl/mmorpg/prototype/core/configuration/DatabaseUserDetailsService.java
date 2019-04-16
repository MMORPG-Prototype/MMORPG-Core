package pl.mmorpg.prototype.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.mmorpg.prototype.data.entities.User;
import pl.mmorpg.prototype.data.entities.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Configuration
@DatabaseAuthentication
public class DatabaseUserDetailsService implements UserDetailsService
{
	private final UserRepository userService;

	@Autowired
	public DatabaseUserDetailsService(UserRepository userService)
	{
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> user = Optional.ofNullable(userService.findByUsername(username));
		if (user.isEmpty())
			throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user.get());

		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), grantedAuthorities);
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(User user)
	{
		return Collections.singleton(new RoleGrantedAuthority(user.getRole()));
	}
}