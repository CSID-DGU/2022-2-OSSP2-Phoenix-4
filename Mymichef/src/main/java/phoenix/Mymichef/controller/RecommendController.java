package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.CookingInfoDTO;
import phoenix.Mymichef.data.dto.UserDietDto;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.repository.UserDietRepository;
import phoenix.Mymichef.service.UserDietService;
import phoenix.Mymichef.service.UserIngredientService;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
    public String recommendDiet(@RequestBody Map params)throws Exception {
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        String userId = CookingInfoDTO.currentUserId();
        String menu;
        ArrayList<String> userIngred = userIngredientService.CheckIngredname(userId);
        String start, end;
        LocalDate now = userDietService.currentTime();
        UserDietDto save = new UserDietDto();
        int count  = 0;
        String realDate;
        realDate = (String) params.get("start");


        start = (String) params.get("start");
        end = (String) params.get("end");

        count = userDietService.countDate(end, start);

        List<String> dish = (List<String>) params.get("dish");

        for(int i = 0; i < count; i++){
            for(int j = 0; j < dish.size(); j++){
                jsonObject = new JSONObject();
                try {
                    menu = userDietService.recommendMenu(userIngred);
                }catch (Exception e){
                    throw new Exception("식단 찾기 오류(server.controller)");
                }

                jsonObject.put("date", realDate);
                jsonObject.put("RECIPE_NM_KO",menu);
                jsonObject.put("userid",userId);
                jsonObject.put("time",dish.get(j));

                Optional<UserDietEntity> exist = userDietService.OverlapTime(userId, realDate, dish.get(j));
                if(exist.isEmpty()) {
                    save.setRecipenm(menu);
                    save.setUserid(userId);
                    save.setTime(dish.get(j));
                    save.setDate(realDate);
                    try {
                        userDietService.saveRecommendInfo(save);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                else {
                    try {
                        userDietService.ModifyRecommendInfo(exist, menu);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                jsonArray.add(jsonObject);
            }
            realDate = userDietService.addOneDayCalendar(realDate);
        }

        return jsonArray.toString();
    }

    //나라별 추천
    @PostMapping("/nation")
    @ResponseBody
    public String recommendDietByNation(@RequestBody Map params) throws Exception{
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        String menu;
        String start, end;
        String userId = CookingInfoDTO.currentUserId();
        LocalDate now = userDietService.currentTime();
        UserDietDto save = new UserDietDto();
        int count  = 0;

        String realDate;

        realDate = (String) params.get("start");

        start = (String) params.get("start");
        end = (String) params.get("end");

        count = userDietService.countDate(end, start);

        List<String> dish = (List<String>) params.get("dish");

        for(int i = 0; i < count; i++){
            for(int j = 0; j < dish.size(); j++){
                jsonObject = new JSONObject();
                try {
                    menu = userDietService.recommendNation((String) params.get("nation"));
                }catch (Exception e){
                    throw new Exception("국가별 추천 식단 불러오기 실패(server.controller)");
                }

                jsonObject.put("date", realDate);
                jsonObject.put("RECIPE_NM_KO",menu);
                jsonObject.put("userid",userId);
                jsonObject.put("time",dish.get(j));

                Optional<UserDietEntity> exist = userDietService.OverlapTime(userId, realDate, dish.get(j));
                if(exist.isEmpty()) {
                    save.setRecipenm(menu);
                    save.setUserid(userId);
                    save.setTime(dish.get(j));
                    save.setDate(realDate);
                    try {
                        userDietService.saveRecommendInfo(save);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                else {
                    try {
                        userDietService.ModifyRecommendInfo(exist, menu);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                jsonArray.add(jsonObject);
            }
            realDate = userDietService.addOneDayCalendar(realDate);
        }

        return jsonArray.toString();
    }

    //난이도별 추천
    @PostMapping("/difficulty")
    @ResponseBody
    public String recommendDietDiffi(@RequestBody Map params)throws Exception{
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        String menu;
        String start, end;
        String userId = CookingInfoDTO.currentUserId();
        LocalDate now = userDietService.currentTime();
        UserDietDto save = new UserDietDto();
        int count  = 0;

        String realDate;
        realDate = (String) params.get("start");

        start = (String) params.get("start");
        end = (String) params.get("end");

        count = userDietService.countDate(end, start);

        List<String> dish = (List<String>) params.get("dish");

        for(int i = 0; i < count; i++){
            for(int j = 0; j < dish.size(); j++){
                jsonObject = new JSONObject();
                try {
                    menu = userDietService.recommendDifficulty((String) params.get("difficulty"));
                }catch (Exception e){
                    throw new Exception("난이도별 추천 식단 불러오기 실패(server.controller)");
                }

                jsonObject.put("date", realDate);
                jsonObject.put("RECIPE_NM_KO",menu);
                jsonObject.put("userid",userId);
                jsonObject.put("time",dish.get(j));

                Optional<UserDietEntity> exist = userDietService.OverlapTime(userId, realDate, dish.get(j));
                if(exist.isEmpty()) {
                    save.setRecipenm(menu);
                    save.setUserid(userId);
                    save.setTime(dish.get(j));
                    save.setDate(realDate);
                    try {
                        userDietService.saveRecommendInfo(save);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                else {
                    try {
                        userDietService.ModifyRecommendInfo(exist, menu);
                    } catch (Exception e) {
                        throw new Exception("저장 문제 발생(server.controller)");
                    }
                }
                jsonArray.add(jsonObject);
            }
            realDate = userDietService.addOneDayCalendar(realDate);
        }

        return jsonArray.toString();
    }

}
