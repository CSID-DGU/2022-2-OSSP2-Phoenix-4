package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@NoArgsConstructor
@Entity (name = "usertbl")
public class UserEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "Gender")
    private String gender;

    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Password")
    private String pw;
    @Column(name = "Height")
    private Long height;
    @Column (name = "Weight")
    private Long weight;
    @Column(name = "phone")
    private String phone;

    @Builder
    public UserEntity(String name, String gender, String id, String pw, Long height, Long weight, String phone){
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.pw = pw;
        this.height = height;
        this.weight = weight;
        this.phone = phone;
    }

}
