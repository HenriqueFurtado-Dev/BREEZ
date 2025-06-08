package br.com.breez.breez.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "temperaturas_realtime")
public class TemperaturaRealtime {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String regiao;
    private Double temperatura;
    private Double umidade;
    private String fonteSensor;
    private String condicaoClima;
    private String observacoes;
} 