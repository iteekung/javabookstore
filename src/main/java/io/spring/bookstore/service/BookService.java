package io.spring.bookstore.service;

import io.spring.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book saveBook(Book book);
}
