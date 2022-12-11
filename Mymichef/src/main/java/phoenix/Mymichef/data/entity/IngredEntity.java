package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.Mymichef.data.dto.IngredDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cookingingred")
public class IngredEntity {
    @Id
    @Column(name="ROW_NUM")
    public String ROW_NUM;

    @Column(name = "RECIPE_ID")
    public String recipeid;

    @Column(name = "IRDNT_SN")
    public String IRDNT_SN;

    @Column(name = "IRDNT_NM")
    public String IRDNT_NM;

    @Column(name = "IRDNT_CPCTY")
    public String IRDNT_CPCTY;

    @Column(name = "IRDNT_AM")
    public String IRDNT_AM;

    @Column(name = "INDNT_UN")
    public String IRDNT_UN;

    @Column(name = "IRDNT_TY_CODE")
    public String IRDNT_TY_CODE;

    @Column(name = "IRDNT_TY_NM")
    public String IRDNT_TY_NM;

    @Builder
    public IngredEntity(String ROW_NUM, String recipeid, String IRDNT_SN, String IRDNT_NM, String IRDNT_CPCTY, String IRDNT_AM, String IRDNT_UN, String IRDNT_TY_CODE, String IRDNT_TY_NM) {
        this.ROW_NUM = ROW_NUM;
        this.recipeid = recipeid;
        this.IRDNT_SN = IRDNT_SN;
        this.IRDNT_NM = IRDNT_NM;
        this.IRDNT_CPCTY = IRDNT_CPCTY;
        this.IRDNT_AM = IRDNT_AM;
        this.IRDNT_UN = IRDNT_UN;
        this.IRDNT_TY_CODE = IRDNT_TY_CODE;
        this.IRDNT_TY_NM = IRDNT_TY_NM;
    }

    public IngredDTO toDto(){
        return IngredDTO.builder()
                .ROW_NUM(ROW_NUM)
                .recipeid(recipeid)
                .IRDNT_SN(IRDNT_SN)
                .IRDNT_NM(IRDNT_NM)
                .IRDNT_CPCTY(IRDNT_CPCTY)
                .IRDNT_AM(IRDNT_AM)
                .IRDNT_UN(IRDNT_UN)
                .IRDNT_TY_CODE(IRDNT_TY_CODE)
                .IRDNT_TY_NM(IRDNT_TY_NM)
                .build();
    }
}

