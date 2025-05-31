// src/main/java/br/com/breez/breez/repository/TemperatureRecordRepository.java
package br.com.breez.breez.repository;

import br.com.breez.breez.model.TemperatureRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRecordRepository extends JpaRepository<TemperatureRecord, Long> {
}
