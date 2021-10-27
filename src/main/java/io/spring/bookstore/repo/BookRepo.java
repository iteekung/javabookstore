package io.spring.bookstore.repo;

import io.spring.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByIsRecommendedDesc();
}
