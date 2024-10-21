package the.coyote.produto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import the.coyote.produto.exception.DuplicateValue;
import the.coyote.produto.exception.IntegratyViolation;
import the.coyote.produto.exception.NotFound;
import the.coyote.produto.model.dto.ProdutoBasicResponseDTO;
import the.coyote.produto.model.dto.ProdutoResponseDTO;
import the.coyote.produto.model.dto.ProdutoResquestDTO;
import the.coyote.produto.model.repository.ProdutoRepository;
import the.coyote.produto.service.ProdutoService;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResponseDTO> findAll(int pagina, int quantidade) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<ProdutoResponseDTO> findById(String id) throws NotFound {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
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
    

}
