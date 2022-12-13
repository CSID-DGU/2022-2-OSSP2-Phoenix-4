package phoenix.Mymichef.data.dto;


import lombok.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import phoenix.Mymichef.data.entity.CookingInfoEntity;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CookingInfoDTO {
    public String ROW_NUM;
    public String recipeid;

    public String recipenm;

    public String SUMRY;

    public String NATION_CODE;

    public String nationnm;

    public String TY_CODE;

    public String TY_NM;

    public String COOKING_TIME;

    public String CALORIE;

    public String QNT;

    public String levelnm;

    public String IRDNT_CODE;

    public CookingInfoEntity toEntity(){
        return CookingInfoEntity.builder()
                .ROW_NUM(ROW_NUM)
                .recipeid(recipeid)
                .recipenm(recipenm)
                .SUMRY(SUMRY)
                .NATION_CODE(NATION_CODE)
                .nationnm(nationnm)
                .TY_CODE(TY_CODE)
                .TY_NM(TY_NM)
                .COOKING_TIME(COOKING_TIME)
                .CALORIE(CALORIE)
                .QNT(QNT)
                .levelnm(levelnm)
                .IRDNT_CODE(IRDNT_CODE)
                .build();
    }

    public CookingInfoDTO(CookingInfoEntity cookingInfoEntity){
        ROW_NUM = cookingInfoEntity.ROW_NUM;
        recipeid = cookingInfoEntity.recipeid;
        recipenm = cookingInfoEntity.recipenm;
        SUMRY = cookingInfoEntity.SUMRY;
        NATION_CODE = cookingInfoEntity.NATION_CODE;
        nationnm = cookingInfoEntity.nationnm;
        TY_CODE = cookingInfoEntity.TY_CODE;
        TY_NM = cookingInfoEntity.TY_NM;
        COOKING_TIME = cookingInfoEntity.COOKING_TIME;
        CALORIE = cookingInfoEntity.CALORIE;
        QNT = cookingInfoEntity.QNT;
        levelnm = cookingInfoEntity.levelnm;
        IRDNT_CODE = cookingInfoEntity.IRDNT_CODE;
    }

    public static String currentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
