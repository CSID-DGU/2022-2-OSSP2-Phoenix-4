package phoenix.Mymichef.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//home-> UserInput (식단 추천받기 위해 정보 입력하는 페이지)
@Controller
@RequiredArgsConstructor
public class RecommendController {

    @GetMapping(value = "/home/recommend")
    public String recommend(Model model){
        return "recommend";
    }

}