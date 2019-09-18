package com.api.model.dto;

import com.api.model.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class GastoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomePessoa;

    private String descricao;

    private Date dataHora;

    private BigDecimal valor;

    private Collection<TagDTO> tags;
}
