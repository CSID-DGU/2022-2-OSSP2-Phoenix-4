package phoenix.Mymichef.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "ingredient")

public class IngredListEntity {
    @Id
    @Column(name = "ingred")
    private String ingred;

    @Column(name = "unit")
    private String unit;

    @Column(name = "type")
    private String type;

    @Builder
    public IngredListEntity(String ingred, String unit, String type) {
        this.ingred = ingred;
        this.unit = unit;
        this.type = type;
    }
}
