package com.sicimike.mayserver.enums;

import lombok.Getter;

/**
 * 点赞状态
 * @author sicimike
 * @create 2018-12-14 10:43
 */
@Getter
public enum EnumLikeStatus {

    //
    LIKE(1, "已赞"),
    NOT_LIKE(0, "未赞");

    private Integer status;
    private String message;

    EnumLikeStatus(){

    }

    EnumLikeStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
