package com.sicimike.mayserver.service;

import com.sicimike.mayserver.entity.Book;
import com.sicimike.mayserver.entity.HotWord;
import com.sicimike.mayserver.entity.ShortComment;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-18 19:01
 */
public interface BookService {

    /**
     * 查询热门书籍
     * @return
     */
    List<Book> findHotList();

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    Book findDetail(Long id);

    /**
     * 根据书籍id获取短评列表
     * @param bookId
     * @return
     */
    List<ShortComment> findShortComment(Long bookId);

    /**
     * 根据书籍id获取点赞情况
     * @param bookId
     * @return
     */
    Book findBookFavor(Long bookId);

    /**
     * 搜索热词列表
     * @return
     */
    List<HotWord> findHotWordList();

    /**
     * 根据条件搜索书籍列表
     * @param content
     * @param start
     * @param size
     * @return
     */
    Page<Book> findBookList(String content, Integer start, Integer size);
}
