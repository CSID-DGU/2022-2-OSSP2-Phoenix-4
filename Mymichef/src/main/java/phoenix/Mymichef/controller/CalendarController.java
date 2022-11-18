package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//'메뉴추천' 메뉴에 포함할지 홈화면에서 바로 달력을 볼 수 있도록 할지
@Controller
@RequiredArgsConstructor
public class CalendarController {

    @GetMapping(value = "/home/calendar")
    public String calendar(Model model){
        return "calendar";
    }
}
