package br.com.breez.breez.service;

import br.com.breez.breez.model.TemperaturaRealtime;
import br.com.breez.breez.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperaturaProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarTemperatura(TemperaturaRealtime temperatura) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            temperatura
        );
    }
} 