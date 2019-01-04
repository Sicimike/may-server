package com.sicimike.mayserver.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sicimike
 * @create 2018-12-18 18:54
 */
@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String category;

    private String binding;

    private String title;

    private String subtitle;

    private String author;

    private String summary;

    private String image;

    private String publisher;

    private String translator;

    private Double price;

    private Integer pages;

    private Date pubdate;

    @Column(name = "fav_nums")
    private Integer favNums;

    @Column(name = "like_status")
    private Integer likeStatus;

    public Book() {
    }

    public Book(Long id, Integer favNums, Integer likeStatus) {
        this.id = id;
        this.favNums = favNums;
        this.likeStatus = likeStatus;
    }

    public Book(Long id, String title, String author, String image, Integer favNums) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = image;
        this.favNums = favNums;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
