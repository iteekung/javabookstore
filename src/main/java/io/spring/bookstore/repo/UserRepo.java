package io.spring.bookstore.repo;

import io.spring.bookstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
