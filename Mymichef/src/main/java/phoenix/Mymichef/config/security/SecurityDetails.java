package phoenix.Mymichef.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import phoenix.Mymichef.data.entity.UserEntity;

import java.util.Collection;

//인증에 성공한 객체에 대한 정보 Detail 설정
public class SecurityDetails implements UserDetails {

    private UserEntity securityUserEntity;

    public SecurityDetails(UserEntity securityUserEntity){
        this.securityUserEntity = securityUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword(){ return securityUserEntity.getPw(); }

    @Override
    public String getUsername() {
        return securityUserEntity.getId();
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
