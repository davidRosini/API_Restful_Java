package com.api.service;

import com.api.exception.ResourceNotFoundException;
import com.api.model.dto.GastoDTO;
import com.api.model.entity.Gasto;
import com.api.model.mapper.GastoMapper;
import com.api.repository.GastoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class GastoService {

    private final GastoMapper mapper;
    private final GastoRepository repository;

    public GastoDTO obterPorId(Long id) {
        final Optional<Gasto> one = repository.findById(id);
        final Gasto entity = one.orElseThrow(ResourceNotFoundException::new);
        return mapper.toDto(entity);
    }

    public Collection<GastoDTO> obterTodos() {
        final Iterable<Gasto> itens = repository.findAll();
        return mapper.toDto(itens);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GastoDTO incluir(GastoDTO dto) {
        final Gasto entity = mapper.toEntity(dto);
        return mapper.toDto(this.saveGasto(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GastoDTO alterar(Long id, GastoDTO dto) {
        final Optional<Gasto> one = repository.findById(id);
        final Gasto entity = one.orElseThrow(ResourceNotFoundException::new);
        mapper.fromDto(dto, entity);
        return mapper.toDto(this.saveGasto(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void excluir(Long id) {
        final Optional<Gasto> one = repository.findById(id);
        final Gasto entity = one.orElseThrow(ResourceNotFoundException::new);
        repository.delete(entity);
    }

    private Gasto saveGasto(Gasto gasto) {
        gasto.getTags().forEach(t -> t.setGasto(gasto));
        return repository.save(gasto);
    }
}
