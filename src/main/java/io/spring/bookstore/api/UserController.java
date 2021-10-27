package io.spring.bookstore.api;

import io.spring.bookstore.domain.User;
import io.spring.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

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
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/orders")
    public ResponseEntity<Map<String, Double>> userOrderBook(@RequestBody Map<String, List> payload) {
        System.out.println("payload " + payload);
        User user = userService.getUser("foo");
        Map<String, Double> sumPrice = new HashMap<>();
        sumPrice.put("price", user.orderBookSumPrice());
        return ResponseEntity.ok().body(sumPrice);
    }
}
