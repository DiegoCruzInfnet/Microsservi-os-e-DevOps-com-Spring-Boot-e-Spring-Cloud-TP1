package br.com.biblioteca.avaliacao_service.repository;

import br.com.biblioteca.avaliacao_service.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {
    List<Avaliacao> findByBookId(Long bookId);
    List<Avaliacao> findByUserId(Long userId);
}