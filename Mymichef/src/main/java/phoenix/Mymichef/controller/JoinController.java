//package phoenix.Mymichef.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import phoenix.Mymichef.data.dto.UserDTO;
//import phoenix.Mymichef.service.UserService;
//
////일단은 회원가입 controller인데 아직 join 프론트가 없는듯하여 일단 보류
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/join")
//public class JoinController {
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    @GetMapping("")
//    public String join(Model model){
//        model.addAttribute("UserFormDto", new UserDTO());
//        return "join";
//    }
//
//    @PostMapping("")
//    public String join(UserDTO userDto, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            return "join";
//        }
//        try{
//            userService.saveUser(userDto);
//        }catch (IllegalStateException e){
//            model.addAttribute("errorMessage", e.getMessage());
//            model.addAttribute("userFormDto", new UserDTO());
//            return "join";
//        }
//        catch(Exception e){
//            model.addAttribute("errorMessage", e.getMessage());
//            return "login";
//        }
//
//        return "login";
//    }
//}
