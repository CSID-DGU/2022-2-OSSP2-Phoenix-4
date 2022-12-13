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
   public void changeDate(){
       String date = "2022-12-13";
       int a= 2;
       int idate = 0;
       String sDate;

       idate = Integer.parseInt(date.substring(8));
       idate += a;
       System.out.println("idate = " + idate);
       System.out.println("idate 변화값 = " + idate);

       sDate = String.valueOf(idate);
       System.out.println("sDate = " + sDate);

       StringBuffer replace = new StringBuffer();
       replace.append(date);
       System.out.println("초기 replace = " + replace);
       String changed = String.valueOf(replace.replace(8,10,sDate));
       System.out.println("changed = " + changed);

   }

//   @Test
//    public void test(){
//       String date = "2022-10-15";
//       for(int i = 0; i < 5; i++){
//
//           changeDate(date,i);
//           System.out.println("date = " + date);
//       }
//   }
}

