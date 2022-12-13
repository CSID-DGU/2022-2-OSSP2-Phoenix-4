package phoenix.Mymichef.data.entity;

import lombok.*;

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

    @Column(name = "nation")
    private String nation;

    @Column(name = "difficulty")
    private String difficulty;

    @Builder
    public UserDietEntity(Long id, String userid, String recipenm, String date, String time, String nation, String difficulty) {
        this.id = id;
        this.userid = userid;
        this.recipenm = recipenm;
        this.date = date;
        this.time = time;
        this.nation = nation;
        this.difficulty = difficulty;
    }

    public UserDietEntity(){}
}
