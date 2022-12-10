package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.entity.UserShoppingEntity;

public interface UserDietRepository extends JpaRepository<UserDietEntity, Long> {
}
