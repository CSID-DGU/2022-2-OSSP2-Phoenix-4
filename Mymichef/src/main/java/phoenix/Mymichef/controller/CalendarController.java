package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.dto.UserDietDto;
import phoenix.Mymichef.service.UserDietService;

import java.util.ArrayList;

//'메뉴추천' 메뉴에 포함할지 홈화면에서 바로 달력을 볼 수 있도록 할지
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/calendar")
public class CalendarController {

    private final UserDietService userDietService;

    @GetMapping(value = "")
    public String calendar(Model model){
        return "calendar";
    }


    @PostMapping(value = "/check")
    public @ResponseBody String CheckShoppingList() throws Exception {
        JSONObject jsonrecipe = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String userId = UserDTO.currentUserId();
        try {
            ArrayList<String> recipe = userDietService.CheckRecipe(userId);
            ArrayList<String> recipename = userDietService.returnrecipe(userId);
            ArrayList<String> recipedate = userDietService.returndate(userId);
            ArrayList<String> recipetime = userDietService.returntime(userId);

            for (int i = 0; i < recipe.size(); i++) {
                JSONObject jsonObjectingred = new JSONObject();
                JSONObject jsonObjectrecipe = new JSONObject();
                jsonrecipe.put("recipe", recipename.get(i));
                jsonrecipe.put("date", recipedate.get(i));
                jsonrecipe.put("time", recipetime.get(i));

                ArrayList<String> ingrednamelist = userDietService.IngredientNameList(recipe.get(i));
                ArrayList<String> ingredcpctylist = userDietService.IngredientCpctyList(recipe.get(i));
                ArrayList<String> cookingprocess = userDietService.CookingProcessList(recipe.get(i));

                for (int j = 0; j < ingrednamelist.size(); j++) {
                    jsonObjectingred.put(ingrednamelist.get(j), ingredcpctylist.get(j));

                }
                jsonrecipe.put("재료", jsonObjectingred);

                for(int j = 0 ; j < cookingprocess.size(); j++){
                    jsonObjectrecipe.put("조리과정"+ (j+1), cookingprocess.get(j));

                }
                jsonrecipe.put("조리과정", jsonObjectrecipe);
                jsonObject.put(String.valueOf(i), jsonrecipe);
            }

        }catch (Exception e){
            log.info("e", e);
            return "저장된 식단이 존재하지 않습니다.";
        }


        return jsonObject.toString();
    }
}
