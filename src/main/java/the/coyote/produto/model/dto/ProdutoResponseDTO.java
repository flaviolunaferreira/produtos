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
public class ProdutoResponseDTO {

    private String id;
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

    public ProdutoResponseDTO(ProdutoEntity produto) {
        this.setId(produto.getId());
        this.setNome(produto.getNome());
        this.setDescricao(produto.getDescricao());
        this.setTipo(produto.getTipo());
        this.setPreco(produto.getPreco());
        this.setDesconto(produto.getDesconto());
        this.setProdutoNovo(produto.isProdutoNovo());
        this.setImagens(produto.getImagens());
        this.setImagemDescricao(produto.getImagemDescricao());
        this.setAvaliacoes(produto.getAvaliacoes());
        this.setCores(produto.getCores());
        this.setTamanhos(produto.getTamanhos());
    }
}
