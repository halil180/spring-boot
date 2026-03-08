package org.bookstore.customer.repository;

import org.bookstore.common.repository.GenericRepository;
import org.bookstore.customer.entity.Address;
import org.bookstore.customer.entity.CreditCard;
import org.bookstore.customer.entity.Customer;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import static org.bookstore.customer.entity.CreditCardType.MASTER_CARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerRepositoryIT {

	private static final String PERSISTENCE_UNIT = "bookstore";

	private final GenericRepository<Customer, Long> repository = new GenericRepository<>(Customer.class);
	private final EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

	@Test
	public void persistCustomer() {
		Address address = new Address("Street", "City", null, "1000", "Country");
		CreditCard creditCard = new CreditCard(MASTER_CARD, "5400000000000005", 1, 2025);
		Customer customer = new Customer("FirstName", "LastName", "name@example.org", address, creditCard);
		customer = repository.persist(customer);
		assertNotNull(em.find(Customer.class, customer.getId()));
	}

	@Test
	public void findCustomer() {
		assertNotNull(repository.find(1L));
	}

	@Test
	public void findNonExistingCustomer() {
		assertNull(repository.find(0L));
	}

	@Test
	public void updateCustomer() {
		Customer customer = em.find(Customer.class, 1L);
		customer.setAddress(new Address("Street1", "City1", "Province1", "1001", "Country1"));
		repository.update(customer);
		assertEquals("Province1", em.find(Customer.class, 1L).getAddress().getStateProvince());
	}

	@Test
	public void deleteCustomer() {
		repository.delete(2L);
		assertNull(em.find(Customer.class, 2L));
	}
}
