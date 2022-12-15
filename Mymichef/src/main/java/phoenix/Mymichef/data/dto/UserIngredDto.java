package phoenix.Mymichef.data.dto;

import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private String ingredunit;

    public UserIngredEntity toEntity() {
        return UserIngredEntity.builder()
                .id(id)
                .userid(userid)
                .ingredname(ingredname)
                .ingredamount(ingredamount)
                .ingreddate(ingreddate)
                .ingredtype(ingredtype)
                .ingredunit(ingredunit)
                .build();
    }
    /**
     * 로그인 정보 반환
     * @return 현재 로그인한 유저 아이디
     */
    public static String currentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
