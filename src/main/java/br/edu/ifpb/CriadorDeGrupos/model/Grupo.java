package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id_grupo;

    @Column(name = "id_sorteio")
    private Long id_sorteio;

    @Column
    private String nome_grupo;




    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluno> alunos = new ArrayList<>();

    // Construtores
    public Grupo() {}

    public Grupo(String nome) {
        this.nome_grupo = nome;
    }

    // Getters e Setters
    public Long getId_grupo() { return id_grupo; }
    public void setId_grupo(Long id) { this.id_grupo = id; }

    public String getNome() { return nome_grupo; }
    public void setNome(String nome) { this.nome_grupo = nome; }

    public List<Aluno> getAlunos() { return alunos; }
    public void setAlunos(List<Aluno> candidatos) { this.alunos = candidatos; }

    public void adicionarCandidato(Aluno candidato) {
        alunos.add(candidato);
        candidato.setGrupo(this);
    }

    @Override
    public String toString() {
        return "Grupo {id=" + id_grupo + ", nome='" + nome_grupo + "', candidatos=" + alunos.size() + "}";
    }
}