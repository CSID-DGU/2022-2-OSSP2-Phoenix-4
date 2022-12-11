package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserShoppingService;

//home -> shopping(장바구니)
@Controller
@RequiredArgsConstructor
public class ShoppingController {

    private final UserShoppingService userShoppingService;
    @GetMapping(value = "/home/shopping")
    public String shopping(Model model){
        String userId = UserDTO.currentUserId();
        userShoppingService.Shopping(userId, "30");
        return "shopping_ik";
    }
}
