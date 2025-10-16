package br.edu.ifpb.CriadorDeGrupos.model;

import jakarta.persistence.*;

@Entity
@Table(name="professor")

public class Professor extends MembroAcademico{

    @Column
    private String dataDeIngresso;
    @Column
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id_professor;

    @Column
    private String matricula;
    @Column
    private String nome;
    @Column
    private String senha;


    @Override
    public String toString() {

        return "PROFESSOR: {id=" + id_professor + ", nome= " + nome + "', time=" + (grupo != null ? grupo.getNome() : "null") + "}";
    }



}
