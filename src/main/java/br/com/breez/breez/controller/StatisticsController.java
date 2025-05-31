// src/main/java/br/com/breez/breez/controller/StatisticsController.java
package br.com.breez.breez.controller;

import br.com.breez.breez.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/stats")
    public String showStatisticsPage(Model model) {

        // 1) Evolução diária de temperatura
        Map<java.time.LocalDate, Double> dailyMap = statisticsService.getDailyTemperatureAverage();
        List<String> dailyLabels = dailyMap.keySet().stream()
                .map(dateFormatter::format)
                .collect(Collectors.toList());
        List<Double> dailyValues = dailyMap.values().stream().collect(Collectors.toList());

        model.addAttribute("dailyLabels", dailyLabels);
        model.addAttribute("dailyValues", dailyValues);

        // 2) Evolução semanal de temperatura
        Map<String, Double> weeklyMap = statisticsService.getWeeklyTemperatureAverage();
        List<String> weeklyLabels = weeklyMap.keySet().stream().collect(Collectors.toList());
        List<Double> weeklyValues = weeklyMap.values().stream().collect(Collectors.toList());

        model.addAttribute("weeklyLabels", weeklyLabels);
        model.addAttribute("weeklyValues", weeklyValues);

        // 3) Comparativo anual de temperatura
        Map<Integer, Double> yearlyMap = statisticsService.getYearlyTemperatureAverage();
        List<String> yearlyLabels = yearlyMap.keySet().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        List<Double> yearlyValues = yearlyMap.values().stream().collect(Collectors.toList());

        model.addAttribute("yearlyLabels", yearlyLabels);
        model.addAttribute("yearlyValues", yearlyValues);

        // 4) Número de alertas por região
        Map<String, Long> alertMap = statisticsService.getAlertCountByRegion();
        List<String> alertRegionLabels = alertMap.keySet().stream().collect(Collectors.toList());
        List<Long> alertCounts = alertMap.values().stream().collect(Collectors.toList());

        model.addAttribute("alertRegionLabels", alertRegionLabels);
        model.addAttribute("alertCounts", alertCounts);

        return "stats"; // renderiza src/main/resources/templates/stats.html
    }
}
