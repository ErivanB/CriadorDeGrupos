package br.edu.ifpb.CriadorDeGrupos.service;


import com.divisaotimes.dao.CandidatoDAO;
import com.divisaotimes.dao.TimeDAO;
import com.divisaotimes.model.Candidato;
import com.divisaotimes.model.Time;
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
}
