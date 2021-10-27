package io.spring.bookstore.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.bookstore.domain.Book;
import io.spring.bookstore.domain.User;
import io.spring.bookstore.service.BookService;
import io.spring.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @GetMapping("/users")
    public ResponseEntity<Map> getUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user = userService.getUser((String) usernamePasswordAuthenticationToken.getPrincipal());
        List books = user.getDataBooks().stream().map(x -> x.getId()).collect(Collectors.toList());
        Map resp = user.userInfo();
        resp.put("books", books.toString());
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping("/users")
    public ResponseEntity<Map> addUser(@RequestBody User user) {
        log.info(String.valueOf(user));
        userService.saveUser(user);
        return ResponseEntity.ok().body(user.userInfo());
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        User user = userService.getUser((String) usernamePasswordAuthenticationToken.getPrincipal());
        log.info("username: {} ", user.getUsername());
        user.getDataBooks().removeAll(user.getDataBooks());
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/orders")
    public ResponseEntity<Map<String, Double>> userOrderBook(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, @RequestBody Map<String, String> payload) throws JsonProcessingException {
        User user = userService.getUser((String) usernamePasswordAuthenticationToken.getPrincipal());
        System.out.println("payload order" + payload.get("order"));
        ObjectMapper mapper = new ObjectMapper();
        ArrayList books = mapper.readValue(payload.get("order"), ArrayList.class);
        for (int i = 0; i < books.size(); i++) {
            log.info("book id : {}", books.get(i));
            Book book = bookService.getBook((Integer) books.get(i));
            user.getDataBooks().add(book);
            userService.saveUser(user);
        }

        Map<String, Double> sumPrice = new HashMap<>();
        sumPrice.put("price", user.orderBookSumPrice());
        return ResponseEntity.ok().body(sumPrice);
    }
}
