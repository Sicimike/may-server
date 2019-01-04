package com.sicimike.mayserver.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sicimike
 * @create 2018-12-13 19:08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassicDTO {

    private Long id;

    private String title;

    private Integer index;

    private String image;

    private String content;

    private Integer type;

    private Date pubdata;

    @JsonProperty(value = "fav_nums")
    private Integer favNums;

    @JsonProperty(value = "like_status")
    private Integer likeStatus;

    private String url;

    public ClassicDTO(){

    }

    public ClassicDTO(Long id, Integer favNums) {
        this.id = id;
        this.favNums = favNums;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }

}
