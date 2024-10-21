package the.coyote.produto.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "produto")
@EqualsAndHashCode(callSuper=false)
public class ProdutoEntity extends BasicEntity {

    private String nome;
    private String descricao;
    private String tipo;
    private BigDecimal preco;
    private BigDecimal desconto;
    private boolean produtoNovo;
    private List<ImagemProduto> imagens;
    private List<ImagemDescricao> imagemDescricao;
    private List<Avaliacoes> avaliacoes;
    private List<Cores> cores;
    private List<Tamanhos> tamanhos;

}
