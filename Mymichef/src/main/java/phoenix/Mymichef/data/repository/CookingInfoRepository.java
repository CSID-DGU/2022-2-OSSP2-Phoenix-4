package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.data.entity.CookingInfoEntity;

import java.util.List;


public interface CookingInfoRepository extends JpaRepository<CookingInfoEntity, String> {
    CookingInfoEntity findByRecipeid(String RECIPE_ID);

    List<CookingInfoEntity> findByNationnm(String NATION_NM);

    List<CookingInfoEntity> findByLevelnm(String LEVEL_NM);

    CookingInfoEntity findByRecipenm(String recipenm);

}
