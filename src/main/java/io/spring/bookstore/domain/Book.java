package io.spring.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map bookInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("id", this.getId().toString());
        map.put("name", this.getName());
        map.put("author", this.getAuthor());
        map.put("price", this.getPrice().toString());
        map.put("is_recommended", this.getIsRecommended().toString());
        return map;
    }
}
