package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.IngredEntity;

public interface IngredRepository extends JpaRepository<IngredEntity, String> {
}
