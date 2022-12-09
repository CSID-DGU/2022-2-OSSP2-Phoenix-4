package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.CookingInfoDTO;
import phoenix.Mymichef.service.UserDietService;

//home-> UserInput (식단 추천받기 위해 정보 입력하는 페이지)
@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/recommend")
public class RecommendController {

    private UserDietService userDietService;

    @GetMapping(value = "")
    public String recommend(){
        return "recommend";
    }

    /**
     * 식단추천 API
     */
    @PostMapping("/default")
    @ResponseBody
    public String recommendDiet()throws Exception{
        JSONObject jsonObject = new JSONObject();
        String userId = CookingInfoDTO.currentUserId();

        CookingInfoDTO cookingInfoDTO = new CookingInfoDTO();
        try {
            cookingInfoDTO = userDietService.recommendDiet(userId);
            System.out.println("cookingInfoDTO.getRECIPE_NM_KO() = " + cookingInfoDTO.getRECIPE_NM_KO());
        }catch (Exception e){
            return "정보 불러오기 오류 발생 (server)";
        }
        //이 부분에 필요한 정보 저 json에 담아도 되는데 굳이 추천만 해줄거라 필요한가 싶어서 레시피 이름만 넣음
        jsonObject.put("RECIPE_NM_KO", cookingInfoDTO.getRECIPE_NM_KO());

        return jsonObject.toString();
    }

}
