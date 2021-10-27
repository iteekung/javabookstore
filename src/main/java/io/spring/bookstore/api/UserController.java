package io.spring.bookstore.api;

import io.spring.bookstore.domain.Book;
import io.spring.bookstore.domain.User;
import io.spring.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        Double sumPrice = user.orderBookSumPrice();
        System.out.println("sum price : " + sumPrice);
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/orders")
    public ResponseEntity<Map<String, Double>> userOrderBook(@RequestBody Map<String, List> payload) {
        System.out.println("payload " + payload);
        User user = userService.getUser(1);
        Map<String, Double> sumPrice = new HashMap<>();
        sumPrice.put("price", user.orderBookSumPrice());
        return ResponseEntity.ok().body(sumPrice);
    }
}
