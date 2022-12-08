package phoenix.Mymichef;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import phoenix.Mymichef.data.repository.UserIngredRepository;
import phoenix.Mymichef.service.UserIngredientService;


@SpringBootTest
class MymichefApplicationTests {

    UserIngredientService userIngredientService;
    UserIngredRepository userIngredRepository;


    @Test
    void 식재료수정확인테스트() {
        String ingred = "감자";
        String ingredamount = "1";
        String userid = "robot";
        userIngredientService.ModifyIngred(userid, ingred, ingredamount);
        System.out.printf("%s", userIngredRepository.findByUseridAndIngredname(userid, ingredamount).getIngredamount());
    }

}