package br.com.breez.breez.controller;

import br.com.breez.breez.model.User;
import br.com.breez.breez.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void showRegistrationForm_ShouldReturnRegisterView() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void processRegistration_WithValidUser_ShouldRedirectToLogin() throws Exception {
        when(userService.emailExists(any())).thenReturn(false);
        
        mockMvc.perform(post("/register")
                .param("fullName", "Test User")
                .param("email", "test@example.com")
                .param("password", "password123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?registered"));
    }

    @Test
    public void processRegistration_WithExistingEmail_ShouldReturnToRegisterPage() throws Exception {
        when(userService.emailExists("existing@example.com")).thenReturn(true);

        mockMvc.perform(post("/register")
                .param("fullName", "Test User")
                .param("email", "existing@example.com")
                .param("password", "password123")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("emailError"));
    }

    @Test
    public void processRegistration_WithInvalidData_ShouldReturnToRegisterPage() throws Exception {
        mockMvc.perform(post("/register")
                .param("fullName", "")
                .param("email", "invalid-email")
                .param("password", "")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().hasErrors());
    }
} 