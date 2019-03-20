package com.fatlab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RA_TIPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    public String RA;

    @Enumerated(EnumType.STRING)
    public Tipo tipo;

    public RA_TIPO(String RA, Tipo tipo) {
        this.RA = RA;
        this.tipo = tipo;
    }
}
