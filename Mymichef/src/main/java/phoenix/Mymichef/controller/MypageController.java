package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//home -> mypage
@Controller
@RequiredArgsConstructor
public class MypageController {

    @GetMapping(value = "/mypage")
    public String mypage(Model model){
        return "mypage";
    }

}
