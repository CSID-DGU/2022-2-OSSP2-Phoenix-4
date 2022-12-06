package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.UserDTO;
import phoenix.Mymichef.data.entity.UserEntity;
import phoenix.Mymichef.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender emailsender;

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
     * 이메일 발송 서비스
     */
    public void mailsend(String ToAdress, String passwordmsg) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ToAdress);
        message.setFrom("rualneox@dgu.ac.kr");
        message.setSubject("[MyMichef] 임시 비밀번호 발급 입니다.");
        String emailmsg = "회원님의 임시 비밀번호는 '" + passwordmsg + "'입니다." ;
        message.setText(emailmsg);

        emailsender.send(message);
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
        return findUser.get().toDto();

    }
    /**
     * 임시 비밀번호 생성서비스
     */
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    /**
     * 비밀번호 저장 서비스
     */
    public void updatepassword(String userId, String password){
        Optional<UserEntity> user = userRepository.findById(userId);
        userRepository.save(UserEntity.builder().userId(userId).name(user.get().getName()).email(user.get().getEmail()).height(user.get().getHeight()).weight(user.get().getWeight()).allergy(user.get().getAllergy()).phoneNumber(user.get().getPhoneNumber()).gender(user.get().getGender()).password(passwordEncoder.encode(password)).build());
    }

    /**
     * 아이디 찾기 서비스
     */
    public String findId(String name, String email) throws Exception{
        ArrayList<UserEntity> find = userRepository.findIdByName(name);
        if(find.isEmpty()){
           throw new Exception("입력 정보를 확인하세요");
        }else{
            for(int i = 0 ; i< find.size(); i++){
                if(find.get(i).getEmail().equals(email)){
                    return find.get(i).getUserId();
                }
            }
        } throw new Exception("입력 정보를 확인하세요");
    }

    /**
     * 비밀번호 찾기 서비스
     */
    public String findPw(String name, String userId, String email) throws Exception{
        Optional<UserEntity> find = Optional.ofNullable(userRepository.findByNameAndUserIdAndEmail(name, userId, email));
        if(find.isEmpty()){
            throw new Exception("해당 회원이 존재하지 않습니다.");
        }
        String pw = getTempPassword();
        updatepassword(find.get().getUserId(), pw);
        return pw;
    }

    /**
     *  현재 로그인한 정보 가져오기
     */



}

