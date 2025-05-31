// src/main/java/br/com/breez/breez/repository/AlertRecordRepository.java
package br.com.breez.breez.repository;

import br.com.breez.breez.model.AlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRecordRepository extends JpaRepository<AlertRecord, Long> {
}
