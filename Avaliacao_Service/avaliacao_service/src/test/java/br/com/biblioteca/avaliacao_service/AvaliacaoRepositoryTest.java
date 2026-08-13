package br.com.biblioteca.avaliacao_service;

import br.com.biblioteca.avaliacao_service.model.Avaliacao;
import br.com.biblioteca.avaliacao_service.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private Avaliacao avaliacao;

    @BeforeEach
    public void setUp() {
        avaliacao = new Avaliacao(1L, 1L, 5, "Ótimo livro!");
    }

    @Test
    public void deveSalvarAvaliacao() {
        Avaliacao salva = avaliacaoRepository.save(avaliacao);
        assertNotNull(salva.getId());
    }

    @Test
    public void deveBuscarAvaliacaoPorId() {
        Avaliacao salva = avaliacaoRepository.save(avaliacao);
        Optional<Avaliacao> found = avaliacaoRepository.findById(salva.getId());
        assertTrue(found.isPresent());
    }

    @Test
    public void deveBuscarAvaliacaoPorBookId() {
        avaliacaoRepository.save(avaliacao);
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByBookId(1L);
        assertFalse(avaliacoes.isEmpty());
    }

    @Test
    public void deveBuscarAvaliacaoPorUserId() {
        avaliacaoRepository.save(avaliacao);
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUserId(1L);
        assertFalse(avaliacoes.isEmpty());
    }

    @Test
    public void deveRetornarVazioQuandoNaoExiste() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByBookId(999L);
        assertTrue(avaliacoes.isEmpty());
    }
}
