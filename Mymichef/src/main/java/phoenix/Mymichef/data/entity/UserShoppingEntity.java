package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "usershopping")

public class UserShoppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "ingred")
    private String ingred;

    @Column(name = "amount")
    private String amount;

    @Builder
    public UserShoppingEntity(Long id, String userid, String ingred, String amount) {
        this.id = id;
        this.userid = userid;
        this.ingred = ingred;
        this.amount = amount;
    }
}

