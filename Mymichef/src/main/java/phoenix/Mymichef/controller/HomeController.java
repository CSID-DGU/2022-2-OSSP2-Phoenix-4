package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@AllArgsConstructor
public class HomeController {

    // 메인 페이지(아직은 메인이 로그인 화면)
    @GetMapping("/")
    public String index() {
        return "login";
    }

}