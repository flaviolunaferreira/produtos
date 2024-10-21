package the.coyote.produto.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import the.coyote.produto.model.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoEntity, String>{

    List<ProdutoEntity> findByNomeIgnoreCase(String nome);

}
