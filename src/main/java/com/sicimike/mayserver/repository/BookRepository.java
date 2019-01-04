package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.entity.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author sicimike
 * @create 2018-12-18 19:00
 */
public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    /**
     * 查询热门书籍
     *
     * @param favNumber
     * @return
     */
    @Query("select new com.sicimike.mayserver.entity.Book(b.id,b.title, b.author, b.image, b.favNums) from Book b where b.favNums >= :favNumber")
    Iterable<Book> findHotList(@Param("favNumber") Integer favNumber);

    /**
     * 根据书籍id获取点赞信息
     *
     * @param bookId
     * @return
     */
    @Query("select new com.sicimike.mayserver.entity.Book(b.id, b.favNums, b.likeStatus) from Book b where b.id = :bookId")
    Book findBookFavor(@Param("bookId") Long bookId);


}
