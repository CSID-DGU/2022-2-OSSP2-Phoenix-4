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

        int size = userShoppingService.CheckShoppingname(userId).size();
        for(int i = 0; i < size; i++) {
            try {
                if(!userShoppingService.CheckShoppingamount(userId).get(i).equals("0")) {
                    if (!userShoppingService.CheckShoppingunit(userId).get(i).equals("양념"))
                        jsonObject.put(userShoppingService.CheckShoppingname(userId).get(i), userShoppingService.CheckShoppingamount(userId).get(i)+userShoppingService.CheckShoppingunit(userId).get(i));
                    else
                        jsonObject.put(userShoppingService.CheckShoppingname(userId).get(i), userShoppingService.CheckShoppingunit(userId).get(i));
                }
            } catch (Exception e) {
                log.info("e", e);
                return "data 처리 오류 발생(server)";
            }
        }
        System.out.printf("%s", String.valueOf(jsonObject));
        return jsonObject.toString();
    }
}

