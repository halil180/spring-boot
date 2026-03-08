package org.bookstore.catalog.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.bookstore.catalog.entity.Book;

public class BookRepository implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory;

    public BookRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bookstore");
    }

    public void persist(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    public Book find(String isbn) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Book.class, isbn);
        } finally {
            entityManager.close();
        }
    }

    public Book update(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Book updatedBook = entityManager.merge(book);
            transaction.commit();
            return updatedBook;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    public void delete(String isbn) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Book book = entityManager.find(Book.class, isbn);
            if (book != null) {
                entityManager.remove(book);
            }
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
