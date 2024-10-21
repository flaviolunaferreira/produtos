package the.coyote.produto.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import the.coyote.produto.model.entity.Avaliacoes;
import the.coyote.produto.model.entity.Cores;
import the.coyote.produto.model.entity.ImagemDescricao;
import the.coyote.produto.model.entity.ImagemProduto;
import the.coyote.produto.model.entity.ProdutoEntity;
import the.coyote.produto.model.entity.Tamanhos;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ProdutoResquestDTO {

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

    public ProdutoEntity novoProduto() {
        return new ProdutoEntity(nome, descricao, tipo, preco, desconto, produtoNovo, imagens, imagemDescricao, avaliacoes, cores, tamanhos);
    }

}
