package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.UserShoppingEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface UserShoppingRepository extends JpaRepository<UserShoppingEntity, Long> {
    Optional<UserShoppingEntity> findByUseridAndIngred(String userid, String ingred);
    ArrayList<UserShoppingEntity> findByUserid(String userid);
}
