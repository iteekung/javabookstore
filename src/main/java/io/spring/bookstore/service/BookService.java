package io.spring.bookstore.service;

import io.spring.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    Book getBook(Integer id);
    List<Book> getBooks();
    Book saveBook(Book book);
}
