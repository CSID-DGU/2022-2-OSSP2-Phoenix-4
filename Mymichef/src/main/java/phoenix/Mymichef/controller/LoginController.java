package phoenix.Mymichef.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
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
           return "join_fail(server)";
       }
       return "join_success(server)";
   }

    /**
     *  아이디 찾기 API
     */

    @RequestMapping(value = "/findId")
    public @ResponseBody String findId(@RequestBody UserDTO userDTO) throws Exception {
        String returnJSON;
        try {
            returnJSON = userService.findId(userDTO.getName(), userDTO.getEmail());
        }catch (Exception e){
            log.info("e: ", e);
            return "통신 오류 발생(server)";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", returnJSON);
        return jsonObject.toString();
    }

    /**
     *  비밀번호 찾기 API
     */

    @RequestMapping (value = "/findPw")
    public @ResponseBody String findPw(@RequestBody UserDTO userDTO) throws Exception{
        try{
            userService.findPw(userDTO.getUserId());
        }catch (Exception e){
            log.info("e", e);
            return "입력하신 정보를 확인해주세요.(server)";
        }
        return "findPw_success(server)";
    }
}