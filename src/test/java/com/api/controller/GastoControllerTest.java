package com.api.controller;

import com.api.ApplicationBoot;
import com.api.model.dto.GastoDTO;
import com.api.model.entity.Gasto;
import com.api.repository.GastoRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ApplicationBoot.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.JVM)
public class GastoControllerTest {

    private static final Long ID_GASTO = 1L;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private WebApplicationContext webAppContextSetup;

    @Before
    public void before() {

        RestAssuredMockMvc.webAppContextSetup(webAppContextSetup);
        inserirGasto();
    }

    @After
    public void after() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void obterTodos() {
        MockMvcResponse mockMvcResponse = RestAssuredMockMvc.given().when().get("/gasto");
        System.out.println("Response: " + mockMvcResponse.asString());
        Assert.assertEquals("Erro ao obter todos os gastos!", HttpStatus.OK.value(), mockMvcResponse.getStatusCode());
    }

    @Test
    public void obterPorId() {
        MockMvcResponse mockMvcResponse = RestAssuredMockMvc.given().when().get("/gasto/" + ID_GASTO);
        System.out.println("Response: " + mockMvcResponse.asString());
        Assert.assertEquals("Erro ao obter gasto por ID!", HttpStatus.OK.value(), mockMvcResponse.getStatusCode());
    }

    @Test
    public void incluir() {
        GastoDTO dto = new GastoDTO();

        dto.setDescricao("descrição teste REST POST 1");
        dto.setNomePessoa("Nome pessoa REST POST 1");
        dto.setDataHora(new Date());
        dto.setValor(BigDecimal.valueOf(352.51));
        dto.setTags(Collections.emptyList());

        MockMvcResponse mockMvcResponse = RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/gasto");

        System.out.println("Response: " + mockMvcResponse.asString());
        Assert.assertEquals("Erro ao inserir um gasto!", HttpStatus.CREATED.value(), mockMvcResponse.getStatusCode());
    }

    @Test
    public void alterar() {
        GastoDTO dto = new GastoDTO();

        dto.setId(ID_GASTO);
        dto.setDescricao("descrição teste REST PUT 1");
        dto.setNomePessoa("Nome pessoa REST PUT 1");
        dto.setDataHora(new Date());
        dto.setValor(BigDecimal.valueOf(457.97));
        dto.setTags(Collections.emptyList());

        MockMvcResponse mockMvcResponse = RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .put("/gasto/" + ID_GASTO);

        System.out.println("Response: " + mockMvcResponse.asString());
        Assert.assertEquals("Erro ao atualizar um gasto!", HttpStatus.OK.value(), mockMvcResponse.getStatusCode());
    }

    @Test
    public void excluir() {
        MockMvcResponse mockMvcResponse = RestAssuredMockMvc.given().when().delete("/gasto/" + ID_GASTO);
        System.out.println("Response: " + mockMvcResponse.asString());
        Assert.assertEquals("Erro ao excluir um gasto por ID!", HttpStatus.OK.value(), mockMvcResponse.getStatusCode());
    }

    private void inserirGasto() {
        Gasto gasto = new Gasto();

        gasto.setId(ID_GASTO);
        gasto.setDescricao("descrição teste REST 1");
        gasto.setNomePessoa("Nome pessoa REST 1");
        gasto.setDataHora(new Date());
        gasto.setValor(BigDecimal.valueOf(251.52));
        gasto.setTags(Collections.emptyList());

        gastoRepository.save(gasto);
    }
}
