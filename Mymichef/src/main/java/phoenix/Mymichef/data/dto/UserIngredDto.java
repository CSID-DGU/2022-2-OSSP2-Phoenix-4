package phoenix.Mymichef.data.dto;

import lombok.*;
import phoenix.Mymichef.data.entity.UserIngredEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserIngredDto {
    private Long id;
    private String userid;
    private String ingredname;
    private String ingredamount;
    private String ingreddate;
    private String ingredtype;

    public UserIngredEntity toEntity() {
        return UserIngredEntity.builder()
                .id(id)
                .userid(userid)
                .ingredname(ingredname)
                .ingredamount(ingredamount)
                .ingreddate(ingreddate)
                .ingredtype(ingredtype)
                .build();
    }
}
