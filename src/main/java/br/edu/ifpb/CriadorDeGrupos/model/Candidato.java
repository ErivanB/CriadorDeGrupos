package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "candidatos")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    // getters e setters
}
