package br.com.breez.breez.service;

import br.com.breez.breez.model.User;
import br.com.breez.breez.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void emailExists_WhenEmailExists_ReturnsTrue() {
        // Arrange
        String email = "teste@exemplo.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act
        boolean exists = userService.emailExists(email);

        // Assert
        assertTrue(exists);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void emailExists_WhenEmailDoesNotExist_ReturnsFalse() {
        // Arrange
        String email = "naoexiste@exemplo.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        boolean exists = userService.emailExists(email);

        // Assert
        assertFalse(exists);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void register_ShouldEncodePasswordAndSaveUser() {
        // Arrange
        User user = new User();
        user.setEmail("novo@exemplo.com");
        user.setPassword("senha123");
        
        when(passwordEncoder.encode("senha123")).thenReturn("senha_criptografada");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.register(user);

        // Assert
        verify(passwordEncoder).encode("senha123");
        verify(userRepository).save(user);
        assertEquals("senha_criptografada", user.getPassword());
    }
} 