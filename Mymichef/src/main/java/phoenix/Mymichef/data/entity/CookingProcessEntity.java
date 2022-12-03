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
@Entity(name = "cookingprocess")

public class CookingProcessEntity {

    @Id
    @Column(name = "ROW_NUM")
    public String ROW_NUM;
    @Column(name = "RECIPE_ID")
    public String RECIPE_ID;
    @Column(name = "COOKING_NO")
    public String COOKING_NO;
    @Column(name = "COOKING_DC")
    public String COOKING_DC;
    @Column(name = "STEP_TIP")
    public String STEP_TIP;


    @Builder
    public CookingProcessEntity(String ROW_NUM,
                                String RECIPE_ID,
                                String COOKING_NO,
                                String COOKING_DC,
                                String STEP_TIP) {
        this.ROW_NUM = ROW_NUM;
        this.RECIPE_ID = RECIPE_ID;
        this.COOKING_NO = COOKING_NO;
        this.COOKING_DC = COOKING_DC;
        this.STEP_TIP = STEP_TIP;
    }
}
