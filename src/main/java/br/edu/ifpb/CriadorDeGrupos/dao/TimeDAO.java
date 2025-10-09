package br.edu.ifpb.CriadorDeGrupos.dao;


import com.divisaotimes.model.Time;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Time save(Time time) {
        if (time.getId() == null) {
            entityManager.persist(time);
            return time;
        } else {
            return entityManager.merge(time);
        }
    }

    public Time findById(Long id) {
        return entityManager.find(Time.class, id);
    }

    public List<Time> findAll() {
        TypedQuery<Time> query = entityManager.createQuery(
                "SELECT t FROM Time t", Time.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Time time = findById(id);
        if (time != null) {
            entityManager.remove(time);
        }
    }

    @Transactional
    public Time update(Time time) {
        return entityManager.merge(time);
    }
}
