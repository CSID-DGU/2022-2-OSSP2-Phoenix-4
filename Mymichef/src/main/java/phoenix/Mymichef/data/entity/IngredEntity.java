package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public String RECIPE_ID;

    @Column(name = "IRDNT_SN")
    public String IRDNT_SN;

    @Column(name = "IRDNT_NM")
    public String IRDNT_NM;

    @Column(name = "IRDNT_CPCTY")
    public String IRDNT_CPCTY;

    @Column(name = "IRDNT_TY_CODE")
    public String IRDNT_TY_CODE;

    @Column(name = "IRDNT_TY_NM")
    public String IRDNT_TY_NM;

    @Builder
    public IngredEntity(String ROW_NUM,
                     String RECIPE_ID,
                     String IRDNT_SN,
                     String IRDNT_NM,
                     String IRDNT_CPCTY,
                     String IRDNT_TY_CODE,
                     String IRDNT_TY_NM){
        this.ROW_NUM=ROW_NUM;
        this.RECIPE_ID = RECIPE_ID;
        this.IRDNT_SN = IRDNT_SN;
        this.IRDNT_NM = IRDNT_NM;
        this.IRDNT_CPCTY = IRDNT_CPCTY;
        this.IRDNT_TY_CODE = IRDNT_TY_CODE;
        this.IRDNT_TY_NM = IRDNT_TY_NM;
    }
}
