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
@Entity(name = "cookinginfo")
public class CookingInfoEntity {
    @Id
    @Column(name = "ROW_NUM")
    public String ROW_NUM;
    @Column(name = "RECIPE_ID")
    public String recipeid;

    @Column(name = "RECIPE_NM_KO")
    public String RECIPE_NM_KO;

    @Column(name = "SUMRY")
    public String SUMRY;

    @Column(name = "NATION_CODE")
    public String NATION_CODE;

    @Column(name = "NATION_NM")
    public String nationnm;

    @Column(name = "TY_CODE")
    public String TY_CODE;

    @Column(name = "TY_NM")
    public String TY_NM;

    @Column(name = "COOKING_TIME")
    public String COOKING_TIME;

    @Column(name = "CALORIE")
    public String CALORIE;

    @Column(name = "QNT")
    public String QNT;

    @Column(name = "LEVEL_NM")
    public String levelnm;

    @Column(name = "IRDNT_CODE")
    public String IRDNT_CODE;


    @Builder
    public CookingInfoEntity(String ROW_NUM,
                             String recipeid,
                             String RECIPE_NM_KO,
                             String SUMRY,
                             String NATION_CODE,
                             String nationnm,
                             String TY_CODE,
                             String TY_NM,
                             String COOKING_TIME,
                             String CALORIE,
                             String QNT,
                             String levelnm,
                             String IRDNT_CODE) {
        this.ROW_NUM = ROW_NUM;
        this.recipeid = recipeid;
        this.RECIPE_NM_KO = RECIPE_NM_KO;
        this.SUMRY = SUMRY;
        this.NATION_CODE = NATION_CODE;
        this.nationnm = nationnm;
        this.TY_CODE = TY_CODE;
        this.TY_NM = TY_NM;
        this.COOKING_TIME = COOKING_TIME;
        this.CALORIE = CALORIE;
        this.QNT = QNT;
        this.levelnm = levelnm;
        this.IRDNT_CODE = IRDNT_CODE;
    }
}