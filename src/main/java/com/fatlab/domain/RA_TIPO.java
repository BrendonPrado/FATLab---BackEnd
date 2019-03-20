package com.fatlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RA_TIPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(unique = true)
    private String ra;

    @Enumerated(value = EnumType.STRING)
    public Tipo tipo;

    public RA_TIPO(String RA, Tipo tipo) {
        this.ra = RA;
        this.tipo = tipo;
    }


}
