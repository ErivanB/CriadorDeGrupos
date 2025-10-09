package br.edu.ifpb.CriadorDeGrupos.service;


import com.divisaotimes.dao.CandidatoDAO;
import com.divisaotimes.model.Candidato;
import com.divisaotimes.model.Time;
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
}
