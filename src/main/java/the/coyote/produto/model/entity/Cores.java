package the.coyote.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cores {

    private String cor;

    public Cores(String cor) {
        this.cor = cor;
    }

}
