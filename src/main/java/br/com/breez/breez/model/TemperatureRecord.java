// src/main/java/br/com/breez/breez/model/TemperatureRecord.java
package br.com.breez.breez.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "temperature_record")
public class TemperatureRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data da medição (usamos LocalDate, sem hora)
    private LocalDate date;

    // Região de onde veio a medição (ex: "Centro", "Zona Norte", "Zona Sul" etc.)
    private String region;

    // Temperatura média registrada naquele dia/região
    private Double temperature;
}
