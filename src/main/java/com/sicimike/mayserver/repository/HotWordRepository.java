package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.entity.HotWord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-20 16:30
 */
public interface HotWordRepository extends PagingAndSortingRepository<HotWord, Long>{

    /**
     * 查询热门搜索关键字
     * @param pageable
     * @return
     */
    @Query("select h from HotWord h order by h.searchCount desc")
    List<HotWord> findHotWordList(Pageable pageable);

    /**
     * 根据字段查找
     * @param word
     * @return
     */
    HotWord findByWord(String word);

    /**
     * 更新搜索热度
     * @param searchCount
     * @param id
     */
    @Modifying
    @Query("update HotWord hw set hw.searchCount = :searchCount where hw.id = :id")
    void update(@Param("searchCount") Integer searchCount, @Param("id") Long id);
}
