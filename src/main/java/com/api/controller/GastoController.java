package com.api.controller;

import com.api.model.dto.GastoDTO;
import com.api.service.GastoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(tags = "Gasto")
@RequestMapping(value = "/gasto")
@RequiredArgsConstructor
@RestController
public class GastoController {

    @Autowired
    private GastoService service;

    @ApiOperation(value = "Obter gasto por id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GastoDTO> obterPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @ApiOperation(value = "Obter todos os gastos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<GastoDTO>> obterTodos() {
        Collection<GastoDTO> lista = service.obterTodos();
        return ResponseEntity.ok(lista);
    }

    @ApiOperation(value = "Incluir gasto")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GastoDTO> incluir(@Valid @RequestBody GastoDTO dto) {
        dto = service.incluir(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar gasto")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GastoDTO> alterar(@Valid @RequestBody GastoDTO dto, @PathVariable(name = "id") Long id) {
        dto = service.alterar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Excluir gasto por id")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluir(@PathVariable(name = "id") Long id) {
        service.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
