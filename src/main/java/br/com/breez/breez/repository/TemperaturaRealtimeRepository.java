package br.com.breez.breez.repository;

import br.com.breez.breez.model.TemperaturaRealtime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import java.util.List;

public interface TemperaturaRealtimeRepository extends MongoRepository<TemperaturaRealtime, String> {
    List<TemperaturaRealtime> findTop100ByOrderByTimestampDesc();
    List<TemperaturaRealtime> findByRegiaoOrderByTimestampDesc(String regiao);

    @Aggregation(pipeline = {
        "{ $group: { " +
        "    _id: '$regiao', " +
        "    mediaTemperatura: { $avg: '$temperatura' }, " +
        "    maxTemperatura: { $max: '$temperatura' }, " +
        "    minTemperatura: { $min: '$temperatura' }, " +
        "    totalMedicoes: { $sum: 1 } " +
        "} }"
    })
    List<TemperaturaAgregacao> getAgregacaoPorRegiao();

    interface TemperaturaAgregacao {
        String getId();
        Double getMediaTemperatura();
        Double getMaxTemperatura();
        Double getMinTemperatura();
        Long getTotalMedicoes();
    }
} 