package com.api.service;

import com.api.ApplicationBoot;
import com.api.exception.ResourceNotFoundException;
import com.api.model.dto.GastoDTO;
import com.api.model.dto.TagDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationBoot.class})
public class GastoServiceTest {

    private static final int NOME_PESSOA_SIZE = 255;
    private static final int DESCRICAO_SIZE = 1000;
    private static final int DESCRICAO_TAG_SIZE = 255;
    private static final int MAX_TAGS = 3;
    private static final BigDecimal VALOR_RANGE = new BigDecimal("1000.0");

    private GastoDTO dto;

    @Autowired
    private GastoService gastoService;

    @Test
    public void incluir() {

        dto = new GastoDTO();

        dto.setId(null);
        dto.setDataHora(new Date());
        dto.setDescricao(RandomStringUtils.randomAlphanumeric(DESCRICAO_SIZE));
        dto.setNomePessoa(RandomStringUtils.randomAlphanumeric(NOME_PESSOA_SIZE));
        dto.setValor(BigDecimal.valueOf(Math.random()).divide(VALOR_RANGE, BigDecimal.ROUND_DOWN));
        dto.setTags(generateTags());

        dto = this.gastoService.incluir(dto);

        Assert.assertNotNull("Erro ao inserir um gasto!", dto.getId());
    }

    @Test
    public void alterar() {

        if (dto == null) {
            incluir();
        }

        String descricao = RandomStringUtils.randomAlphanumeric(DESCRICAO_SIZE);
        String nomePessoa = RandomStringUtils.randomAlphanumeric(NOME_PESSOA_SIZE);
        BigDecimal valor = BigDecimal.valueOf(Math.random()).divide(VALOR_RANGE, BigDecimal.ROUND_DOWN);

        dto.setDescricao(descricao);
        dto.setNomePessoa(nomePessoa);
        dto.setValor(valor);

        dto = gastoService.alterar(dto.getId(), dto);

        Assert.assertEquals("Erro ao alterar uma descrição!", descricao, dto.getDescricao());
        Assert.assertEquals("Erro ao alterar o nome de uma pessoa!", nomePessoa, dto.getNomePessoa());
        Assert.assertEquals("Erro ao alterar o valor de um gasto!", valor, dto.getValor());
    }

    @Test
    @Transactional
    public void obterTodos() {

        if (dto == null) {
            incluir();
        }

        final Collection<GastoDTO> dtos = gastoService.obterTodos();

        Assert.assertFalse("Erro ao buscar os gastos", dtos.isEmpty());
    }

    @Test
    @Transactional
    public void obterPorId() {

        if (dto == null) {
            incluir();
        }

        Long id = dto.getId();

        dto = gastoService.obterPorId(id);

        Assert.assertEquals("Erro ao buscar um gasto por id!", id, dto.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void excluir() {

        if (dto == null) {
            incluir();
        }

        gastoService.excluir(dto.getId());

        gastoService.obterPorId(dto.getId());
    }

    private Collection<TagDTO> generateTags() {

        final Collection<TagDTO> tags = new ArrayList<>();

        final int maxItens = new Random().nextInt(MAX_TAGS) + 1;

        for (int i = 0; i < maxItens; i++) {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setDescricao(RandomStringUtils.randomAlphanumeric(DESCRICAO_TAG_SIZE));
            tags.add(tagDTO);
        }

        return tags;
    }
}
