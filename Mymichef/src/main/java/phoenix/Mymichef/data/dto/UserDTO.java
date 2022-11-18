package phoenix.Mymichef.data.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import phoenix.Mymichef.data.entity.UserEntity;

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
        return phoenix.Mymichef.data.entity.UserEntity.builder()
                .name(name)
                .gender(gender)
                .id(id)
                .pw(pw)
                .height(height)
                .weight(weight)
                .phone(phone)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
