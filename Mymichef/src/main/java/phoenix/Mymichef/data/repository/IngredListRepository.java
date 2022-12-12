package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.IngredListEntity;

import java.util.ArrayList;

public interface IngredListRepository extends JpaRepository<IngredListEntity, String> {
    ArrayList<IngredListEntity> findByType(String type);
}
