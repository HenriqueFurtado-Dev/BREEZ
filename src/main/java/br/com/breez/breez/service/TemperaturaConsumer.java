package br.com.breez.breez.service;

import br.com.breez.breez.model.TemperaturaRealtime;
import br.com.breez.breez.repository.TemperaturaRealtimeRepository;
import br.com.breez.breez.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperaturaConsumer {

    private final TemperaturaRealtimeRepository repository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberTemperatura(TemperaturaRealtime temperatura) {
        repository.save(temperatura);
    }
} 