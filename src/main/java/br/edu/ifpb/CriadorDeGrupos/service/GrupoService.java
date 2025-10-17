package br.edu.ifpb.CriadorDeGrupos.service;

import br.edu.ifpb.CriadorDeGrupos.dao.AlunoDAO;
import br.edu.ifpb.CriadorDeGrupos.dao.GrupoDAO;
import br.edu.ifpb.CriadorDeGrupos.model.Aluno;
import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoDAO timeDAO;

    @Autowired
    private AlunoDAO candidatoDAO;

    public Grupo salvar(Grupo time) {
        return timeDAO.save(time);
    }

    public Grupo buscarPorId(Long id) {
        return timeDAO.findById(id);
    }

    public List<Grupo> listarTodos() {
        return timeDAO.findAll();
    }

    public Grupo atualizar(Grupo time) {
        return timeDAO.update(time);
    }

    public void excluir(Long id) {
        timeDAO.delete(id);
    }

    public void adicionarCandidato(Long timeId, Long candidatoId) {
        Grupo time = timeDAO.findById(timeId);
        Aluno candidato = candidatoDAO.findById(candidatoId);

        if (time != null && candidato != null) {
            candidato.setGrupo(time);
            candidatoDAO.update(candidato);
        }
    }

    public void limparTimes() {
        timeDAO.deleteAll();
    }

    public Grupo buscarPorNome(String nome) {
        return timeDAO.findByNome(nome);
    }

    public Grupo criarTime(String nome) {
        Grupo time = new Grupo(nome);
        return timeDAO.save(time);
    }
}