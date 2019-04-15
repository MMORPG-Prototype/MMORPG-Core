package pl.mmorpg.prototype.core.mmorpgcore.model.user.control;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mmorpg.prototype.core.mmorpgcore.model.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByUsername(String username);
}
