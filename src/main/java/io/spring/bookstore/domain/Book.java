package io.spring.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private Double price;
    private Boolean isRecommended;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "dataBooks")
    @JsonIgnoreProperties("dataBooks")
    private List<User> dataUsers = new ArrayList<>();

//    public Book(String name, String author, double price, boolean isRecommended) {
//        this.setName(name);
//        this.setAuthor(author);
//        this.setPrice(price);
//        this.setIsRecommended(isRecommended);
//    }
}
