package com.logap.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VogalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void encontrarVogalTest() {
        String inputString = "aAbBABacafe";

        Map<String, String> request = new HashMap<>();
        request.put("string", inputString);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(request);

        ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:" + port + "/vogal/encontrar", requestEntity, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("string")).isEqualTo(inputString);
        assertThat(response.getBody().get("vogal")).isEqualTo("e");
        assertThat(response.getBody().get("tempoTotal")).isNotNull();
    }
}