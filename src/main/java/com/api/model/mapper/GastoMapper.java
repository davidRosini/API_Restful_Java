package com.api.model.mapper;

import com.api.model.dto.GastoDTO;
import com.api.model.entity.Gasto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GastoMapper {

    GastoDTO toDto(Gasto entity);

    Collection<GastoDTO> toDto(Iterable<Gasto> entities);

    Gasto toEntity(GastoDTO dto);

    @InheritInverseConfiguration(name = "toDto")
    void fromDto(GastoDTO dto, @MappingTarget Gasto entity);
}
