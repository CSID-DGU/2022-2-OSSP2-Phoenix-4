package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.entity.IngredEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IngredRepository extends JpaRepository<IngredEntity, String> {
    @Query(nativeQuery = true,
            value = "select RECIPE_ID as recipe, count(IRDNT_NM) as cnt from cookingingred where IRDNT_NM In (:useringred)  group by RECIPE_ID having count(IRDNT_NM) >= 1 order by count(IRDNT_NM) DESC")
    List<IngredInterface> findTest(@Param("useringred") ArrayList<String> useringred);

    ArrayList<IngredEntity> findByRecipeid(String recipeid);


}
