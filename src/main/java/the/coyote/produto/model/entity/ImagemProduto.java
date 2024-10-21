package the.coyote.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class ImagemProduto {

    private String url;
    private String descricao;

    public ImagemProduto(String url, String descricao) {
        this.url = url;
        this.descricao = descricao;
    }

}
