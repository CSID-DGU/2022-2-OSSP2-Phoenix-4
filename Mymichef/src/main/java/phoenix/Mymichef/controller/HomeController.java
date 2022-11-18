package phoenix.Mymichef.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import phoenix.Mymichef.data.dto.UserDTO;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String home(Model model){
        return "home";
    }
}
