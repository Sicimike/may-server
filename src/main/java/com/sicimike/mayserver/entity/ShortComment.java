package com.sicimike.mayserver.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 短评
 * @author sicimike
 * @create 2018-12-19 10:14
 */
@Data
@Entity
@Table(name = "short_comment")
public class ShortComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    private String content;

    private Integer nums;
}
