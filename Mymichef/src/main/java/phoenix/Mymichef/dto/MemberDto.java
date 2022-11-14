package phoenix.Mymichef.dto;

import lombok.*;
import phoenix.Mymichef.domain.entitiy.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Long birth;
    private Long height;
    private Long weight;
    private String gender;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .height(height)
                .weight(weight)
                .gender(gender)
                .build();
    }
    @Builder
    public MemberDto(Long id, String email, String password, String name, Long birth, Long height, Long weight, String gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
