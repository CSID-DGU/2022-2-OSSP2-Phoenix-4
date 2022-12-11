package phoenix.Mymichef.data.dto;

import lombok.*;
import phoenix.Mymichef.data.entity.UserShoppingEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserShoppingDto {

    private Long id;
    private String userid;
    private String ingred;
    private String amount;
    private String need;
    private String have;

    public UserShoppingEntity toEntity(){
        return UserShoppingEntity.builder()
                .id(id)
                .userid(userid)
                .ingred(ingred)
                .amount(amount)
                .need(need)
                .have(have)
                .build();
    }
}
