package com.sicimike.mayserver.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * user-classic关联表
 * @author sicimike
 * @create 2018-12-27 16:40
 */
@Data
@Entity
@Table(name = "user_classic")
public class UserClassic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_openid")
    private String userOpenid;

    @Column(name = "classic_id")
    private Long classicId;

    @Column(name = "like_status")
    private Integer likeStatus;
}
