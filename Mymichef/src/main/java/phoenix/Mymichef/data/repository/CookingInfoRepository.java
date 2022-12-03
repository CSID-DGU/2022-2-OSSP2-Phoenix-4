package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.CookingInfoEntity;


public interface CookingInfoRepository extends JpaRepository<CookingInfoEntity, String> {
}
