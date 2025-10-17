package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id_Aluno;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @JoinColumn(name = "id_professor")
    private Long id_Professor;

    @Column(name = "matricula")
    private String matricula;

    // Construtores
    public Aluno() {}

    public Aluno(String nome) {
        this.nome = nome;
    }

    public Aluno(String nome, Grupo time) {

        this.nome = nome;
        this.grupo = time;
    }

    // Getters e Setters
    public Long getId_Aluno() {
        return id_Aluno;
    }
    public void setId_Aluno(Long id) {
        this.id_Aluno = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo time) {
        this.grupo = time;
    }

    @Override
    public String toString() {
        return "ALUNO: {id=" + id_Aluno + ", nome= " + nome + "', time=" + (grupo != null ? grupo.getNome() : "null") + "}";
    }
}