package phoenix.Mymichef.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserFormDto {
    private String id;
    private String name;
     private Long birth;
    private String pw;
    private String gender;
    private Long height;
    private Long weight;
    private String allergy;
    private String phone;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .id(id)
                .name(name)
                .birth(birth)
                .pw(pw)
                .gender(gender)
                .height(height)
                .weight(weight)
                .allergy(allergy)
                .phone(phone)
                .build();
    }
}
