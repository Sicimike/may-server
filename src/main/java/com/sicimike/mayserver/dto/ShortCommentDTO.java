package com.sicimike.mayserver.dto;

import lombok.Data;

/**
 * 短评DTO
 * @author sicimike
 * @create 2018-12-19 10:53
 */
@Data
public class ShortCommentDTO {

    private Long id;

    private Long bookId;

    private String content;

    private Integer nums;

}
