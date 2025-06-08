package br.com.breez.breez.controller;

import br.com.breez.breez.model.TemperaturaRealtime;
import br.com.breez.breez.repository.TemperaturaRealtimeRepository;
import br.com.breez.breez.service.TemperaturaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/temperatura-realtime")
@RequiredArgsConstructor
public class TemperaturaRealtimeController {

    private final TemperaturaRealtimeRepository repository;
    private final TemperaturaProducer producer;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("temperaturas", repository.findTop100ByOrderByTimestampDesc());
        model.addAttribute("agregacao", repository.getAgregacaoPorRegiao());
        return "temperatura-realtime/index";
    }

    @GetMapping("/registrar")
    public String formularioRegistro(Model model) {
        model.addAttribute("temperatura", new TemperaturaRealtime());
        return "temperatura-realtime/registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute TemperaturaRealtime temperatura) {
        temperatura.setTimestamp(LocalDateTime.now());
        producer.enviarTemperatura(temperatura);
        return "redirect:/temperatura-realtime";
    }

    @GetMapping("/grafico")
    public String grafico(Model model) {
        model.addAttribute("dados", repository.findTop100ByOrderByTimestampDesc());
        return "temperatura-realtime/grafico";
    }

    @GetMapping("/api/dados")
    @ResponseBody
    public List<TemperaturaRealtime> getDados(@RequestParam(required = false) String regiao) {
        if (regiao != null && !regiao.isEmpty()) {
            return repository.findByRegiaoOrderByTimestampDesc(regiao);
        }
        return repository.findTop100ByOrderByTimestampDesc();
    }
} 