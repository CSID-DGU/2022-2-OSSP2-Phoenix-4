package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.SourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.dto.UserDietDto;
import phoenix.Mymichef.data.entity.CookingInfoEntity;
import phoenix.Mymichef.data.entity.CookingProcessEntity;
import phoenix.Mymichef.data.entity.IngredEntity;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.repository.*;

import java.awt.*;
import java.awt.datatransfer.FlavorEvent;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserDietService {

    @Autowired
    private UserIngredRepository userIngredRepository;
    @Autowired
    private IngredRepository ingredRepository;
    @Autowired
    private CookingInfoRepository cookingInfoRepository;

    @Autowired
    private UserDietRepository userDietRepository;

    @Autowired
    private CookingProcessRepository cookingProcessRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 식단 추천 서비스 (default) 폐기
     */

//    public CookingInfoDTO recommendDiet(String userId) throws Exception {
//
//        ArrayList<String> userIngredName = new ArrayList<>();
//        ArrayList<UserIngredEntity> find = userIngredRepository.findByUserid(userId);
//        ArrayList<IngredEntity> allIngred = (ArrayList<IngredEntity>) ingredRepository.findAll();
//        ArrayList<Integer> compare = new ArrayList<>(Collections.nCopies(195453, 0));
//        ArrayList<Integer> recommand = new ArrayList<>();
//        CookingInfoEntity cookingInfoEntity = new CookingInfoEntity();
//        Random random = new Random();
//        random.setSeed(System.currentTimeMillis());
//        int recipeId;
//        String recipeIngred;
//        int cnt = 0;
//        int a = 0;
//        int maxx = 0;
//
//
//            //유저 식재료 List
//            for (int i = 0; i < find.size(); i++) {
//                userIngredName.add(find.get(i).getIngredname());
//            }
//
//
//            //유저 식재료랑 전체 식재료랑 비교해서 List 생성
//            // 구조는 id 위치에 겹치는 재료 수
//
//            for (int i = 0; i < userIngredName.size()-1; i++) {
//
//                for (int j = 0; j < allIngred.size(); j++) {
//                    recipeId = Integer.parseInt((allIngred.get(j).getRECIPE_ID()));
//                    recipeIngred = allIngred.get(j).getIRDNT_NM();
//
//                    if (userIngredName.get(i).equals(recipeIngred)) {
//                        cnt++;
//                        if (compare.get(recipeId) == null) {
//                            compare.add(recipeId, cnt);
//                        } else {
//                            cnt = compare.get(recipeId);
//                            cnt++;
//                            compare.remove(recipeId);
//                            compare.add(recipeId, cnt);
//                        }
//                        cnt = 0;
//                    }
//                }
//            }
//
//
//            //겹치는 재료의 최대 수 + 겹치는게 하나도 없을 때 그냥 랜덤으로 하나 보내줌
//            maxx = (int) Collections.max(compare);
//             System.out.println("겹치는 재료 최대 수임 maxx = " + maxx);
//            if(maxx == 0){
//                a = random.nextInt(500);
//                Optional<CookingInfoEntity> food =
//                        cookingInfoRepository.findById(recommand.get(a).toString());
//
//                if (food.isEmpty()) {
//                    throw new Exception("많이 잘못됐습니다.");
//                }else {
//                    cookingInfoEntity = food.get();
//                    System.out.println("꺼낼 레시피 이름 = " + cookingInfoEntity.getRECIPE_NM_KO());
//                }
//
//                return new CookingInfoDTO(cookingInfoEntity);
//            }
//
//
//            //쭉 돌며 추천해줄 레시피 아이디 저장
//            for (int i = 0; i < compare.size(); i++) {
//                if (compare.get(i).equals(maxx)) {
//                    recommand.add(i);
//                }
//            }
//
//
//            //추천해줄 리스트에서 랜덤으로 하나 뽑기
//            a = random.nextInt(100) % (recommand.size());
//
//            for(int i = 0; i < recommand.size(); i++){
//                System.out.println("추천할 아이디 목록 = " + recommand.get(i));
//            }
//            System.out.println("recommand = " + recommand.size());
//            System.out.println("최종 선정 id = " + a);
//
//            Optional<CookingInfoEntity> food =
//                    cookingInfoRepository.findById(recommand.get(a).toString());
//
//            if (food.isEmpty()) {
//                throw new Exception("해당 아이디에 해당하는 레시피가 없습니다.");
//            }else {
//                cookingInfoEntity = food.get();
//                System.out.println("꺼낼 레시피 이름 = " + cookingInfoEntity.getRECIPE_NM_KO());
//            }
//
//        return new CookingInfoDTO(cookingInfoEntity);
//    }

    /**
     * 식단 추천 서비스 (default)
     */

    public String recommendMenu(ArrayList<String> useringred) throws Exception {
        List<IngredInterface> MenuList = ingredRepository.findTest(useringred);
        String MAX = MenuList.get(0).getcnt();
        List<String> RealMenuList = new ArrayList<>();
        for(int i = 0 ; i < MenuList.size(); i++){
            if(MenuList.get(i).getcnt().equals(MAX))
                RealMenuList.add(MenuList.get(i).getrecipe());
        }

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int a = 0;
        String recipeId;

        if (RealMenuList.isEmpty()) {
            throw new Exception("해당하는 데이터가 없습니다.");
        }
        else {
            while (true) {
                //젤 많은 종류가 537개라
                a = random.nextInt(538) % (RealMenuList.size());

                if (RealMenuList.get(a) != null) {
                    break;
                }
            }
            recipeId = RealMenuList.get(a);
            return cookingInfoRepository.findByRecipeid(recipeId).getRecipenm();

        }
    }

    /**
     * 식단 추천 서비스 (calories)
     */

    public String recommendCalorie(ArrayList<String> useringred) throws Exception {
        List<IngredInterface> MenuList = ingredRepository.findTest(useringred);
        List<String> Menu = new ArrayList<>();
        for (int i = 0; i < MenuList.size(); i++) {
            Menu.add(MenuList.get(i).getrecipe());
        }

        double cal = userRepository.findByUserId(UserDTO.currentUserId()).getCal();
        List<IngredInterface> RecipeList = cookingInfoRepository.findCalorie(Menu);
        List<String> RealMenu = new ArrayList<>();

        for (int i = 0; i < RecipeList.size(); i++) {
            if (Float.valueOf(StringSplit(RecipeList.get(i).getcnt())) < cal) {
                RealMenu.add(RecipeList.get(i).getrecipe());
            }
        }
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int a = 0;

        if (RealMenu.isEmpty()) {
            throw new Exception("해당하는 데이터가 없습니다.");
        }
        else {
            while (true) {
                //젤 많은 종류가 537개라
                a = random.nextInt(538) % (RealMenu.size());
                if (RealMenu.get(a) != null) {
                    break;
                } else {

                }
            }
            return cookingInfoRepository.findByRecipeid(RealMenu.get(a)).getRecipenm();
        }
    }





    public String StringSplit(String s){
        return s.substring(0, s.length() - 4);
    }

    /**
     * 추천 식단 저장 서비스
     */

    public void saveRecommendInfo(UserDietDto userDietDto) throws Exception {
        UserDietEntity userDietEntity = userDietDto.toEntity();
        try {
            userDietRepository.save(userDietEntity);
        } catch (Exception e) {
            throw new Exception("저장 이상 발생(server)");
        }
    }

    /**
     * 식단 추천 (나라별)
     */
    public String recommendNation(String nationName) throws Exception {
        List<CookingInfoEntity> recipe = cookingInfoRepository.findByNationnm(nationName);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int a = 0;


        System.out.println("recipe사이즈 = " + recipe.size());
        while (true) {
            //젤 많은 종류가 537개라
            a = random.nextInt(538) % (recipe.size());

            if (recipe.get(a) != null) {
                break;
            }
        }

        return recipe.get(a).getRecipenm();

    }

    /**
     * 식단 추천 (난이도별)
     */
    public String recommendDifficulty(String difficulty) throws Exception {
        List<CookingInfoEntity> recipe = cookingInfoRepository.findByLevelnm(difficulty);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int a = 0;

        while (true) {
            //젤 많은 종류가 537개라
            a = random.nextInt(538) % (recipe.size());

            if (recipe.get(a) != null) {
                break;
            }
        }

        return recipe.get(a).getRecipenm();

    }

    /**
     * 현재 시간 가져오기
     */
    public LocalDate currentTime() {
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 포맷 적용
        String formatedNow = now.format(formatter);

        return now;
    }

    /**
     * 날짜 변환
     */
    public String addOneDayCalendar(String date) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dt = format.parse(date);
        cal.setTime(dt);

        cal.add(Calendar.DATE, 1);

        return format.format(cal.getTime());
    }

    /**
     *  날짜 계산
     */
    public int countDate(String date1, String date2) throws ParseException {

        int count = 0;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = format.parse( date1 );
        Date d2 = format.parse( date2 );

        long Sec = (d1.getTime() - d2.getTime());
        long Days = Sec / (24*60*60*1000); // 일자수

        count += Days;
        System.out.println(count + "일 차이");

        return count + 1;
    }

    /**
     * 중복 레시피 수정
     */

    public Optional<UserDietEntity> OverlapTime(String Userid, String date, String time) {
        return userDietRepository.findByUseridAndDateAndTime(Userid, date, time);
    }

    /**
     * 중복 시간 레시피 수정
     */

    public void ModifyRecommendInfo(Optional<UserDietEntity> user, String recipename) throws Exception {
        user.ifPresent(selectUser -> {
            selectUser.setRecipenm(recipename);
            userDietRepository.save(selectUser);
        });
    }

    /**
     * 식단 확인 서비스
     */

    public ArrayList<String> CheckRecipe(String userid) throws Exception {
        ArrayList<UserDietEntity> RecipeList = userDietRepository.findByUserid(userid);
        ArrayList<String> recipe = new ArrayList<>();
        ArrayList<String> recipeid = new ArrayList<>();
        if (!RecipeList.isEmpty()) {
            for (int i = 0; i < RecipeList.size(); i++) {
                recipe.add(RecipeList.get(i).getRecipenm());
            }
            for (int i = 0; i < recipe.size(); i++) {
                recipeid.add(cookingInfoRepository.findByRecipenm(recipe.get(i)).getRecipeid());
            }
            return recipeid;
        }
        else {
            throw new Exception("저장된 식단이 존재하지 않습니다.");
        }

    }

    /**
     * 식단 재료 차감 서비스
     */

/*    public void DeductionRecipe(String recipenm, String UserId){
        String recipeid = cookingInfoRepository.findByRecipenm(recipenm).getRecipeid();
        ArrayList<String> ingredname = new ArrayList<>();
        ArrayList<String> ingredamount = new ArrayList<>();
        ArrayList<IngredEntity> NeedIngredList = ingredRepository.findByRecipeid(recipeid);
        for(int i = 0; i<NeedIngredList.size(); i++){
            ingredname.add(NeedIngredList.get(i).getIRDNT_NM());
            ingredamount.add(NeedIngredList.get(i).getIRDNT_AM());
        }
    }*/


    public ArrayList<String> returnrecipe(String userid){
        ArrayList<String> recipenamelist = new ArrayList<>();
        ArrayList<UserDietEntity> userDietEntities = userDietRepository.findByUserid(userid);
        for(int i = 0 ; i< userDietEntities.size(); i++){
            recipenamelist.add(userDietEntities.get(i).getRecipenm());
        }

        return recipenamelist;
    }

    public ArrayList<String> returndate(String userid){
        ArrayList<String> recipedatelist = new ArrayList<>();
        ArrayList<UserDietEntity> userDietEntities = userDietRepository.findByUserid(userid);
        for(int i = 0 ; i< userDietEntities.size(); i++){
            recipedatelist.add(userDietEntities.get(i).getDate());
        }

        return recipedatelist;
    }

    public ArrayList<String> returntime(String userid){
        ArrayList<String> recipetimelist = new ArrayList<>();
        ArrayList<UserDietEntity> userDietEntities = userDietRepository.findByUserid(userid);
        for(int i = 0 ; i< userDietEntities.size(); i++){
            recipetimelist.add(userDietEntities.get(i).getTime());
        }

        return recipetimelist;
    }

    public ArrayList<UserDietDto> Dto(String userid){
        ArrayList<UserDietEntity> userDietEntities = userDietRepository.findByUserid(userid);
        ArrayList<UserDietDto> userDietDtos = new ArrayList<>();
        for(int i = 0 ; i< userDietEntities.size(); i++){
            userDietDtos.add(userDietEntities.get(i).toDto());
        }

        return userDietDtos;
    }

    public ArrayList<String> IngredientNameList(String recipeid) {
        ArrayList<String> ingrednamelist = new ArrayList<>();
        ArrayList<IngredEntity> ingredlist = ingredRepository.findByRecipeid(recipeid);
        for (int i = 0; i < ingredlist.size(); i++) {
            ingrednamelist.add(ingredlist.get(i).getIRDNT_NM());
        }
        return ingrednamelist;
    }

    public ArrayList<String> IngredientCpctyList(String recipeid) {
        ArrayList<String> ingredcpctylist = new ArrayList<>();
        ArrayList<IngredEntity> ingredlist = ingredRepository.findByRecipeid(recipeid);
        for (int i = 0; i < ingredlist.size(); i++) {
            ingredcpctylist.add(ingredlist.get(i).getIRDNT_CPCTY());
        }
        return ingredcpctylist;
    }

    public ArrayList<String> CookingProcessList(String recipeid) {
        ArrayList<String> cookingprocesslist = new ArrayList<>();
        ArrayList<CookingProcessEntity> cookingprocess = cookingProcessRepository.findByRecipeid(recipeid);
        for(int i = 0 ; i < cookingprocess.size(); i++){
            cookingprocesslist.add(cookingprocess.get(i).getCOOKING_DC());
        }
        return cookingprocesslist;
    }
}

