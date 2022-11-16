package phoenix.Mymichef.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "UserTbl")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    내가 pk로 정한 값은 String형인데 기본키를 자동생성 하려 하니 오류가 남..
    @Column(name = "Id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "Birth")
    private Long birth;
    @Column(name = "Password")
    private String pw;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Height")
    private Long height;
    @Column (name = "Weight")
    private Long weight;
    @Column (name = "Allergy")
    private String allergy;
    @Column(name = "phone")
    private String phone;

    @Builder
    public User(String id, String name, Long birth, String pw, String gender, Long height, Long weight, String allergy, String phone){
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.pw = pw;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.allergy = allergy;
        this.phone = phone;
    }

}
