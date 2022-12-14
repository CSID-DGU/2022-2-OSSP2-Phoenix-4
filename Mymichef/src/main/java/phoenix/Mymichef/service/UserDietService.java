package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.dto.UserDietDto;
import phoenix.Mymichef.data.entity.CookingInfoEntity;
import phoenix.Mymichef.data.entity.CookingProcessEntity;
import phoenix.Mymichef.data.entity.IngredEntity;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int a = 0;
        String recipeId;

        if (MenuList.isEmpty()) {
            throw new Exception("해당하는 데이터가 없습니다.");
        } else {
            while (true) {
                //젤 많은 종류가 537개라
                a = random.nextInt(538) % (MenuList.size());

                if (MenuList.get(a) != null) {
                    break;
                }
            }
            recipeId = MenuList.get(a).getrecipe();
            return cookingInfoRepository.findByRecipeid(recipeId).getRecipenm();

        }
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
    public String changeDate(String date, int a) {
        int idate = 0;
        String sDate;

        idate = Integer.parseInt(date.substring(8));
        idate += a;
        sDate = String.valueOf(idate);

        StringBuffer replace = new StringBuffer();
        replace.append(date);
        String changed = String.valueOf(replace.replace(8, 10, sDate));

        return changed;
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

