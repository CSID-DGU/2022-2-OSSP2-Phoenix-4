package phoenix.Mymichef.data.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import phoenix.Mymichef.data.entity.IngredEntity;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredDTO {
    public String ROW_NUM;
    public String RECIPE_ID;

    public String IRDNT_SN;

    public String IRDNT_NM;

    public String IRDNT_CPCTY;

    public String IRDNT_TY_CODE;

    public String IRDNT_TY_NM;

    public IngredEntity toEntity(){
        return IngredEntity.builder()
                .ROW_NUM(ROW_NUM)
                .RECIPE_ID(RECIPE_ID)
                .IRDNT_SN(IRDNT_SN)
                .IRDNT_NM(IRDNT_NM)
                .IRDNT_CPCTY(IRDNT_CPCTY)
                .IRDNT_TY_CODE(IRDNT_TY_CODE)
                .IRDNT_TY_NM(IRDNT_TY_NM)
                .build();
    }
}
