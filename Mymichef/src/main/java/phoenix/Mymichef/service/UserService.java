package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.domain.SecurityDetails;
import phoenix.Mymichef.domain.User;
import phoenix.Mymichef.domain.UserFormDto;
import phoenix.Mymichef.repository.UserRepository;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *  회원가입 서비스
     */
    public void saveUser(UserFormDto userFormDto)throws Exception{
        User user = null;
        Optional<User> findUser = userRepository.findById(user.getId());

        findUser.ifPresent(m -> {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        });
        userRepository.save(user);
        throw new Exception("회원가입에 성공했습니다!");
    }

    /**
     * 로그인 서비스
     */
    public UserDetails loadUserByUsername(String id)throws UsernameNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("아이디와 비밀번호를 확인하세요.");
        }
        User user1 = user.get(); //user가 Optional<User>형이라 이렇게 해줘야함.
        return new SecurityDetails(user1);
    }
}
