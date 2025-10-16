package br.edu.ifpb.CriadorDeGrupos.dao;

import br.edu.ifpb.CriadorDeGrupos.model.Aluno;
import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import br.edu.ifpb.CriadorDeGrupos.model.MembroAcademico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoDAO extends MembroAcademico {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Aluno save(Aluno candidato) {
        if (candidato.getId() == null) {
            entityManager.persist(candidato);
            return candidato;
        } else {
            return entityManager.merge(candidato);
        }
    }

    public Aluno findById(Long id) {
        return entityManager.find(Aluno.class, id);
    }

    public List<Aluno> findAll() {
        TypedQuery<Aluno> query = entityManager.createQuery(
                "SELECT c FROM Aluno c ORDER BY c.id", Aluno.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Aluno candidato = findById(id);
        if (candidato != null) {
            entityManager.remove(candidato);
        }
    }

    public List<Aluno> findByTime(Grupo time) {
        TypedQuery<Aluno> query = entityManager.createQuery(
                "SELECT c FROM Aluno c WHERE c.time = :time ORDER BY c.id", Aluno.class);
        query.setParameter("time", time);
        return query.getResultList();
    }

    @Transactional
    public Aluno update(Aluno candidato) {
        return entityManager.merge(candidato);
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Aluno").executeUpdate();
    }

    public List<Aluno> findSemTime() {
        TypedQuery<Aluno> query = entityManager.createQuery(
                "SELECT c FROM Aluno c WHERE c.time IS NULL ORDER BY c.id", Aluno.class);
        return query.getResultList();
    }
}