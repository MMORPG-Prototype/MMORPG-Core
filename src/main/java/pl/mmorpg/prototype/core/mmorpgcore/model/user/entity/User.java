package pl.mmorpg.prototype.core.mmorpgcore.model.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.mmorpg.prototype.core.mmorpgcore.configuration.Role;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;
}