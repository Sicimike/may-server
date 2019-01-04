package com.sicimike.mayserver.controller;

import com.alibaba.fastjson.JSON;
import com.sicimike.mayserver.base.ApiResponse;
import com.sicimike.mayserver.dto.UserDTO;
import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关操作
 *
 * @author sicimike
 * @create 2018-12-26 16:18
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ApiResponse login(@RequestParam("code") String code) {

        String result = userService.login(code);
        if(result == null){
            return ApiResponse.ofFail(EnumResponseCode.SERVER_INTERNAL_ERROR);
        }

        UserDTO userDTO = JSON.parseObject(result, UserDTO.class);

        return ApiResponse.ofSuccess(userDTO);
    }

}
