package br.com.biblioteca.avaliacao_service.service;

import br.com.biblioteca.avaliacao_service.client.BibliotecaClient;
import br.com.biblioteca.avaliacao_service.model.Avaliacao;
import br.com.biblioteca.avaliacao_service.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final BibliotecaClient bibliotecaClient;

    public Avaliacao save(Avaliacao avaliacao) {
        // valida se livro existe
        bibliotecaClient.buscarLivro(avaliacao.getBookId());
        // valida se usuário existe
        bibliotecaClient.buscarUsuario(avaliacao.getUserId());
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> findById(String id) {
        return avaliacaoRepository.findById(id);
    }

    public List<Avaliacao> findByBookId(Long bookId) {
        return avaliacaoRepository.findByBookId(bookId);
    }

    public List<Avaliacao> findByUserId(Long userId) {
        return avaliacaoRepository.findByUserId(userId);
    }

    public void deleteById(String id) {
        avaliacaoRepository.deleteById(id);
    }
}
