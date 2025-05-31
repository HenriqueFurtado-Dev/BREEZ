// src/main/java/br/com/breez/breez/service/StatisticsService.java
package br.com.breez.breez.service;

import br.com.breez.breez.model.AlertRecord;
import br.com.breez.breez.model.TemperatureRecord;
import br.com.breez.breez.repository.AlertRecordRepository;
import br.com.breez.breez.repository.TemperatureRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final TemperatureRecordRepository tempRepo;
    private final AlertRecordRepository alertRepo;

    public StatisticsService(TemperatureRecordRepository tempRepo,
                             AlertRecordRepository alertRepo) {
        this.tempRepo = tempRepo;
        this.alertRepo = alertRepo;
    }


    public Map<LocalDate, Double> getDailyTemperatureAverage() {
        List<TemperatureRecord> all = tempRepo.findAll();

        // Agrupa por data e calcula média
        Map<LocalDate, Double> dailyAvg = all.stream()
                .collect(Collectors.groupingBy(
                        TemperatureRecord::getDate,
                        Collectors.averagingDouble(TemperatureRecord::getTemperature)
                ));

        // Ordena o mapa por chave (data)
        return dailyAvg.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    public Map<String, Double> getWeeklyTemperatureAverage() {
        List<TemperatureRecord> all = tempRepo.findAll();
        WeekFields wf = WeekFields.ISO;

        // Para cada registro, determina a chave da semana: "<year>-W<weekNumber>"
        Map<String, Double> weeklyAvg = all.stream()
                .collect(Collectors.groupingBy(
                        rec -> {
                            int weekNumber = rec.getDate().get(wf.weekOfWeekBasedYear());
                            int year = rec.getDate().getYear();
                            // formato: "2025-W22"
                            return String.format("%d-W%02d", year, weekNumber);
                        },
                        Collectors.averagingDouble(TemperatureRecord::getTemperature)
                ));

        // Ordena por ano e semana (ordenando lexicograficamente costuma funcionar se o formato for YYYY-Www)
        return weeklyAvg.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    public Map<Integer, Double> getYearlyTemperatureAverage() {
        List<TemperatureRecord> all = tempRepo.findAll();

        Map<Integer, Double> yearlyAvg = all.stream()
                .collect(Collectors.groupingBy(
                        rec -> rec.getDate().getYear(),
                        Collectors.averagingDouble(TemperatureRecord::getTemperature)
                ));

        // Ordena pelo ano crescente
        return yearlyAvg.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    public Map<String, Long> getAlertCountByRegion() {
        List<AlertRecord> all = alertRepo.findAll();

        Map<String, Long> countByRegion = all.stream()
                .collect(Collectors.groupingBy(
                        AlertRecord::getRegion,
                        Collectors.counting()
                ));

        // Ordena por região (chave) em ordem alfabética
        return countByRegion.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
