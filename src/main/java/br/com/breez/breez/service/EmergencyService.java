// src/main/java/br/com/breez/breez/service/EmergencyService.java
package br.com.breez.breez.service;

import br.com.breez.breez.model.EmergencyContact;
import br.com.breez.breez.repository.EmergencyContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyService {

    private final EmergencyContactRepository repo;

    public EmergencyService(EmergencyContactRepository repo) {
        this.repo = repo;
    }

    public List<EmergencyContact> getAllContacts() {
        return repo.findAll();
    }

    public List<String> getQuickGuide() {
        return List.of(
                "Hidrate-se imediatamente com água ou soro.",
                "Procure sombra ou ambiente fresco.",
                "Eleve as pernas se estiver tonto.",
                "Afrouxe roupas apertadas.",
                "Se sintomas graves, ligue para ajuda."
        );
    }

    public String getEmergencyNumber() {
        // número genérico de emergência
        return "192"; // SAMU
    }
}
