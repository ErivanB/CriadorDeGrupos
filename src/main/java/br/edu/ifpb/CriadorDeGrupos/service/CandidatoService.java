package br.edu.ifpb.CriadorDeGrupos.service;


import br.edu.ifpb.CriadorDeGrupos.dao.CandidatoDAO;
import br.edu.ifpb.CriadorDeGrupos.model.Candidato;
import br.edu.ifpb.CriadorDeGrupos.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoDAO candidatoDAO;

    public Candidato salvar(Candidato candidato) {
        return candidatoDAO.save(candidato);
    }

    public Candidato buscarPorId(Long id) {
        return candidatoDAO.findById(id);
    }

    public List<Candidato> listarTodos() {
        return candidatoDAO.findAll();
    }

    public Candidato atualizar(Candidato candidato) {
        return candidatoDAO.update(candidato);
    }

    public void excluir(Long id) {
        candidatoDAO.delete(id);
    }

    public List<Candidato> buscarPorTime(Time time) {
        return candidatoDAO.findByTime(time);
    }

    public void limparCandidatos() {
        candidatoDAO.deleteAll();
    }

    public List<Candidato> buscarSemTime() {
        return candidatoDAO.findSemTime();
    }

    public Candidato criarCandidato(String nome) {
        Candidato candidato = new Candidato(nome);
        return candidatoDAO.save(candidato);
    }
}