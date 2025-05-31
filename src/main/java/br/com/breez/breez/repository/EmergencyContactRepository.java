// src/main/java/br/com/breez/breez/repository/EmergencyContactRepository.java
package br.com.breez.breez.repository;

import br.com.breez.breez.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
