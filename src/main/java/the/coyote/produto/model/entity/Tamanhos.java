package the.coyote.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tamanhos {

    private String tamanho;

    public Tamanhos(String tamanho) {
        this.tamanho = tamanho;
    }

}
