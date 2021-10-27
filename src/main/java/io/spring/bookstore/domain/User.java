package io.spring.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Data
public class User {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "orderBook",
        joinColumns = {@JoinColumn(name = "uid")},
        inverseJoinColumns = {@JoinColumn(name = "bid")})
    @JsonIgnoreProperties("dataUsers")
    private List<Book> dataBooks = new ArrayList<>();

//    public User(String name, String sname, String username, String password, Date date) {
//        this.setName(name);
//        this.setSurname(sname);
//        this.setUsername(username);
//        this.setPassword(password);
//        this.setDateOfBirth(date);
//    }

    public Double orderBookSumPrice() {
        return this.getDataBooks().stream().map(x -> x.getPrice()).reduce(0.0, (a, b) -> a + b);
    }
}
