package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class LoginController {

    private UserService userService;
    @GetMapping("")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "login";
    }

    //아이디 찾기
    @PostMapping(value = "/find_id")
    @ResponseBody
    public String find_id(Model model, String name) {
        String result = userService.findId(name);
        model.addAttribute("result", result);
        return "findId";
    }
}