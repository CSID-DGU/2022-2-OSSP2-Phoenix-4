package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserService;

import javax.validation.Valid;
import java.util.Map;

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

    /**
     *  회원가입 API
     */

   @RequestMapping(value = "/join")
    public @ResponseBody String join(@RequestBody UserDTO userDTO) throws Exception {
       try {
           userService.saveUser(userDTO);
       } catch (Exception e) {
           System.out.println("e = " + e);
           return "join_fail";
       }
       return "join_success";
   }
}