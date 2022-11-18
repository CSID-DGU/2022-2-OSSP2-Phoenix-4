package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//home -> shopping(장바구니)
@Controller
@RequiredArgsConstructor
public class ShoppingController {

    @GetMapping(value = "/home/shopping")
    public String shopping(Model model){
        return "shopping";
    }
}
