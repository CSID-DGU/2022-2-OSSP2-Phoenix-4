package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.entity.UserShoppingEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public interface UserDietRepository extends JpaRepository<UserDietEntity, Long> {
    Optional<UserDietEntity> findByUseridAndDateAndTime(String userid, String date, String time);
    ArrayList<UserDietEntity> findByUserid(String userid);
}
