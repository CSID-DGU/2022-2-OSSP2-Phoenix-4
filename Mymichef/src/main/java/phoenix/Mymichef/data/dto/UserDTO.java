package phoenix.Mymichef.data.dto;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import phoenix.Mymichef.data.entity.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private String name;

    private String gender;

    private String id;
    private String pw;
    private Long height;
    private Long weight;
    private String phone;

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
}
