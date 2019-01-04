package com.sicimike.mayserver.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 搜索表单
 * @author sicimike
 * @create 2018-12-20 18:49
 */
@Data
public class SearchForm {

    /**
     * 起始记录下标
     */
    private Integer start = 0;

    /**
     * page size
     */
    private Integer size = 20;

    @NotNull(message = "搜索内容不能为空")
    private String content;

}
