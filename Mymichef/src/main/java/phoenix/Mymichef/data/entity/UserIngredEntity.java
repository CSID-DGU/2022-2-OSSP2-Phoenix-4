package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "useringred")
public class UserIngredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "ingredname")
    private String ingredname;

    @Column(name = "ingredamount")
    private String ingredamount;

    @Column(name = "ingreddate")
    private String ingreddate;

    @Column(name = "ingredtype")
    private String ingredtype;

    @Builder
    public UserIngredEntity(Long id, String userid, String ingredname, String ingredamount, String ingreddate, String ingredtype) {
        this.id = id;
        this.userid = userid;
        this.ingredname = ingredname;
        this.ingredamount = ingredamount;
        this.ingreddate = ingreddate;
        this.ingredtype = ingredtype;
    }
}
