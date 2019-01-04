package com.sicimike.mayserver.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author sicimike
 * @create 2018-12-20 16:59
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotWordDTO {

    private String word;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
