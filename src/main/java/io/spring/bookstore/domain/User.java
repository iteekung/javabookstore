package io.spring.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity @Data
public class User {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Date date_of_birth;

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
//        this.getDate_of_birth(date);
//    }

    public Double orderBookSumPrice() {
        return this.getDataBooks().stream().map(x -> x.getPrice()).reduce(0.0, (a, b) -> a + b);
    }

    public Map userInfo() {
        Map<String, String> resp = new HashMap<>();
        resp.put("name", this.getName());
        resp.put("surname", this.getSurname());
        resp.put("date_of_birth", new SimpleDateFormat("dd/MM/yyyy").format(this.getDate_of_birth()));
        return resp;
    }
}
