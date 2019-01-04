package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.entity.ShortComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author sicimike
 * @create 2018-12-19 10:19
 */
public interface ShortCommentRepository extends CrudRepository<ShortComment, Long> {

    /**
     * 根据bookId查询列表
     * @param boodId
     * @return
     */
    List<ShortComment> findByBookIdOrderByNumsDesc(Long boodId);

}
