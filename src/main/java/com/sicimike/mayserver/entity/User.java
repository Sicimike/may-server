package com.sicimike.mayserver.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户相关操作
 * @author sicimike
 * @create 2018-12-27 10:30
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String openid;

    @Column(name = "session_key")
    private String sessionKey;
}
