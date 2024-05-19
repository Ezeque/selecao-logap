package com.logap.backend.Controller;

import com.logap.backend.Service.VogalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vogal")
public class VogalController {

    @Autowired
    private VogalService vogalService;

    @PostMapping("/encontrar")
    public ResponseEntity<Map<String, Object>> encontrarVogal(@RequestBody Map<String, String> request) {
        String frase = request.get("string");

        Instant comeco = Instant.now();
        String vogal = vogalService.encontrarVogal(frase);
        Instant fim = Instant.now();

        long duracao = Duration.between(comeco, fim).toMillis();

        Map<String, Object> response = new HashMap<>();
        response.put("string", frase);
        response.put("vogal", vogal);
        response.put("tempoTotal", duracao + "ms");

        return ResponseEntity.ok(response);
    }
}
