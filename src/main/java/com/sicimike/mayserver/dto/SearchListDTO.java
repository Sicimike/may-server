package com.sicimike.mayserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 搜索返回DTO
 * @author sicimike
 * @create 2018-12-24 16:33
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchListDTO<T> {

    /**
     * 起始记录下标
     */
    private Integer start;
    /**
     * 返回记录数
     */
    private Integer count;
    /**
     * 总数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List<T> data;
}
