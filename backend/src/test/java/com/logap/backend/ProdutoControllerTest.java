package com.logap.backend;

import com.logap.backend.Models.Categoria;
import com.logap.backend.Models.Fornecedor;
import com.logap.backend.Models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Categoria categoria;
    private Fornecedor fornecedor;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        ResponseEntity<Categoria> categoriaResponse = restTemplate.postForEntity("http://localhost:" + port + "/categoria/criar", categoria, Categoria.class);
        categoria = categoriaResponse.getBody();

        fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor X");
        ResponseEntity<Fornecedor> fornecedorResponse = restTemplate.postForEntity("http://localhost:" + port + "/Fornecedor/criar", fornecedor, Fornecedor.class);
        fornecedor = fornecedorResponse.getBody();
    }

    @Test
    void salvarProdutoTest() {
        System.out.println("Chegou aqui");
        Produto produto = new Produto();
        produto.setName("Smartphone");
        produto.setValor(999.99f);
        produto.setQuantidade(10);
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);

        ResponseEntity<Produto> response = restTemplate.postForEntity("http://localhost:" + port + "/produtos/salvar", produto, Produto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Smartphone");
    }

    @Test
    void recuperaTodosProdutosTest() {
        ResponseEntity<List<Produto>> response = restTemplate.exchange(
                "http://localhost:" + port + "/produtos/todos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Produto>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isGreaterThan(0);
    }

    @Test
    void recuperaProdutosFaltaTest() {
        ResponseEntity<List<Produto>> response = restTemplate.exchange(
                "http://localhost:" + port + "/produtos/falta",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Produto>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().stream().allMatch(produto -> produto.getQuantidade() == 0)).isTrue();
    }

    @Test
    void recuperaProdutoTest() {
        Long produtoId = 68L;

        ResponseEntity<Produto> response = restTemplate.getForEntity("http://localhost:" + port + "/produtos/buscar?id=" + produtoId, Produto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(produtoId);
    }
}
