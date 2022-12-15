package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.entity.CookingInfoEntity;

import java.util.ArrayList;
import java.util.List;


public interface CookingInfoRepository extends JpaRepository<CookingInfoEntity, String> {
    CookingInfoEntity findByRecipeid(String RECIPE_ID);

    List<CookingInfoEntity> findByNationnm(String NATION_NM);

    List<CookingInfoEntity> findByLevelnm(String LEVEL_NM);

    CookingInfoEntity findByRecipenm(String recipenm);

    @Query(nativeQuery = true,
            value = "select RECIPE_ID as recipe, CALORIE as cnt from cookinginfo where RECIPE_ID In (:userrecipe)  group by RECIPE_ID order by CALORIE*1")
    List<IngredInterface> findCalorie(@Param("userrecipe") List<String> userrecipe);
}
