package com.sicimike.mayserver.service;

import com.sicimike.mayserver.dto.ClassicDTO;
import com.sicimike.mayserver.entity.Classic;
import com.sicimike.mayserver.entity.UserClassic;

import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-13 18:57
 */
public interface ClassicService {

    /**
     * 查找最新一期
     * @return
     */
    Classic findLatestOne();

    /**
     * 查找上一期
     * @param index
     * @return
     */
    Classic findPreOne(Integer index);

    /**
     * 查找下一期
     * @param index
     * @return
     */
    Classic findNextOne(Integer index);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Classic findById(Long id);

    /**
     * 点赞
     * @param openid
     * @param id
     * @param type
     * @param isUpdate
     */
    void like(String openid, Long id, Integer type, Boolean isUpdate);

    /**
     * 取消点赞
     * @param openid
     * @param id
     * @param type
     */
    void cancel(String openid, Long id, Integer type);

    /**
     * 获取点赞信息
     * @param id
     * @param type
     * @return
     */
    ClassicDTO findFavorInfo(Long id, Integer type);

    /**
     * 查找单个用户对单个期刊的点赞状态
     * @param openid
     * @param classicId
     * @return
     */
    UserClassic findByUserIdAndClassicId(String openid, Long classicId);

    /**
     * 统计某个用户的点赞数量
     * @param openid
     * @param status
     * @return
     */
    Long favorCount(String openid, Integer status);

    /**
     * 查询用户点赞列表
     * @param openid
     * @param status
     * @return
     */
    List<Classic> classicFavor(String openid, Integer status);
}
