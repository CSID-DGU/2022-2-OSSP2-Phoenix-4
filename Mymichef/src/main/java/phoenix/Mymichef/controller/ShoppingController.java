package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserShoppingService;

import java.util.ArrayList;

//home -> shopping(장바구니)
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/home/shopping")
public class ShoppingController {

    private final UserShoppingService userShoppingService;

    @GetMapping(value = "")
    public String shopping(Model model){
        return "shopping_ik";
    }

    @PostMapping(value = "/check")
    public @ResponseBody String CheckShoppingList()throws Exception{
        JSONObject jsonObject = new JSONObject();
        String userId = UserDTO.currentUserId();

        try {
            ArrayList<String> UserIngredname = userShoppingService.CheckShoppingname(userId);
            ArrayList<String> UserIngredamount = userShoppingService.CheckShoppingamount(userId);
            ArrayList<String> UserIngredunit = userShoppingService.CheckShoppingunit(userId);
            int size = UserIngredname.size();
            for (int i = 0; i < size; i++) {
                if (!UserIngredamount.get(i).equals("0")) {
                    if (!UserIngredunit.get(i).equals("양념"))
                        jsonObject.put(UserIngredname.get(i), UserIngredamount.get(i) + UserIngredunit.get(i));
                    else
                        jsonObject.put(UserIngredname.get(i), UserIngredunit.get(i));
                }
            }
        }catch (Exception e) {
            log.info("e", e);
            jsonObject.put("error", "등록된 장바구니가 없습니다.");
            return jsonObject.toString();
        }

        System.out.printf(" %s", String.valueOf(jsonObject));


        return jsonObject.toString();

    }
}

