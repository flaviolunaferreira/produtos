package the.coyote.produto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import the.coyote.produto.model.entity.ProdutoEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoBasicResponseDTO {

    private String id;
    private String nome;
    private String descricao;
    private String tipo;
    private BigDecimal preco;
    private BigDecimal desconto;
    private boolean produtoNovo;

    public ProdutoBasicResponseDTO(ProdutoEntity produto) {
        this.setId(produto.getId());
        this.setNome(produto.getNome());
        this.setDescricao(produto.getDescricao());
        this.setTipo(produto.getTipo());
        this.setPreco(produto.getPreco());
        this.setDesconto(produto.getDesconto());
        this.setProdutoNovo(produto.isProdutoNovo());
    }

}
