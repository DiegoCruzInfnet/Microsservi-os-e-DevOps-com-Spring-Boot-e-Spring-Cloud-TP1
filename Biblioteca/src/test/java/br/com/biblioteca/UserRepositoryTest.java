package br.com.biblioteca;

import br.com.biblioteca.model.User;
import br.com.biblioteca.model.vo.UserDetails;
import br.com.biblioteca.repository.UserRepository;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    public void setUp(){
        UserDetails userDetails = new UserDetails(
                "UserNomeTest",
                "teste@email.com",
                "00000000000"
        );
        testUser = new User(userDetails);
    }

    @Test
    public void deveLocalizarUser(){
        User userSalvo = userRepository.save(testUser);
        assertNotNull(userSalvo.getId());
    }

    @Test
    public void deveBuscarUserById(){
        User userSalvo = userRepository.save(testUser);
        Long id = userSalvo.getId();
        Optional<User> user = userRepository.findById(id);
        assertEquals(id, user.get().getId());
    }

    @Test
    public void deveBuscarUserByEmail(){
        userRepository.save(testUser);
        List<User> users = userRepository.findByDetailsEmail("teste@email.com");
        assertFalse(users.isEmpty());
    }

    @Test
    public void deveBuscarUserByNome(){
        User userSalvo = userRepository.save(testUser);
        List<User> users = userRepository.findByDetailsNome("UserNomeTest");
        assertFalse(users.isEmpty());
    }
    @Test
    public void deveBuscarUserByTelefone(){
        User userSalvo = userRepository.save(testUser);
        List<User> users = userRepository.findByDetailsTelefone("00000000000");
        assertFalse(users.isEmpty());
    }

    @Test
    public void deveRetornarVazioQuandoUsuarioNaoExiste(){
        Long id = 99L;
        assertEquals(Optional.empty(), userRepository.findById(id));
    }
}

