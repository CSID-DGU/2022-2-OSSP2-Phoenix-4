package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FoodController {

    @GetMapping(value = "/food/input")
    public String shopping(Model model){
        return "food_input";
    }
}
//보류