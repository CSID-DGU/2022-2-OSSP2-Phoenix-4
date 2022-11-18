package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.entity.UserEntity;
import phoenix.Mymichef.data.repository.UserRepository;

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
    public void saveUser(UserDTO userDto)throws Exception{
        UserEntity userEntity = userDto.toEntity(passwordEncoder);
        Optional<UserEntity> findUser = userRepository.findById(userEntity.getId());
        findUser.ifPresent(m -> {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        });
        userRepository.save(userEntity);
        System.out.println("넘어온 정보 = " + userEntity.getId());
        System.out.println("넘어온 정보 = " + userEntity.getPw());
        throw new Exception("회원가입에 성공했습니다!");
    }

    /**
     * 로그인 서비스
     */
    public UserDTO loadUserByUsername(String id)throws UsernameNotFoundException{
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("아이디와 비밀번호를 확인하세요.");
        }
        UserEntity userEntity = user.get();
        //user가 Optional<User>형이라 이렇게 해줘야함.
        System.out.println("userEntity = " + userEntity);

        return new UserDTO(userEntity);
    }
}
