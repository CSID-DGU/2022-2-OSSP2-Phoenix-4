package phoenix.Mymichef.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import phoenix.Mymichef.data.handler.LoginFailHandler;

//spring security 사용 이유는 보안적으로 뛰어나다는 것, 사용자가 일일이 구현하지 않아도 된다는 점 등
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginFailHandler loginFailHandler; //로그인 핸들러 의존성 주입
    @Bean // 패스워드 암호화 관련 메소드
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // spring security 쓰는 이유 중 하나

    /**
     * spring security web 관련 설정
     */
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        // 사용자 요청 중 /resources로 시작하는 요청은 제외
    }

    /**
     * spring security 권한 인증 관련 설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/home").authenticated()
                .anyRequest().permitAll() //인증없이 접근하게 해주는 프론트쪽 주소 현재는 home으로 임의 설정
                .and()

                .formLogin()
                .loginPage("/login") //로그인 페이지 설정
                .defaultSuccessUrl("/home") // 로그인 성공시 뜨는 화면
                .failureHandler(new LoginFailHandler()) // 로그인 실패하면 생성되는 객체 handler 폴더에서 따로 관리
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 시 페이지
                .invalidateHttpSession(true)
        ;

    }



}



