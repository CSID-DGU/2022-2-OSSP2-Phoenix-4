package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.CookingProcessEntity;

public interface CookingProcessRepository extends JpaRepository<CookingProcessEntity, String> {
}
