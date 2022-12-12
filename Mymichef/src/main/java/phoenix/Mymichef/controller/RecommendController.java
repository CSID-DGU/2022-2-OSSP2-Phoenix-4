package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.CookingInfoDTO;
import phoenix.Mymichef.data.dto.UserDietDto;
import phoenix.Mymichef.service.UserDietService;
import phoenix.Mymichef.service.UserIngredientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//home-> UserInput (식단 추천받기 위해 정보 입력하는 페이지)
@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/recommend")
public class RecommendController {

    private UserDietService userDietService;
    private UserIngredientService userIngredientService;


    @GetMapping(value = "")
    public String recommend(){
        return "recommend";
    }

    /**
     * 식단추천 API
     */

    // 식재료 기반 (default)
    @PostMapping("/default")
    @ResponseBody
    public String recommendDiet()throws Exception{
        JSONObject jsonObject = new JSONObject();
        String userId = CookingInfoDTO.currentUserId();
        String menu;
        ArrayList<String> userIngred = userIngredientService.CheckIngredname(userId);

        try {
            menu = userDietService.recommendMenu(userIngred);
            System.out.println("getRECIPE_NM_KO() = " + menu);
        }catch (Exception e){
            return "정보 불러오기 오류 발생 (server.Controller)";
        }
        //이 부분에 필요한 정보 저 json에 담아도 되는데 굳이 추천만 해줄거라 필요한가 싶어서 레시피 이름만 넣음
        jsonObject.put("RECIPE_NM_KO", menu);

        return jsonObject.toString();
    }

    //나라별 추천
    @PostMapping("/nation")
    @ResponseBody
    public String recommendDietByNation(@RequestBody Map params) throws Exception{
        JSONObject jsonObject = new JSONObject();
        String menu;
        try {
            System.out.println("가져온 값 = " + (String) params.get("nation"));
            menu = userDietService.recommendNation((String) params.get("nation"));

        }catch (Exception e){
            System.out.println("e = " + e);
            return "나라별 식단 추천 오류 발생(server)";
        }
        jsonObject.put("RECIPE_NM_KO", menu);

        return jsonObject.toString();
    }

    //난이도별 추천
    @PostMapping("/difficulty")
    @ResponseBody
    public String recommendDietDiffi(@RequestBody Map params)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String menu;
        try {
            menu = userDietService.recommendDifficulty((String) params.get("difficulty"));
        }catch (Exception e) {
            return "난이도별 식단 추천 오류 발생(server)";
        }
        jsonObject.put("RECIPE_NM_KO", menu);

        return jsonObject.toString();
    }

    @PostMapping("/save")
    public @ResponseBody String saveRecommendInfo(@RequestBody UserDietDto userDietDto, @RequestParam("recipeId") String recipeId) throws Exception {
        String userId = UserDietDto.currentUserId();
        UserDietDto save = new UserDietDto();
        String now = String.valueOf(userDietService.currentTime());

        try {
            save.setUserid(userDietDto.getUserid());
            save.setDate(userDietDto.getDate());
            save.setRecipeid(recipeId);
            save.setTime(userDietDto.getTime());
        } catch (Exception e) {
            System.out.println("e = " + e);
            return "식단, 시간, 날짜 저장 오류(server)";
        }
        userDietService.saveRecommendInfo(save);

        return "식단, 시간, 날짜 저장 성공(server)";


    }
}
