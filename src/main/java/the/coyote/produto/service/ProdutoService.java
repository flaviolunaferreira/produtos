package the.coyote.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import the.coyote.produto.exception.DuplicateValue;
import the.coyote.produto.exception.IntegratyViolation;
import the.coyote.produto.exception.NotFound;
import the.coyote.produto.model.dto.ProdutoBasicResponseDTO;
import the.coyote.produto.model.dto.ProdutoResponseDTO;
import the.coyote.produto.model.dto.ProdutoResquestDTO;

@Service
public interface ProdutoService {

    List<ProdutoResponseDTO> findAll(int pagina, int quantidade);

    Optional<ProdutoResponseDTO> findById(String id) throws NotFound;

    ProdutoBasicResponseDTO create(ProdutoResquestDTO dto) throws DuplicateValue;

    ProdutoBasicResponseDTO update(String id, ProdutoResquestDTO dto) throws NotFound;

    String delete(Integer id) throws IntegratyViolation;

    List<ProdutoResponseDTO> findByNomeConteiningIgnoreCase(String id) throws NotFound;

    
} 
