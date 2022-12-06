package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.service.UserIngredientService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

    private UserIngredientService userIngredientService;

    @GetMapping(" ")
    public String food_input(Model model){
        return "food_input";
    }


    /**
     *    식재료 입력 API
     */
    @PostMapping(value = "/input")
    public @ResponseBody String checkMyInfo(@RequestBody UserIngredDto userIngredDto) throws Exception {
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
}
//보류