package org.bookstore.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
public class Book {

    @Id
    @Column(name = "ISBN", length = 10, nullable = false)
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SUBTITLE")
    private String subtitle;

    @Column(name = "AUTHORS", nullable = false)
    private String authors;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "PUBLICATION_YEAR")
    private Integer publicationYear;

    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "PRICE", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    public Book() {
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Book(String isbn, String title, String authors, String publisher, BigDecimal price) {
        this(isbn, title, null, authors, publisher, null, null, null, null, price);
    }

    public Book(String isbn, String title, String subtitle, String authors, String publisher,
                Integer publicationYear, Integer numberOfPages, String description,
                String imageUrl, BigDecimal price) {
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
