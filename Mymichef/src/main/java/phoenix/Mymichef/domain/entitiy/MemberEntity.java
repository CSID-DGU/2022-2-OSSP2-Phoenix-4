package phoenix.Mymichef.domain.entitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private Long birth;

    @Column(length = 20, nullable = false)
    private Long height;

    @Column(length = 10, nullable = false)
    private Long weight;

    @Column(nullable = false)
    private String gender;

    @Builder
    public MemberEntity(Long id, String email, String password, String name, Long birth, Long height, Long weight, String gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
