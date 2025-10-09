package br.edu.ifpb.CriadorDeGrupos.dao;


import com.divisaotimes.model.Candidato;
import com.divisaotimes.model.Time;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CandidatoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Candidato save(Candidato candidato) {
        if (candidato.getId() == null) {
            entityManager.persist(candidato);
            return candidato;
        } else {
            return entityManager.merge(candidato);
        }
    }

    public Candidato findById(Long id) {
        return entityManager.find(Candidato.class, id);
    }

    public List<Candidato> findAll() {
        TypedQuery<Candidato> query = entityManager.createQuery(
                "SELECT c FROM Candidato c", Candidato.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Candidato candidato = findById(id);
        if (candidato != null) {
            entityManager.remove(candidato);
        }
    }

    public List<Candidato> findByTime(Time time) {
        TypedQuery<Candidato> query = entityManager.createQuery(
                "SELECT c FROM Candidato c WHERE c.time = :time", Candidato.class);
        query.setParameter("time", time);
        return query.getResultList();
    }

    @Transactional
    public Candidato update(Candidato candidato) {
        return entityManager.merge(candidato);
    }
}
