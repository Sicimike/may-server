package com.sicimike.mayserver.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 点赞form
 * @author sicimike
 * @create 2018-12-14 14:14
 */
@Data
public class LikeForm {

    @NotNull(message = "id不能为空")
    private Long artId;

    @NotNull(message = "type不能为空")
    private Integer type;

    @NotNull(message = "openid不能为空")
    private String openid;
}
