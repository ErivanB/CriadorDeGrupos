package br.edu.ifpb.CriadorDeGrupos.dao;

import br.edu.ifpb.CriadorDeGrupos.model.Grupo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GrupoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Grupo save(Grupo time) {
        if (time.getId_grupo() == null) {
            entityManager.persist(time);
            return time;
        } else {
            return entityManager.merge(time);
        }
    }

    public Grupo findById(Long id) {
        return entityManager.find(Grupo.class, id);
    }

    public List<Grupo> findAll() {
        TypedQuery<Grupo> query = entityManager.createQuery(
                "SELECT t FROM Grupo t ORDER BY t.id_grupo", Grupo.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Grupo time = findById(id);
        if (time != null) {
            entityManager.remove(time);
        }
    }

    @Transactional
    public Grupo update(Grupo time) {
        return entityManager.merge(time);
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Grupo").executeUpdate();
    }

    public Grupo findByNome(String nome) {
        TypedQuery<Grupo> query = entityManager.createQuery(
                "SELECT t FROM Grupo t WHERE t.nome = :nome", Grupo.class);
        query.setParameter("nome", nome);

        List<Grupo> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
