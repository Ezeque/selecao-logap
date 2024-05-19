package com.logap.backend;

import com.logap.backend.Models.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void criaCategoriaTest() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        ResponseEntity<Categoria> response = restTemplate.postForEntity("http://localhost:" + port + "/categoria/criar", categoria, Categoria.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNome()).isEqualTo("Eletrônicos");
    }

    @Test
    void excluirCategoriaTest() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        Categoria categoriaCriada = restTemplate.postForEntity("http://localhost:" + port + "/categoria/criar", categoria, Categoria.class).getBody();
        assertThat(categoriaCriada).isNotNull();

        restTemplate.postForEntity("http://localhost:" + port + "/categoria/excluir", categoriaCriada, Void.class);
    }
}
