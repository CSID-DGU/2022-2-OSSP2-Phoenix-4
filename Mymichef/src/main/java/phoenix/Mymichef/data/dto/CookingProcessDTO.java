package phoenix.Mymichef.data.dto;

import lombok.*;
import phoenix.Mymichef.data.entity.CookingProcessEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CookingProcessDTO {

    public String ROW_NUM;

    public String RECIPE_ID;

    public String COOKING_NO;

    public String COOKING_DC;

    public String STEP_TIP;

    public CookingProcessEntity toEntity(){
        return CookingProcessEntity.builder()
                .ROW_NUM(ROW_NUM)
                .RECIPE_ID(RECIPE_ID)
                .COOKING_NO(COOKING_NO)
                .COOKING_DC(COOKING_DC)
                .STEP_TIP(STEP_TIP)
                .build();
    }
}
