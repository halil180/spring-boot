package org.bookstore.common.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class GenericRepository<T, ID> {

    private static final String PERSISTENCE_UNIT = "bookstore";

    private final Class<T> entityClass;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T persist(T entity) {
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("Failed to create " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    public T find(ID id) {
        try {
            return em.find(entityClass, id);
        } catch (RuntimeException e) {
            throw new PersistenceException(
                    "Failed to find " + entityClass.getSimpleName() + " by id: " + e.getMessage(),
                    e
            );
        }
    }

    public T update(T entity) {
        em.getTransaction().begin();
        try {
            T updatedEntity = em.merge(entity);
            em.getTransaction().commit();
            return updatedEntity;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("Failed to update " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    public void delete(ID id) {
        em.getTransaction().begin();
        try {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new PersistenceException(entityClass.getSimpleName() + " with id " + id + " not found");
            }
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("Failed to delete " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }
}
