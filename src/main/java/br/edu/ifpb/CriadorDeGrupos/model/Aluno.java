package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo time;

    // Construtores
    public Aluno() {}

    public Aluno(String nome) {
        this.nome = nome;
    }

    public Aluno(String nome, Grupo time) {
        this.nome = nome;
        this.time = time;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Grupo getTime() {
        return time;
    }

    public void setTime(Grupo time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ALUNO: {id=" + id + ", nome= " + nome + "', time=" + (time != null ? time.getNome() : "null") + "}";
    }
}