package com.sicimike.mayserver.controller;

import com.sicimike.mayserver.base.ApiResponse;
import com.sicimike.mayserver.dto.ClassicDTO;
import com.sicimike.mayserver.dto.LikeForm;
import com.sicimike.mayserver.entity.Classic;
import com.sicimike.mayserver.entity.UserClassic;
import com.sicimike.mayserver.enums.EnumLikeStatus;
import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.service.ClassicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-13 14:02
 */
@RestController
public class ClassicController {

    @Autowired
    private ClassicService classicService;

    /**
     * 获取最新一期
     *
     * @param openid
     * @return
     */
    @GetMapping("/classic/latest")
    public ApiResponse latest(@RequestParam("openid") String openid) {
        Classic classic = classicService.findLatestOne();
        if (classic == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        ClassicDTO classicDTO = new ClassicDTO();
        BeanUtils.copyProperties(classic, classicDTO);
        //第一次进入，尚未授权
        if (StringUtils.isEmpty(openid)) {
            classicDTO.setLikeStatus(EnumLikeStatus.NOT_LIKE.getStatus());
        } else {
            UserClassic userClassic = classicService.findByUserIdAndClassicId(openid, classic.getId());
            if (userClassic != null) {
                classicDTO.setLikeStatus(userClassic.getLikeStatus());
            } else {
                //授权后对该期刊没有点赞信息
                classicDTO.setLikeStatus(EnumLikeStatus.NOT_LIKE.getStatus());
            }
        }
        return ApiResponse.ofSuccess(classicDTO);
    }

    /**
     * 获取上一期
     *
     * @param index
     * @param openid
     * @return
     */
    @GetMapping("/classic/{index}/previous")
    public ApiResponse previous(@PathVariable("index") Integer index,
                                @RequestParam("openid") String openid) {
        Classic classic = classicService.findPreOne(index);
        if (classic == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        ClassicDTO classicDTO = new ClassicDTO();
        BeanUtils.copyProperties(classic, classicDTO);
        UserClassic userClassic = classicService.findByUserIdAndClassicId(openid, classic.getId());
        if (userClassic != null) {
            classicDTO.setLikeStatus(userClassic.getLikeStatus());
        } else {
            //授权后对该期刊没有点赞信息
            classicDTO.setLikeStatus(EnumLikeStatus.NOT_LIKE.getStatus());
        }
        return ApiResponse.ofSuccess(classicDTO);
    }

    /**
     * 获取下一期
     *
     * @param index
     * @param openid
     * @return
     */
    @GetMapping("/classic/{index}/next")
    public ApiResponse next(@PathVariable("index") Integer index,
                            @RequestParam("openid") String openid) {
        Classic classic = classicService.findNextOne(index);
        if (classic == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        ClassicDTO classicDTO = new ClassicDTO();
        BeanUtils.copyProperties(classic, classicDTO);
        UserClassic userClassic = classicService.findByUserIdAndClassicId(openid, classic.getId());
        if (userClassic != null) {
            classicDTO.setLikeStatus(userClassic.getLikeStatus());
        } else {
            //授权后对该期刊没有点赞信息
            classicDTO.setLikeStatus(EnumLikeStatus.NOT_LIKE.getStatus());
        }
        return ApiResponse.ofSuccess(classicDTO);
    }

    /**
     * 点赞
     *
     * @param likeForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/like")
    public ApiResponse like(@RequestBody @Valid LikeForm likeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ApiResponse.ofFail(EnumResponseCode.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        UserClassic userClassic = classicService.findByUserIdAndClassicId(likeForm.getOpenid(), likeForm.getArtId());
        /**
         * user_classic表中没有点赞信息，需要判断新增还是更新
         */
        Boolean isUpdate = true;
        if (userClassic == null) {
            //未找到点赞信息，新增
            isUpdate = false;
        } else {
            if (EnumLikeStatus.LIKE.getStatus().equals(userClassic.getLikeStatus())) {
                return ApiResponse.ofFail(EnumResponseCode.ALREADY_COMPLIMENT);
            }
        }

        classicService.like(likeForm.getOpenid(), likeForm.getArtId(), likeForm.getType(), isUpdate);
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 取消点赞
     *
     * @param likeForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/like/cancel")
    public ApiResponse cancel(@RequestBody @Valid LikeForm likeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ApiResponse.ofFail(EnumResponseCode.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        UserClassic userClassic = classicService.findByUserIdAndClassicId(likeForm.getOpenid(), likeForm.getArtId());
        if (userClassic == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        if (EnumLikeStatus.NOT_LIKE.getStatus().equals(userClassic.getLikeStatus())) {
            return ApiResponse.ofFail(EnumResponseCode.NOT_COMPLIMENT);
        }
        classicService.cancel(likeForm.getOpenid(), likeForm.getArtId(), likeForm.getType());
        return ApiResponse.ofSuccess(null);
    }

    /**
     * 查询某个用户对某个期刊的点赞信息
     *
     * @param type
     * @param id
     * @param openid
     * @return
     */
    @GetMapping("/classic/{type}/{id}/{openid}/favor")
    public ApiResponse favor(@PathVariable("type") Integer type,
                             @PathVariable("id") Long id,
                             @PathVariable("openid") String openid) {
        ClassicDTO classicDTO = classicService.findFavorInfo(id, type);
        if (classicDTO == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        UserClassic userClassic = classicService.findByUserIdAndClassicId(openid, id);
        if (userClassic != null) {
            classicDTO.setLikeStatus(userClassic.getLikeStatus());
        } else {
            //授权后对该期刊没有点赞信息
            classicDTO.setLikeStatus(EnumLikeStatus.NOT_LIKE.getStatus());
        }
        return ApiResponse.ofSuccess(classicDTO);
    }

    /**
     * 查询用户点赞期刊数
     *
     * @param openid
     * @return
     */
    @GetMapping("/classic/favor/count")
    public ApiResponse favorCount(@RequestParam("openid") String openid) {
        Long count = classicService.favorCount(openid, EnumLikeStatus.LIKE.getStatus());
        return ApiResponse.ofSuccess(count);
    }

    /**
     * 查询用户所有点赞期刊
     *
     * @param openid
     * @return
     */
    @GetMapping("/classic/favor")
    public ApiResponse classicFavor(@RequestParam("openid") String openid) {
        List<Classic> classicList = classicService.classicFavor(openid, EnumLikeStatus.LIKE.getStatus());
        List<ClassicDTO> classicDTOList = new ArrayList<>(classicList.size());
        classicList.forEach(classic -> {
            ClassicDTO classicDTO = new ClassicDTO();
            BeanUtils.copyProperties(classic, classicDTO);
            classicDTOList.add(classicDTO);
        });
        return ApiResponse.ofSuccess(classicDTOList);
    }
}
