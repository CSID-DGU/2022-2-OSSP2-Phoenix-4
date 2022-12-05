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
@RequestMapping("")
public class FoodController {

    private UserIngredientService userIngredientService;

    @GetMapping(value = "/home/food_input")
    public String food_input(Model model){
        return "food_input";
    }


    /**
     *  식재료 입력 api
     */
    @PostMapping(value = "/food_input")
    public @ResponseBody String checkMyInfo(@RequestBody UserIngredDto userIngredDto) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDetail = (UserDTO) principal;
        String userId = userDetail.getUsername();

        try {
            userIngredientService.saveUserIngred(userIngredDto, userId);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return "재료 등록 실패";
        }
        return "재료 등록 성공";
    }
}
//보류