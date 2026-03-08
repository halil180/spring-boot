package org.bookstore.catalog.repository;

import org.bookstore.catalog.entity.Book;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookRepositoryIT {

    // normaleerweise wurde man eine testdatenbank am laufen haben und nicht eine echte datenbank
    private static final String PERSISTENCE_UNIT = "bookstore";

    private final BookRepository repository = new BookRepository();
    private final EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

    @Test
    public void persistBook() {
        Book book = new Book("1000000000", "Title", "Authors", "Publisher", new BigDecimal("10.0"));
        repository.persist(book);
        assertNotNull(em.find(Book.class, "1000000000"));
    }

    @Test
    public void persistBookWithMissingIsbn() {
        Book book = new Book(null, "Title", "Authors", "Publisher", new BigDecimal("10.0"));
        assertThrows(PersistenceException.class, () -> repository.persist(book));
    }

    @Test
    public void persistBookWithInvalidIsbn() {
        Book book = new Book("1000000000X", "Title", "Authors", "Publisher", new BigDecimal("10.0"));
        assertThrows(PersistenceException.class, () -> repository.persist(book));
    }

    @Test
    public void persistExistingBook() {
        Book book = new Book("1000000001", "Title1", "Authors1", "Publisher1", new BigDecimal("10.0"));
        assertThrows(PersistenceException.class, () -> repository.persist(book));
    }

    @Test
    public void findBook() {
        assertNotNull(repository.find("1000000001"));
    }

    @Test
    public void findNonExistingBook() {
        assertNull(repository.find("100000000X"));
    }

    @Test
    public void updateBook() {
        Book book = em.find(Book.class, "1000000001");
        book.setSubtitle("Subtitle1");
        repository.update(book);
        assertEquals("Subtitle1", em.find(Book.class, "1000000001").getSubtitle());
    }

    @Test
    public void deleteBook() {
        repository.delete("1000000002");
        assertNull(em.find(Book.class, "1000000002"));
    }
}
