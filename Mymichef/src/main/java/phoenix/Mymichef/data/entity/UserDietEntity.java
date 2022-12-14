package phoenix.Mymichef.data.entity;

import lombok.*;
import org.springframework.security.core.parameters.P;
import phoenix.Mymichef.data.dto.UserDietDto;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "userdiet")
public class UserDietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "recipenm")
    private String recipenm;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;


    @Builder
    public UserDietEntity(Long id, String userid, String recipenm, String date, String time) {
        this.id = id;
        this.userid = userid;
        this.recipenm = recipenm;
        this.date = date;
        this.time = time;
    }

    public UserDietDto toDto(){
        return UserDietDto.builder()
                .id(id)
                .userid(userid)
                .recipenm(recipenm)
                .date(date)
                .time(time)
                .build();
    }
    public UserDietEntity(){}
}
