package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.CookingProcessEntity;

import java.util.ArrayList;

public interface CookingProcessRepository extends JpaRepository<CookingProcessEntity, String> {
    ArrayList<CookingProcessEntity> findByRecipeid(String recipeid);
}
