package pl.mmorpg.prototype.core.mmorpgcore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.mmorpg.prototype.core.mmorpgcore.model.user.control.UserRepository;
import pl.mmorpg.prototype.core.mmorpgcore.model.user.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

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
		Optional<User> user = userService.findByUsername(username);
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