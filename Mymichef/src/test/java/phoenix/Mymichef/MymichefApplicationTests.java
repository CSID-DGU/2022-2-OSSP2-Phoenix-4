package phoenix.Mymichef;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import phoenix.Mymichef.data.entity.CookingInfoEntity;
import phoenix.Mymichef.data.repository.CookingInfoRepository;
import phoenix.Mymichef.data.repository.UserIngredRepository;
import phoenix.Mymichef.service.UserIngredientService;

import java.util.List;
import java.util.Random;


@SpringBootTest
class MymichefApplicationTests {

    UserIngredientService userIngredientService;
    UserIngredRepository userIngredRepository;

    CookingInfoRepository cookingInfoRepository;


   @Test
   public String recommendNation(String nationName) throws Exception{
        nationName = "한식";
       List<CookingInfoEntity> recipe = cookingInfoRepository.findByNationnm(nationName);
       if(recipe.size() == 0){throw new Exception("먼일이고");}
       Random random = new Random();
       random.setSeed(System.currentTimeMillis());
       int a = 0;


       System.out.println("recipe사이즈 = " + recipe.size());
       while(true) {
           //젤 많은 종류가 537개라
           a = random.nextInt(538) % (recipe.size());

           if(recipe.get(a) != null){
               break;
           }
       }

       return recipe.get(a).getRECIPE_NM_KO();

   }

}