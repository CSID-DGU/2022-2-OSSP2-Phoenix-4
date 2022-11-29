package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.entity.JoinEntity;
import phoenix.Mymichef.data.entity.UserEntity;
import phoenix.Mymichef.data.repository.JoinRepository;
import phoenix.Mymichef.data.repository.UserRepository;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원가입 서비스
     */
    public void saveUser(UserDTO userDto) throws Exception {
        UserEntity userEntity = userDto.toEntity(passwordEncoder);
        validateDuplicateUser(userEntity);
    }

    private void validateDuplicateUser(UserEntity userEntity) throws Exception {
        Optional<UserEntity> findUser = userRepository.findById(userEntity.getUserId());
        findUser.ifPresent(m -> {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        });
        userRepository.save(userEntity);
        throw new Exception("회원가입에 성공했습니다!");
    }


    /**
     * 로그인 서비스
     */
    public UserDTO loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("아이디와 비밀번호를 확인하세요.");
        }
        UserEntity userEntity = user.get();
        //user가 Optional<User>형이라 이렇게 해줘야함.
        System.out.println("userEntity = " + userEntity);

        return new UserDTO(userEntity);
    }

    /**
     * 마이페이지 서비스
     */

    public UserDTO findUser(String id) {
        Optional<UserEntity> findUser = userRepository.findById(id);
        UserEntity userEntity = findUser.get();
        UserDTO userDTO = UserDTO.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .height(userEntity.getHeight())
                .weight(userEntity.getWeight())
                .gender(userEntity.getGender())
                .name(userEntity.getName())
                .allergy(userEntity.getAllergy())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
        return userDTO;

    }

    /**
     * 아이디, 비밀번호 찾기 서비스
     */
    public String findId(String name) {
        Optional<UserEntity> findId = Optional.ofNullable(userRepository.findIdByName(name));
        if(findId.isPresent()){
            return findId.get().getUserId();
        }else{
            return "아이디와 비밀번호를 확인하세요";
        }
    }


}

