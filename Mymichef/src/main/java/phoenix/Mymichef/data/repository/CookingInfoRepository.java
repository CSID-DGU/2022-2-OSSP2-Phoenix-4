package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.CookingInfoEntity;

import java.util.List;


public interface CookingInfoRepository extends JpaRepository<CookingInfoEntity, String> {
    CookingInfoEntity findByRecipeid(String RECIPE_ID);

    List<CookingInfoEntity> findAllByNationnm(String nationNm);

    List<CookingInfoEntity> findAllByLevelnm(String levelNm);

}
