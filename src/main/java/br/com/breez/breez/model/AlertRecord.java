// src/main/java/br/com/breez/breez/model/AlertRecord.java
package br.com.breez.breez.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "alert_record")
public class AlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data em que o alerta foi emitido
    private LocalDate date;

    // Região para a qual o alerta foi enviado (ex: "Centro", "Zona Oeste" etc.)
    private String region;
}
