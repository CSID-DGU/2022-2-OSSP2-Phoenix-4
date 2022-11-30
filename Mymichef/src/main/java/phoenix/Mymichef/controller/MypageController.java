package phoenix.Mymichef.controller;

import lombok.AllArgsConstructor;
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

@Controller
@AllArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private UserService userService;

    @GetMapping("")
    public String mypage(Principal principal, Model model){

        return "mypage";
    }

    /**
     * 마이페이지 정보확인
     */

    @PostMapping("/userInfo")
    public @ResponseBody String checkMyInfo(@RequestBody UserDTO userDTO)throws Exception{
        JSONObject jsonObject = new JSONObject();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDetail = (UserDTO) principal;
        String userId = ((UserDTO) principal).getUsername();

        UserDTO userInfo = new UserDTO();
        try {
            userInfo = userService.findUser(userId);
        } catch (Exception e){
            return "정보 불러오기 오류 발생(server)";
        }
        jsonObject.put("userId", userInfo.getUserId());
        jsonObject.put("name", userInfo.getName());
        jsonObject.put("password", userInfo.getPassword());
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

    public @ResponseBody String updateUserInfo(@RequestBody UserDTO userDTO)throws Exception{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDetail = (UserDTO) principal;
        String id = ((UserDTO) principal).getUsername();
        UserDTO update =new UserDTO();
        try {
            update = userService.findUser(id);
            update.setUserId(userDTO.getUserId());
            update.setName(userDTO.getName());
            update.setEmail(userDTO.getEmail());
            update.setPassword(userDTO.getPassword());
            update.setAllergy(userDTO.getAllergy());
            update.setGender(userDTO.getGender());
            update.setPhoneNumber(userDTO.getPhoneNumber());
            update.setHeight(userDTO.getHeight());
            update.setWeight(userDTO.getWeight());
        }catch (Exception e) {
            return "update data 오류 (server)";
        }
        userService.saveUser(update);
        return "update date 통신 성공 (server)";
    }

}
