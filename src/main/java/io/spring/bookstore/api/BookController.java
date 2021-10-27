package io.spring.bookstore.api;

import io.spring.bookstore.domain.Book;
import io.spring.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Map<String, List<Book>>>getBook() {
        Map<String, List<Book>> books = new HashMap<>();
        books.put("books", bookService.getBooks());
        return ResponseEntity.ok().body(books);
    }
}
