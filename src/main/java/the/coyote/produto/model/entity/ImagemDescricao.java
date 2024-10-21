package the.coyote.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagemDescricao {

    private String url;
    private String detalhe;

    public ImagemDescricao(String url, String detalhe) {
        this.url = url;
        this.detalhe = detalhe;
    }

}
