package pl.mmorpg.prototype.core.configuration;

import org.springframework.security.core.GrantedAuthority;
import pl.mmorpg.prototype.data.entities.UserRole;

public class RoleGrantedAuthority implements GrantedAuthority
{
	private final UserRole role;

	public RoleGrantedAuthority(UserRole role)
	{
		this.role = role;
	}

	@Override
	public String getAuthority()
	{
		return role.toString();
	}
}
