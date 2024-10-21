package the.coyote.produto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Collation;

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
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ProdutoBasicResponseDTO update(String id, ProdutoResquestDTO dto) throws NotFound {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public String delete(Integer id) throws IntegratyViolation {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<ProdutoResponseDTO> findByNomeConteiningIgnoreCase(String id) throws NotFound {
        throw new UnsupportedOperationException("Unimplemented method 'findByNomeConteiningIgnoreCase'");
    }
    

    private ProdutoEntity getProdutoById(String id) throws NotFound {
        return produtoRepository.findById(id).orElseThrow(() -> new NotFound("Sinto muito mas não encontrei o produto com o id: " + id ));
    }
}
