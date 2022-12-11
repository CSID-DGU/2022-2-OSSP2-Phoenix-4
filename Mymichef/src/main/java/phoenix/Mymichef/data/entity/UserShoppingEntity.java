package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.Mymichef.data.dto.UserShoppingDto;

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

    @Column(name = "need")
    private String need;

    @Column(name = "have")
    private String have;

    @Column(name = "unit")
    private String unit;

    @Builder
    public UserShoppingEntity(Long id, String userid, String ingred, String amount, String need, String have, String unit) {
        this.id = id;
        this.userid = userid;
        this.ingred = ingred;
        this.amount = amount;
        this.need = need;
        this.have = have;
        this.unit = unit;
    }

    public UserShoppingDto toDto(){
        return UserShoppingDto.builder()
                .id(id)
                .userid(userid)
                .ingred(ingred)
                .amount(amount)
                .need(need)
                .have(have)
                .unit(unit)
                .build();

    }
}

