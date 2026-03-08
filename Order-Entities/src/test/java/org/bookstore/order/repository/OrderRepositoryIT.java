package org.bookstore.order.repository;

import org.bookstore.catalog.entity.Book;
import org.bookstore.common.repository.GenericRepository;
import org.bookstore.customer.entity.Customer;
import org.bookstore.order.entity.Order;
import org.bookstore.order.entity.OrderItem;
import org.bookstore.order.entity.OrderStatus;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderRepositoryIT {

	private static final String PERSISTENCE_UNIT = "bookstore";

	private static final GenericRepository<Order, Long> repository = new GenericRepository<>(Order.class);
	private final EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

	@Test
	public void persistOrder() {
		Customer customer = em.find(Customer.class, 1L);
		Book book = em.find(Book.class, "1000000001");
		List<OrderItem> items = List.of(new OrderItem(book, 1));
		Order order = new Order(LocalDateTime.now(), book.getPrice(), OrderStatus.ACCEPTED, customer, items);
		order = repository.persist(order);
		assertNotNull(em.find(Order.class, order.getId()));
	}

	@Test
	public void findOrder() {
		assertNotNull(repository.find(1L));
	}

	@Test
	public void findNonExistingOrder() {
		assertNull(repository.find(0L));
	}

	@Test
	public void updateOrder() {
		Order order = em.find(Order.class, 1L);
		order.setStatus(OrderStatus.CANCELED);
		order = repository.update(order);
		assertEquals(OrderStatus.CANCELED, em.find(Order.class, order.getId()).getStatus());
	}

	@Test
	public void deleteOrder() {
		repository.delete(2L);
		assertNull(em.find(Order.class, 2L));
	}
}
