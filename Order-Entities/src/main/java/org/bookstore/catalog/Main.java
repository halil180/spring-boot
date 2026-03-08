package org.bookstore.catalog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.bookstore.catalog.entity.Book;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        Book book = new Book(
//                "0764574831",
//                "unProfessional Java Development with the Spring Framework",
//                null,
//                "Rod Johnson, Juergen Hoeller",
//                "Wrox",
//                2005,
//                656,
//                "Comprehensive guide to Spring-based Java development.",
//                null,
//                new BigDecimal("49.99")
//        );
//        em.persist(book);

        var tables = em.createNativeQuery("""
    select table_name
    from information_schema.tables
    where table_schema='public'
    order by table_name
""").getResultList();

        System.out.println("Tables: " + tables);
        em.getTransaction().commit();
        emf.close();
    //    System.out.println("Book with ISBN " + book.getIsbn() + " persisted");
    }
}
