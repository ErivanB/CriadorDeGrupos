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
    private AlunoDAO candidatoDAO;

    public Aluno salvar(Aluno candidato) {
        return candidatoDAO.save(candidato);
    }

    public Aluno buscarPorId(Long id) {
        return candidatoDAO.findById(id);
    }

    public List<Aluno> listarTodos() {
        return candidatoDAO.findAll();
    }

    public Aluno atualizar(Aluno candidato) {
        return candidatoDAO.update(candidato);
    }

    public void excluir(Long id) {
        candidatoDAO.delete(id);
    }

    public List<Aluno> buscarPorTime(Grupo time) {
        return candidatoDAO.findByTime(time);
    }

    public void limparCandidatos() {
        candidatoDAO.deleteAll();
    }

    public List<Aluno> buscarSemTime() {
        return candidatoDAO.findSemTime();
    }

    public Aluno criarCandidato(String nome) {
        Aluno candidato = new Aluno(nome);
        return candidatoDAO.save(candidato);
    }
}