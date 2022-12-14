package phoenix.Mymichef.data.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import phoenix.Mymichef.data.entity.UserEntity;
import phoenix.Mymichef.data.role.Role;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO implements UserDetails {


    private String userId;

    private String password;

    private String email;
    private String name;

    private String phoneNumber;
    private String gender;

    private String allergy;
    private Long height;
    private Long weight;



    private UserEntity SecurityUserEntity;
    public UserDTO(UserEntity SecurityUserEntity){

        this.SecurityUserEntity = SecurityUserEntity;
    }

    public UserEntity toModify(){
        return UserEntity.builder()
                .userId(userId)
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .height(height)
                .allergy(allergy)
                .weight(weight)
                .gender(gender)
                .build();
    }

    public UserEntity toEntity(PasswordEncoder passwordEncoder){
        return UserEntity.builder()
                .userId(userId)
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .height(height)
                .weight(weight)
                .allergy(allergy)
                .gender(gender)
                .build();
    }

    /**
     * ????????? ?????? ??????
     * @return ?????? ???????????? ?????? ?????????
     */
    public static String currentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }


    //????????? rhwo84?????? ????????? ????????? ?????? User ?????? ????????? ??????.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        Role role;
        if("rhwo84".equals(SecurityUserEntity.getUserId())){role = Role.ROLE_ADMIN;}
        else {role=Role.ROLE_USER;}
        collection.add(new SimpleGrantedAuthority(role.getValue()));
        return collection;
    }

    @Override
    public String getPassword(){ return SecurityUserEntity.getPassword(); }

    @Override
    public String getUsername() {
        return SecurityUserEntity.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
