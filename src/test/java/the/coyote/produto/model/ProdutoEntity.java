package the.coyote.produto.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import the.coyote.produto.model.entity.ProdutoEntity;

import java.util.Collections;
import java.math.BigDecimal;

class ProdutoEntityTest {

    private ProdutoEntity produto;

    @BeforeEach
    void setUp() {
        produto = new ProdutoEntity();
        produto.setId("123");
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setTipo("Tipo Teste");
        produto.setPreco(new BigDecimal(100.00));
        produto.setDesconto(new BigDecimal(10.00));
        produto.setProdutoNovo(true);
        produto.setImagens(Collections.emptyList());
        produto.setImagemDescricao(Collections.emptyList());
        produto.setAvaliacoes(Collections.emptyList());
        produto.setCores(Collections.emptyList());
        produto.setTamanhos(Collections.emptyList());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("123", produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals("Descrição Teste", produto.getDescricao());
        assertEquals("Tipo Teste", produto.getTipo());
        assertEquals(100.0, produto.getPreco());
        assertEquals(10.0, produto.getDesconto());
        assertTrue(produto.isProdutoNovo());
    }

    @Test
    void testEquals() {
        ProdutoEntity produto2 = new ProdutoEntity();
        produto2.setId("123");
        produto2.setNome("Produto Teste");

        assertEquals(produto, produto2);
    }

    @Test
    void testHashCode() {
        ProdutoEntity produto2 = new ProdutoEntity();
        produto2.setId("123");
        produto2.setNome("Produto Teste");

        assertEquals(produto.hashCode(), produto2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "ProdutoEntity(id=123, nome=Produto Teste, descricao=Descrição Teste, tipo=Tipo Teste, preco=100.0, desconto=10.0, produtoNovo=true, imagens=[], imagemDescricao=[], avaliacoes=[], cores=[], tamanhos=[])";
        assertEquals(expected, produto.toString());
    }
}
