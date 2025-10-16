package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluno> candidatos = new ArrayList<>();

    // Construtores
    public Grupo() {}

    public Grupo(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Aluno> getCandidatos() { return candidatos; }
    public void setCandidatos(List<Aluno> candidatos) { this.candidatos = candidatos; }

    public void adicionarCandidato(Aluno candidato) {
        candidatos.add(candidato);
        candidato.setTime(this);
    }

    @Override
    public String toString() {
        return "Time{id=" + id + ", nome='" + nome + "', candidatos=" + candidatos.size() + "}";
    }
}