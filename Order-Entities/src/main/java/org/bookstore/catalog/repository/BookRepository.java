package org.bookstore.catalog.repository;

import jakarta.persistence.*;
import org.bookstore.catalog.entity.Book;

public class BookRepository {

    private static final String PERSISTENCE_UNIT = "bookstore";
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();

    public Book persist(Book book) {
        em.getTransaction().begin();
        try {
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenceException("Failed to create book: " + e.getMessage(), e);
        }
    }

    public Book find(String isbn) {
        try {
            return em.find(Book.class, isbn);
        } catch (RuntimeException e) {
            throw new PersistenceException("Failed to find book by ISBN: " + e.getMessage(), e);
        }
    }

    public Book update(Book book) {
        em.getTransaction().begin();
        try {
            book = em.merge(book);
            em.getTransaction().commit();
            return book;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenceException("Failed to update book: " + e.getMessage(), e);
        }
    }

    public void delete(String isbn) {
        em.getTransaction().begin();
        try {
            Book book = em.find(Book.class, isbn);
            if (book != null) {
                em.remove(book);
                em.getTransaction().commit();
            } else {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw new PersistenceException("Book with ISBN " + isbn + " not found");
            }
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenceException("Failed to delete book: " + e.getMessage(), e);
        }
    }
}
