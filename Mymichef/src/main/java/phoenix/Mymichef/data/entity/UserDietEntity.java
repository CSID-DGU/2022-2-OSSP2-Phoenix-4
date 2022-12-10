package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "userdiet")
public class UserDietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "recipeid")
    private String recipeid;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Builder
    public UserDietEntity(Long id, String userid, String recipeid, String date, String time) {
        this.id = id;
        this.userid = userid;
        this.recipeid = recipeid;
        this.date = date;
        this.time = time;
    }
}
