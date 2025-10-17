package br.edu.ifpb.CriadorDeGrupos.service;


import br.edu.ifpb.CriadorDeGrupos.dao.AlunoDAO;
import br.edu.ifpb.CriadorDeGrupos.model.Aluno;
import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoDAO alunoDAO;

    public Aluno salvar(Aluno candidato) {
        return alunoDAO.save(candidato);
    }

    public Aluno buscarPorId(Long id) {
        return alunoDAO.findById(id);
    }

    public List<Aluno> listarTodos() {
        return alunoDAO.findAll();
    }

    public Aluno atualizar(Aluno candidato) {
        return alunoDAO.update(candidato);
    }

    public void excluir(Long id) {
        alunoDAO.delete(id);
    }

    public List<Aluno> buscarPorTime(Grupo time) {
        return alunoDAO.findByTime(time);
    }

    public void limparCandidatos() {
        alunoDAO.deleteAll();
    }

    public List<Aluno> buscarSemTime() {
        return alunoDAO.findSemTime();
    }

    public Aluno criarCandidato(String nome) {
        Aluno candidato = new Aluno(nome);
        return alunoDAO.save(candidato);
    }
}