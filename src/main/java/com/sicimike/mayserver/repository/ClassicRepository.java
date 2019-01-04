package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.dto.ClassicDTO;
import com.sicimike.mayserver.entity.Classic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-13 19:12
 */
public interface ClassicRepository extends CrudRepository<Classic, Long> {

    /**
     * 查找最新一期
     *
     * @return
     */
    @Query("SELECT c FROM Classic c WHERE  c.index = (SELECT MAX(cl.index) FROM Classic cl)")
    Classic findLatestOne();

    /**
     * 查找上一期
     *
     * @param index
     * @return
     */
    @Query("SELECT c FROM Classic c WHERE c.index = :index - 1")
    Classic findPreOne(@Param("index") Integer index);

    /**
     * 查找下一期
     *
     * @param index
     * @return
     */
    @Query("SELECT c FROM Classic c WHERE c.index = :index - 1")
    Classic findNextOne(@Param("index") Integer index);

    /**
     * 点赞
     *
     * @param id
     * @param type
     */
    @Modifying
    @Query("update Classic c set c.favNums = c.favNums + 1 where c.id = :id and c.type = :type")
    void like(@Param("id") Long id, @Param("type") Integer type);

    /**
     * 取消点赞
     *
     * @param id
     * @param type
     */
    @Modifying
    @Query("update Classic c set c.favNums = c.favNums - 1 where c.id = :id and c.type = :type")
    void cancel(@Param("id") Long id, @Param("type") Integer type);

    /**
     * 获取点赞信息
     *
     * @param id
     * @param type
     * @return
     */
    @Query("select new com.sicimike.mayserver.dto.ClassicDTO(c.id, c.favNums) from Classic c where c.id = :id and c.type = :type")
    ClassicDTO findFavorInfo(@Param("id") Long id, @Param("type") Integer type);

    /**
     * 查询用户点赞列表
     * @param openid
     * @param likeStatus
     * @return
     */
    @Query("select c from Classic c, UserClassic uc where c.id = uc.classicId and uc.userOpenid = :openid and uc.likeStatus = :likeStatus")
    List<Classic> findClassicFavor(@Param("openid") String openid, @Param("likeStatus") Integer likeStatus);
}
