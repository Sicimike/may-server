package com.sicimike.mayserver.service;

/**
 * 用户服务
 * @author sicimike
 * @create 2018-12-26 16:46
 */
public interface UserService {

    /**
     * 利用微信提供的API获取openid、accessKey等
     * @param code
     * @return
     */
    String login(String code);
}
