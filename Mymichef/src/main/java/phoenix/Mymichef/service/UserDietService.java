package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.repository.CookingInfoRepository;
import phoenix.Mymichef.data.repository.IngredRepository;
import phoenix.Mymichef.data.repository.UserIngredRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class UserDietService {

    @Autowired
    private UserIngredRepository userIngredRepository;
    @Autowired
    private IngredRepository ingredRepository;
    @Autowired
    private CookingInfoRepository cookingInfoRepository;

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
            a = random.nextInt(100) % (MenuList.size());
            recipeId = MenuList.get(a).getrecipe();
            return cookingInfoRepository.findByRecipeid(recipeId).getRECIPE_NM_KO();

        }
    }
}