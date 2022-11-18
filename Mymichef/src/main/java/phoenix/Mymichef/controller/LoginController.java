package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserService;

@Controller
@AllArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "login";
    }

}