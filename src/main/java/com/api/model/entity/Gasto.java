package com.api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_GASTO")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NO_PESSOA")
    private String nomePessoa;

    @Column(name = "DS_GASTO", length = 1000)
    private String descricao;

    @Column(name = "DT_GASTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(name = "VL_GASTO")
    private BigDecimal valor;

    @OneToMany(mappedBy = "gasto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Tag> tags;
}
