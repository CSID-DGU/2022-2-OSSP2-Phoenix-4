package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity (name = "usertbl")
public class UserEntity {
    @Id
    @Column(name = "userId")
    private String userId;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "height")
    private Long height;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "allergy")
    private String allergy;

    @Builder
    public UserEntity(String userId, String email, String password,
                      String name, String phoneNumber, String gender,
                      Long height, Long weight, String allergy){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.allergy = allergy;
    }

}
