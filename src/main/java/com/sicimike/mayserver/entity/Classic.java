package com.sicimike.mayserver.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sicimike
 * @create 2018-12-13 18:58
 */
@Data
@Entity
@Table(name = "classic")
public class Classic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer index;

    private String image;

    private String content;

    private Integer type;

    private Date pubdata;

    @Column(name = "fav_nums")
    private Integer favNums;

    private String url;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }


}
