package com.logap.backend;

import com.logap.backend.Models.Fornecedor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FornecedorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void criarFornecedorTest() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor X");

        ResponseEntity<Fornecedor> response = restTemplate.postForEntity("http://localhost:" + port + "/Fornecedor/criar", fornecedor, Fornecedor.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNome()).isEqualTo("Fornecedor X");
    }

    @Test
    void excluirFornecedorTest() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor X");

        Fornecedor fornecedorCriado = restTemplate.postForEntity("http://localhost:" + port + "/Fornecedor/criar", fornecedor, Fornecedor.class).getBody();
        assertThat(fornecedorCriado).isNotNull();

        restTemplate.postForEntity("http://localhost:" + port + "/Fornecedor/excluir", fornecedorCriado, Void.class);
    }
}
