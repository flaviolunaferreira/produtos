package the.coyote.produto.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import the.coyote.produto.exception.DuplicateValue;
import the.coyote.produto.exception.IntegratyViolation;
import the.coyote.produto.exception.NotFound;
import the.coyote.produto.model.dto.ProdutoBasicResponseDTO;
import the.coyote.produto.model.dto.ProdutoResponseDTO;
import the.coyote.produto.model.dto.ProdutoResquestDTO;
import the.coyote.produto.model.entity.ProdutoEntity;
import the.coyote.produto.model.repository.ProdutoRepository;
import the.coyote.produto.service.impl.ProdutoServiceImpl;

class ProdutoServiceImplTest {

    @InjectMocks
    private ProdutoServiceImpl produtoServiceImpl;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        ProdutoEntity produto = new ProdutoEntity();
        Page<ProdutoEntity> page = new PageImpl<>(List.of(produto));
        when(produtoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<ProdutoResponseDTO> result = produtoServiceImpl.findAll(0, 10);
        assertFalse(result.isEmpty());
        verify(produtoRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testFindById_Success() throws NotFound {
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findById(anyString())).thenReturn(Optional.of(produto));

        Optional<ProdutoResponseDTO> result = produtoServiceImpl.findById("1");
        assertTrue(result.isPresent());
        verify(produtoRepository, times(1)).findById(anyString());
    }

    @Test
    void testFindById_NotFound() {
        when(produtoRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> produtoServiceImpl.findById("1"));
        verify(produtoRepository, times(1)).findById(anyString());
    }

    @Test
    void testCreate_Success() throws DuplicateValue {
        ProdutoResquestDTO dto = new ProdutoResquestDTO();
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findByNomeIgnoreCase(anyString())).thenReturn(Collections.emptyList());
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produto);

        ProdutoBasicResponseDTO result = produtoServiceImpl.create(dto);
        assertNotNull(result);
        verify(produtoRepository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    void testCreate_DuplicateValue() {
        ProdutoResquestDTO dto = new ProdutoResquestDTO();
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findByNomeIgnoreCase(anyString())).thenReturn(List.of(produto));

        assertThrows(DuplicateValue.class, () -> produtoServiceImpl.create(dto));
        verify(produtoRepository, never()).save(any(ProdutoEntity.class));
    }

    @Test
    void testUpdate_Success() throws NotFound {
        ProdutoResquestDTO dto = new ProdutoResquestDTO();
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findById(anyString())).thenReturn(Optional.of(produto));

        ProdutoResponseDTO result = produtoServiceImpl.update("1", dto);
        assertNotNull(result);
        verify(produtoRepository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    void testUpdate_NotFound() {
        ProdutoResquestDTO dto = new ProdutoResquestDTO();
        when(produtoRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> produtoServiceImpl.update("1", dto));
        verify(produtoRepository, never()).save(any(ProdutoEntity.class));
    }

    @Test
    void testDelete_Success() throws NotFound, IntegratyViolation {
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findById(anyString())).thenReturn(Optional.of(produto));

        String result = produtoServiceImpl.delete("1");
        assertEquals("Produto apagado com sucesso!!!", result);
        verify(produtoRepository, times(1)).delete(any(ProdutoEntity.class));
    }

    @Test
    void testDelete_NotFound() {
        when(produtoRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> produtoServiceImpl.delete("1"));
        verify(produtoRepository, never()).delete(any(ProdutoEntity.class));
    }

    @Test
    void testFindByNomeContainingIgnoreCase() throws NotFound {
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoRepository.findByNomeIgnoreCase(anyString())).thenReturn(List.of(produto));

        List<ProdutoResponseDTO> result = produtoServiceImpl.findByNomeConteiningIgnoreCase("example");
        assertFalse(result.isEmpty());
        verify(produtoRepository, times(1)).findByNomeIgnoreCase(anyString());
    }
}
