// src/main/java/br/com/breez/breez/service/KnowledgeService.java
package br.com.breez.breez.service;

import br.com.breez.breez.model.Knowledge;
import br.com.breez.breez.repository.KnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeService {

    private final KnowledgeRepository repository;

    public KnowledgeService(KnowledgeRepository repository) {
        this.repository = repository;
    }

    public List<Knowledge> findAll() {
        return repository.findAll();
    }

    public Optional<Knowledge> findById(Long id) {
        return repository.findById(id);
    }

    public Knowledge save(Knowledge knowledge) {
        return repository.save(knowledge);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
