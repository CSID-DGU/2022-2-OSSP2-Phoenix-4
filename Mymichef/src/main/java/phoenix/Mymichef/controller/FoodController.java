package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.data.entity.UserShoppingEntity;
import phoenix.Mymichef.service.UserIngredientService;
import phoenix.Mymichef.service.UserShoppingService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

    private final UserIngredientService userIngredientService;
    private final UserShoppingService userShoppingService;

    @GetMapping(" ")
    public String food_input(Model model){
        return "food_input";
    }


    /**
     *    식재료 입력 API
     */
    @PostMapping(value = "/input")
    public @ResponseBody String InputIngred(@RequestBody UserIngredDto userIngredDto) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String userId = UserIngredDto.currentUserId();

        try {
            userIngredientService.saveUserIngred(userIngredDto, userId);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return "재료 등록 실패(server)";
        }
        return "재료 등록 성공(server)";
    }

    /**
     *    식재료 확인 API
     */
    @PostMapping(value = "/check")
    public @ResponseBody String CheckIngred()throws Exception{
        JSONObject jsonObject = new JSONObject();
        String userId = UserDTO.currentUserId();
        int size = userIngredientService.CheckIngredamount(userId).size();
        for(int i = 0; i < size; i++) {
            try {
                jsonObject.put(userIngredientService.CheckIngredname(userId).get(i), userIngredientService.CheckIngredamount(userId).get(i));
            } catch (Exception e) {
                log.info("e", e);
                return "data 처리 오류 발생(server)";
            }
        }
        System.out.printf("%s", String.valueOf(jsonObject));
        return jsonObject.toString();
    }
}
//보류