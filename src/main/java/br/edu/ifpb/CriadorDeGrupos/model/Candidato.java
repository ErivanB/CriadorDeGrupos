package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidatos")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private Time time;

    // Construtores
    public Candidato() {}

    public Candidato(String nome) {
        this.nome = nome;
    }

    public Candidato(String nome, Time time) {
        this.nome = nome;
        this.time = time;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }

    @Override
    public String toString() {
        return "Candidato{id=" + id + ", nome='" + nome + "', time=" + (time != null ? time.getNome() : "null") + "}";
    }
}