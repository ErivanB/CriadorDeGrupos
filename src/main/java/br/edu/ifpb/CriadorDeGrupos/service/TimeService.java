package br.edu.ifpb.CriadorDeGrupos.service;

import br.edu.ifpb.CriadorDeGrupos.dao.CandidatoDAO;
import br.edu.ifpb.CriadorDeGrupos.dao.TimeDAO;
import br.edu.ifpb.CriadorDeGrupos.model.Candidato;
import br.edu.ifpb.CriadorDeGrupos.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeDAO timeDAO;

    @Autowired
    private CandidatoDAO candidatoDAO;

    public Time salvar(Time time) {
        return timeDAO.save(time);
    }

    public Time buscarPorId(Long id) {
        return timeDAO.findById(id);
    }

    public List<Time> listarTodos() {
        return timeDAO.findAll();
    }

    public Time atualizar(Time time) {
        return timeDAO.update(time);
    }

    public void excluir(Long id) {
        timeDAO.delete(id);
    }

    public void adicionarCandidato(Long timeId, Long candidatoId) {
        Time time = timeDAO.findById(timeId);
        Candidato candidato = candidatoDAO.findById(candidatoId);

        if (time != null && candidato != null) {
            candidato.setTime(time);
            candidatoDAO.update(candidato);
        }
    }

    public void limparTimes() {
        timeDAO.deleteAll();
    }

    public Time buscarPorNome(String nome) {
        return timeDAO.findByNome(nome);
    }

    public Time criarTime(String nome) {
        Time time = new Time(nome);
        return timeDAO.save(time);
    }
}