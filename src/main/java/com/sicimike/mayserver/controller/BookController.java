package com.sicimike.mayserver.controller;

import com.sicimike.mayserver.base.ApiResponse;
import com.sicimike.mayserver.dto.*;
import com.sicimike.mayserver.entity.Book;
import com.sicimike.mayserver.entity.HotWord;
import com.sicimike.mayserver.entity.ShortComment;
import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-18 19:05
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/hot/list")
    public ApiResponse list() {
        List<Book> bookList = bookService.findHotList();
        List<BookDTO> bookDTOList = new ArrayList<>(bookList.size());
        bookList.forEach(book -> {
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            bookDTOList.add(bookDTO);
        });
        return ApiResponse.ofSuccess(bookDTOList);
    }

    @GetMapping("/book/{id}/detail")
    public ApiResponse detail(@PathVariable("id") Long id) {
        Book book = bookService.findDetail(id);
        if (book == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return ApiResponse.ofSuccess(bookDTO);
    }

    @GetMapping("/book/{bookId}/short/comment")
    public ApiResponse shortComment(@PathVariable("bookId") Long bookId) {
        List<ShortComment> shortCommentList = bookService.findShortComment(bookId);
        List<ShortCommentDTO> shortCommentDTOList = new ArrayList<>(shortCommentList.size());
        shortCommentList.forEach(shortComment -> {
            ShortCommentDTO shortCommentDTO = new ShortCommentDTO();
            BeanUtils.copyProperties(shortComment, shortCommentDTO);
            shortCommentDTOList.add(shortCommentDTO);
        });
        return ApiResponse.ofSuccess(shortCommentDTOList);
    }

    @GetMapping("/book/{bookId}/favor")
    public ApiResponse favor(@PathVariable("bookId") Long bookId) {
        Book book = bookService.findBookFavor(bookId);
        if (book == null) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return ApiResponse.ofSuccess(bookDTO);
    }

    @GetMapping("/book/hot/words")
    public ApiResponse hotWords() {
        List<HotWord> hotWordList = bookService.findHotWordList();
        if (CollectionUtils.isEmpty(hotWordList)) {
            return ApiResponse.ofFail(EnumResponseCode.RESOURCE_NOT_FOUND);
        }
        List<HotWordDTO> hotWordDTOList = new ArrayList<>(hotWordList.size());
        hotWordList.forEach(hotWord -> {
            HotWordDTO hotWordDTO = new HotWordDTO();
            BeanUtils.copyProperties(hotWord, hotWordDTO);
            hotWordDTOList.add(hotWordDTO);
        });
        return ApiResponse.ofSuccess(hotWordDTOList);
    }

    @PostMapping("/book/search")
    public ApiResponse search(@RequestBody @Valid SearchForm searchForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ApiResponse.ofFail(EnumResponseCode.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Page<Book> bookPage = bookService.findBookList(searchForm.getContent(), searchForm.getStart(), searchForm.getSize());
        List<Book> bookList = bookPage.getContent();
        List<BookDTO> bookDTOList = new ArrayList<>(bookList.size());
        bookList.forEach(book -> {
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            bookDTOList.add(bookDTO);
        });
        SearchListDTO<BookDTO> searchListDTO = new SearchListDTO<>();
        searchListDTO.setTotal(bookPage.getTotalElements());
        searchListDTO.setStart(searchForm.getStart());
        searchListDTO.setCount(bookPage.getContent().size());
        searchListDTO.setData(bookDTOList);
        return ApiResponse.ofSuccess(searchListDTO);
    }


}
