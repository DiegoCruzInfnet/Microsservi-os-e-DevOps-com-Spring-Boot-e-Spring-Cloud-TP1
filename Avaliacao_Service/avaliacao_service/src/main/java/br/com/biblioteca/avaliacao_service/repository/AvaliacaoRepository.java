package br.com.biblioteca.avaliacao_service.repository;

import br.com.biblioteca.avaliacao_service.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByBookId(Long bookId);
    List<Avaliacao> findByUserId(Long userId);
}