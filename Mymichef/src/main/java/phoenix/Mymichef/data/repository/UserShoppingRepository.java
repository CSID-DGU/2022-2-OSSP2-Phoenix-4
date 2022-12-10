package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.UserShoppingEntity;

public interface UserShoppingRepository extends JpaRepository<UserShoppingEntity, Long> {
}
