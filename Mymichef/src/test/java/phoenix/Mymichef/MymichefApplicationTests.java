package phoenix.Mymichef;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import phoenix.Mymichef.data.entity.CookingInfoEntity;
import phoenix.Mymichef.data.repository.CookingInfoRepository;
import phoenix.Mymichef.data.repository.UserIngredRepository;
import phoenix.Mymichef.service.UserIngredientService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

   @Test
    public void countDate() throws ParseException {
        String date2 = "2022-12-14";
        String date1 = "2023-12-31";
        long count = 0;

       DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

       Date d1 = format.parse( date1 );
       Date d2 = format.parse( date2 );

       long Sec = (d1.getTime() - d2.getTime());
       long Days = Sec / (24*60*60*1000); // 일자수

       System.out.println(Days + "일 차이");
       count += Days;
       System.out.println("count = " + count);
   }

   @Test
    public String addOneDayCalendar()
            throws ParseException {
        String date = "2022-11-30";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dt = format.parse(date);
        cal.setTime(dt);

        cal.add(Calendar.DATE, 1);

        return format.format(cal.getTime());

    }
}

