package com.atividadeProgramada.AtividadeProgramada2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
    private String data;
    private String hora;
    private String descricao;
    private  boolean concluida = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
