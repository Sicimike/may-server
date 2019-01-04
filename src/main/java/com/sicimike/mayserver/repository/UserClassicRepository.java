package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.entity.UserClassic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author sicimike
 * @create 2018-12-27 16:44
 */
public interface UserClassicRepository extends CrudRepository<UserClassic, Long> {

    /**
     * 查找用户对单个期刊的点赞信息
     * @param openid
     * @param classicId
     * @return
     */
    UserClassic findByUserOpenidAndClassicId(String openid, Long classicId);


    /**
     * 点赞时，更新点赞状态
     * @param openid
     * @param artId
     */
    @Modifying
    @Query("update UserClassic uc set uc.likeStatus = 1 where uc.userOpenid = :openid and uc.classicId = :artId")
    void like(@Param("openid") String openid, @Param("artId") Long artId);

    /**
     * 取消点赞时，更新点赞状态
     * @param openid
     * @param artId
     */
    @Modifying
    @Query("update UserClassic uc set uc.likeStatus = 0 where uc.userOpenid = :openid and uc.classicId = :artId")
    void cancel(@Param("openid") String openid, @Param("artId") Long artId);

    /**
     * 根据用户openid和likeStatus统计
     * @param openid
     * @param likeStatus
     * @return
     */
    Long countByUserOpenidAndLikeStatus(String openid, Integer likeStatus);


}
