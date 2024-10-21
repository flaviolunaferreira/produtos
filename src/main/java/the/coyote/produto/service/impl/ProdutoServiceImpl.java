package the.coyote.produto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import the.coyote.produto.exception.DuplicateValue;
import the.coyote.produto.exception.IntegratyViolation;
import the.coyote.produto.exception.NotFound;
import the.coyote.produto.model.dto.ProdutoBasicResponseDTO;
import the.coyote.produto.model.dto.ProdutoResponseDTO;
import the.coyote.produto.model.dto.ProdutoResquestDTO;
import the.coyote.produto.model.entity.ProdutoEntity;
import the.coyote.produto.model.repository.ProdutoRepository;
import the.coyote.produto.service.ProdutoService;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResponseDTO> findAll(Integer pagina, Integer quantidade) {

        int paginaFinal = (pagina != null) ? pagina : 0;
	    int quantidadeFinal = (quantidade != null) ? quantidade : 50;
	    PageRequest pageable = PageRequest.of(paginaFinal, quantidadeFinal);

        Page<ProdutoEntity> lista = produtoRepository.findAll(pageable);
        return lista.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoResponseDTO> findById(String id){
       return Optional.ofNullable(new ProdutoResponseDTO(getProdutoById(id)));
    }

    @Override
    public ProdutoBasicResponseDTO create(ProdutoResquestDTO dto) throws DuplicateValue {
        if(!getByName(dto).isEmpty()) throw new DuplicateValue("Sinto muito mas já tenho um produto cadastrado com esse nome: " + dto.getNome());
        return new ProdutoBasicResponseDTO(produtoRepository.save(dto.novoProduto()));
    }

    @Override
    public ProdutoResponseDTO update(String id, ProdutoResquestDTO dto) throws NotFound {
        if(!getByName(dto).isEmpty()) throw new DuplicateValue("Sinto muito mas já tenho um produto cadastrado com esse nome: " + dto.getNome());

        // Atualiza os dados do produto usando Optional e map
        return Optional.ofNullable(getProdutoById(id))
        .map(produto -> {
            // Atualiza os campos do produto
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            produto.setTipo(dto.getTipo());
            produto.setPreco(dto.getPreco());
            produto.setDesconto(dto.getDesconto());
            produto.setProdutoNovo(dto.isProdutoNovo());
            produto.setImagens(dto.getImagens());
            produto.setImagemDescricao(dto.getImagemDescricao());
            produto.setAvaliacoes(dto.getAvaliacoes());
            produto.setCores(dto.getCores());
            produto.setTamanhos(dto.getTamanhos());

            // Salva o produto atualizado
            produtoRepository.save(produto);
          
            // Retorna o DTO de resposta com o produto atualizado
            return new ProdutoResponseDTO(produto);
        }).orElseThrow(() -> new NotFound("Produto não encontrado com o ID: " + id));
    }

    @Override
    public String delete(String id) throws IntegratyViolation {
        try {
            ProdutoEntity produto = getProdutoById(id);
            produtoRepository.delete(produto);
            return "Produto apagado com sucesso!!!";
        } catch (IntegratyViolation i) {
            return i.getMessage();
        }

    }

    @Override
    public List<ProdutoResponseDTO> findByNomeConteiningIgnoreCase(String nome) throws NotFound {
        List<ProdutoEntity> lista = produtoRepository.findByNomeIgnoreCase(nome);
        return lista.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }
    

    private ProdutoEntity getProdutoById(String id) throws NotFound {
        return produtoRepository.findById(id).orElseThrow(() -> new NotFound("Sinto muito mas não encontrei o produto com o id: " + id ));
    }

    private List<ProdutoEntity> getByName(ProdutoResquestDTO produto) {
        List<ProdutoEntity> lista = produtoRepository.findByNomeIgnoreCase(produto.getNome());
        return lista;
    }
}
