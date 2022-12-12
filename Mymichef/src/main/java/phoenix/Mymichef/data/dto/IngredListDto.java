package phoenix.Mymichef.data.dto;


import lombok.*;
import phoenix.Mymichef.data.entity.IngredListEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class IngredListDto {
    private String ingred;
    private String unit;
    private String type;

    public IngredListEntity toEntity(){
        return IngredListEntity.builder()
                .ingred(ingred)
                .unit(unit)
                .type(type)
                .build();
    }
}
