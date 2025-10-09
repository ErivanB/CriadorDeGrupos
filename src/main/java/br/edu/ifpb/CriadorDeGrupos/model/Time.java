package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidato> candidatos = new ArrayList<>();

    // Construtores
    public Time() {}

    public Time(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Candidato> getCandidatos() { return candidatos; }
    public void setCandidatos(List<Candidato> candidatos) { this.candidatos = candidatos; }
}