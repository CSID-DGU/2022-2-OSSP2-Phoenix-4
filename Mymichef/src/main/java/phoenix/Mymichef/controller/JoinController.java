package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import phoenix.Mymichef.domain.UserFormDto;
import phoenix.Mymichef.service.UserService;

//일단은 회원가입 controller인데 아직 join 프론트가 없는듯하여 일단 보류
@Controller
@RequiredArgsConstructor
public class JoinController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/join")
    public String join(Model model){
        model.addAttribute("UserFromDto", new UserFormDto());
        return "join";
    }

    @PostMapping(value = "/join")
    public String join(UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "join";
        }
        try{
            userService.saveUser(userFormDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("userFormDto", new UserFormDto());
            return "join";
        }
        catch(Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "home";
        }

        return "redirect:/";
    }
}
