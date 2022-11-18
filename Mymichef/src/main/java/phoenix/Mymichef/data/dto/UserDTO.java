package phoenix.Mymichef.data.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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


    private String name;

    private String gender;

    private String id;
    private String pw;
    private Long height;
    private Long weight;
    private String phone;

    private UserEntity SecurityUserEntity;
    public UserDTO(UserEntity SecurityUserEntity){
        this.SecurityUserEntity = SecurityUserEntity;
    }
    public UserEntity toEntity(PasswordEncoder passwordEncoder){
        return UserEntity.builder()
                .name(name)
                .gender(gender)
                .id(id)
                .pw(passwordEncoder.encode(pw))
                .height(height)
                .weight(weight)
                .phone(phone)
                .build();
    }

    //임의로 rhwo84라는 아이디 아니면 모두 User 권한 주도록 설정.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        Role role;
        if("rhwo84".equals(SecurityUserEntity.getId())){role = Role.ROLE_ADMIN;}
        else {role=Role.ROLE_USER;}
        collection.add(new SimpleGrantedAuthority(role.getValue()));
        return collection;
    }

    @Override
    public String getPassword(){ return SecurityUserEntity.getPw(); }

    @Override
    public String getUsername() {
        return SecurityUserEntity.getId();
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
