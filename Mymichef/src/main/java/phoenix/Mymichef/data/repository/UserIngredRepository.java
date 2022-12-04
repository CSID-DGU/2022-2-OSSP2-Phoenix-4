package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.UserIngredEntity;

public interface UserIngredRepository extends JpaRepository<UserIngredEntity, Long> {
    UserIngredEntity findByUseridAndIngredname(String userid, String ingredname);
}
