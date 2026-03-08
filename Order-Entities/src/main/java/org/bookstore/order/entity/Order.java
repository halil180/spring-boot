package org.bookstore.order.entity;

import jakarta.persistence.*;
import org.bookstore.customer.entity.Address;
import org.bookstore.customer.entity.CreditCard;
import org.bookstore.customer.entity.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "BOOK_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ORDER_DATE", nullable = false)
	private LocalDateTime date;

	@Column(name = "AMOUNT", nullable = false, precision = 7, scale = 2)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private OrderStatus status;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;

	@Embedded
	private Address address;

	@Embedded
	private CreditCard creditCard;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderItem> items;

	public Order() {
	}

	public Order(LocalDateTime date, BigDecimal amount, OrderStatus status, Customer customer, List<OrderItem> items) {
		this.date = date;
		this.amount = amount;
		this.status = status;
		this.customer = customer;
		this.address = customer.getAddress();
		this.creditCard = customer.getCreditCard();
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
