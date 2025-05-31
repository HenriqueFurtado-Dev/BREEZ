// src/main/java/br/com/breez/breez/repository/KnowledgeRepository.java
package br.com.breez.breez.repository;

import br.com.breez.breez.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
}
