package phoenix.Mymichef.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";

        if(exception instanceof UsernameNotFoundException){
            errorMsg = "존재하지 않는 아이디입니다.";
        }else if(exception instanceof BadCredentialsException){
            errorMsg = "아이디 또는 비밀번호가 잘못 입력 되었습니다.";
        }

        if(!StringUtils.isEmpty(errorMsg)){
            request.setAttribute("errorMsg", errorMsg);
        }

        response.sendRedirect("/?errorMsg=" + URLEncoder.encode(errorMsg, "UTF-8"));	// redirect 처리
//        request.getRequestDispatcher("").forward(request, response);				// forward 처리
    }
}