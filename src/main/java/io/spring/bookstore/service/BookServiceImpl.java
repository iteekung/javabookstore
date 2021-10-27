package io.spring.bookstore.service;

import io.spring.bookstore.domain.Book;
import io.spring.bookstore.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepo bookRepo;

    @Override
    public Book getBook(Integer id) {
        return bookRepo.getById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepo.findAllByOrderByIsRecommendedDesc();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

}
