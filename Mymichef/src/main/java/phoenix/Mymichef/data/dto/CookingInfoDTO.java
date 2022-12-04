package phoenix.Mymichef.data.dto;


import lombok.*;

import phoenix.Mymichef.data.entity.CookingInfoEntity;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CookingInfoDTO {
    public String ROW_NUM;
    public String RECIPE_ID;

    public String RECIPE_NM_KO;

    public String SUMRY;

    public String NATION_CODE;

    public String NATION_NM;

    public String TY_CODE;

    public String TY_NM;

    public String COOKING_TIME;

    public String CALORIE;

    public String QNT;

    public String LEVEL_NM;

    public String IRDNT_CODE;

    public CookingInfoEntity toEntity(){
        return CookingInfoEntity.builder()
                .ROW_NUM(ROW_NUM)
                .RECIPE_ID(RECIPE_ID)
                .RECIPE_NM_KO(RECIPE_NM_KO)
                .SUMRY(SUMRY)
                .NATION_CODE(NATION_CODE)
                .NATION_NM(NATION_NM)
                .TY_CODE(TY_CODE)
                .TY_NM(TY_NM)
                .COOKING_TIME(COOKING_TIME)
                .CALORIE(CALORIE)
                .QNT(QNT)
                .LEVEL_NM(LEVEL_NM)
                .IRDNT_CODE(IRDNT_CODE)
                .build();
    }
}