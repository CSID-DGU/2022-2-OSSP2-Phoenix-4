package phoenix.Mymichef.data.dto;


import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import phoenix.Mymichef.data.entity.UserDietEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class UserDietDto {
    private Long id;
    private String userid;
    private String recipenm;
    private String date;
    private String time;

    public static String currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    public UserDietEntity toEntity(){
        return UserDietEntity.builder()
                .id(id)
                .userid(userid)
                .recipenm(recipenm)
                .date(date)
                .time(time)

                .build();
    }


}


