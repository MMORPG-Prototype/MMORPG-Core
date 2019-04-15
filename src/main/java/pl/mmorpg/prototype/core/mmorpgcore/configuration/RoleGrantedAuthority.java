package pl.mmorpg.prototype.core.mmorpgcore.configuration;

import org.springframework.security.core.GrantedAuthority;

public class RoleGrantedAuthority implements GrantedAuthority
{
	private final Role role;

	public RoleGrantedAuthority(Role role)
	{
		this.role = role;
	}

	@Override
	public String getAuthority()
	{
		return role.toString();
	}
}
