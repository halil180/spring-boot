package org.bookstore.order.entity;

import jakarta.persistence.*;
import org.bookstore.catalog.entity.Book;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	private Book book;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	public OrderItem() {
	}

	public OrderItem(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
