package br.com.biblioteca.service;

import br.com.biblioteca.model.User;
import br.com.biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByDetailsEmail(email);
    }

    public List<User> findByNome(String nome) {
        return userRepository.findByDetailsNome(nome);
    }

    public List<User> findByTelefone(String telefone) {
        return userRepository.findByDetailsTelefone(telefone);
    }

}
