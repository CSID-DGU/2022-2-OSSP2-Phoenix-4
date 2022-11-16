package phoenix.Mymichef.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

//SecurityConfig에서 login 실패했을때 생성되는 객체 설정
@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String errorMessage;

        if(e instanceof BadCredentialsException){
            errorMessage = "아이디, 비밀번호를 확인해주세요.";
        }
        else{
            errorMessage = "알 수 없는 이유로 로그인 거부.";
        }
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); //한글로 인코딩
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(request,response,e);

    }
}
