package the.coyote.produto.controller;

import lombok.RequiredArgsConstructor;
import the.coyote.produto.exception.DuplicateValue;
import the.coyote.produto.exception.IntegratyViolation;
import the.coyote.produto.exception.NotFound;
import the.coyote.produto.model.dto.ProdutoBasicResponseDTO;
import the.coyote.produto.model.dto.ProdutoResponseDTO;
import the.coyote.produto.model.dto.ProdutoResquestDTO;
import the.coyote.produto.service.ProdutoService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/produto")
@RequiredArgsConstructor
public class ProdutoController {
    
    private final ProdutoService produtoService;

    @GetMapping("/")
    @Operation(summary = "Lista todos os produtos", description = "Exibe uma lista dos produtos em formato JSON.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(
                                @RequestParam(required = false) Integer pagina, 
                                @RequestParam(required = false) Integer quantidade) {
        try {
            return new ResponseEntity<>(produtoService.findAll(pagina, quantidade), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna produto pelo id", description = "Retorna o produto correspondente ao ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado."),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<Optional<ProdutoResponseDTO>> findById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(produtoService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Lista produtos por nome", description = "Lista todos os produtos que contêm o parâmetro fornecido no nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos encontrados."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<List<ProdutoResponseDTO>> findByNome(@PathVariable String nome) throws NotFound {
        try {
            return ResponseEntity.ok().body(produtoService.findByNomeConteiningIgnoreCase(nome));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Salva o cadastro do produto", description = "Cria um novo produto com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Erro de validação."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<ProdutoBasicResponseDTO> create(@RequestBody ProdutoResquestDTO dto) throws DuplicateValue {
        try {
            return new ResponseEntity<>(produtoService.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza o cadastro de produto", description = "Atualiza um produto existente com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable String id, @RequestBody ProdutoResquestDTO dto) throws NotFound {
        try {
            return new ResponseEntity<>(produtoService.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta produto", description = "Remove um produto do sistema com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
        @ApiResponse(responseCode = "423 ", description = "Produto esta bloqueado por uso em outras entidades"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<String> delete(@PathVariable String id) throws IntegratyViolation {
        try {
            return new ResponseEntity<>(produtoService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}