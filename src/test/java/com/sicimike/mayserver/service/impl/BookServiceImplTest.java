package com.sicimike.mayserver.service.impl;

import com.sicimike.mayserver.MayServerApplicationTests;
import com.sicimike.mayserver.entity.Book;
import com.sicimike.mayserver.entity.HotWord;
import com.sicimike.mayserver.entity.ShortComment;
import com.sicimike.mayserver.service.BookService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
public class BookServiceImplTest extends MayServerApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    public void findHotList() throws Exception {
    }

    @Test
    public void findDetail() throws Exception {
    }

    @Test
    public void findShortComment() throws Exception {
        List<ShortComment> shortCommentList = bookService.findShortComment(1L);
        Assert.notEmpty(shortCommentList, "fail");
    }

    @Test
    public void findBookFavor() throws Exception {
        Book book = bookService.findBookFavor(1L);
        Assert.notNull(book, "fail");
    }

    @Test
    public void findHotWordList() throws Exception {
        List<HotWord> hotWordList = bookService.findHotWordList();
        Assert.notNull(hotWordList, "fail");
    }

}