package com.sicimike.mayserver.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 热门搜索
 * @author sicimike
 * @create 2018-12-20 16:26
 */
@Data
@Entity
@Table(name = "hot_word")
public class HotWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @Column(name = "search_count")
    private Integer searchCount;
}
