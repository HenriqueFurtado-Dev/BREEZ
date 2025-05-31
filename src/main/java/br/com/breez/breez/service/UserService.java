package br.com.breez.breez.service;

import br.com.breez.breez.model.User;
import br.com.breez.breez.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public boolean emailExists(String email) {
        return repo.findByEmail(email).isPresent();
    }

    public void register(User user) {
        // criptografa senha antes de salvar
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }
}
