package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private UserService userService;

    @GetMapping("")
    public String mypage(){
        return "mypage";
    }

    /**
     * 마이페이지 정보확인
     */

    @PostMapping("/userInfo")
    @ResponseBody
    public String checkMyInfo()throws Exception{
        JSONObject jsonObject = new JSONObject();
        UserDTO userInfo = new UserDTO();
        String userId = UserDTO.currentUserId();
    try {
        userInfo = userService.findUser(userId);
        System.out.println("api에서 정보 확인 userInfo = " + userInfo.getUserId());
    }catch (Exception e){
        return "정보 불러오기 오류 발생(server)";
    }
        jsonObject.put("userId", userInfo.getUserId());
        jsonObject.put("name", userInfo.getName());
        jsonObject.put("email", userInfo.getEmail());
        jsonObject.put("gender", userInfo.getGender());
        jsonObject.put("phoneNumber", userInfo.getPhoneNumber());
        jsonObject.put("allergy", userInfo.getAllergy());
        jsonObject.put("height", userInfo.getHeight());
        jsonObject.put("weight", userInfo.getWeight());

        return jsonObject.toString();
    }

    /**
     *  마이페이지 정보 수정
     */

    @PostMapping("/updateUserInfo")
    public @ResponseBody String updateUserInfo(@RequestBody UserDTO userDTO) throws Exception{

        String userId = UserDTO.currentUserId();
        UserDTO update =new UserDTO();

        try {
            userService.ModifyUser(userDTO, userId);

        }catch (Exception e) {
            System.out.println("e = " + e);
            return "update data 오류 (server)";
        }
        return "update date 통신 성공 (server)";
    }

}
