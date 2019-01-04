package com.sicimike.mayserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author sicimike
 * @create 2018-12-18 19:07
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

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

    private Integer favNums;

    private Integer likeStatus;
}
