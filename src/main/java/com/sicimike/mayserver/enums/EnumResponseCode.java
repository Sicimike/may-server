package com.sicimike.mayserver.enums;

import lombok.Getter;

/**
 * 返回码
 * @author sicimike
 * @create 2018-12-13 14:39
 */
@Getter
public enum EnumResponseCode {

    //通用类型
    SUCCESS(1, "成功"),
    PARAM_ERROR(1000, "参数错误"),
    JSON_ERROR(1001, "JSON格式错误"),
    RESOURCE_NOT_FOUND(1002, "找不到资源"),
    UNKNOWN_ERROR(1003, "未知错误"),
    DISABLE_ACCESS(1004, "禁止访问"),
    APPKEY_ERROR(1005, "不正确的开发者key"),
    SERVER_INTERNAL_ERROR(1006, "服务器内部错误"),

    //点赞类型
    ALREADY_COMPLIMENT(2000, "你已经点过赞"),
    NOT_COMPLIMENT(2001, "你还没点过赞"),

    //期刊类型
    CONTENT_NOT_EXIST(3000, "期刊不存在")
    ;


    private Integer code;
    private String message;

    EnumResponseCode(){

    }

    EnumResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
