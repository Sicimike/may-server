package com.sicimike.mayserver.service.impl;

import com.sicimike.mayserver.entity.Book;
import com.sicimike.mayserver.entity.HotWord;
import com.sicimike.mayserver.entity.ShortComment;
import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.exception.ResourceNotFoundException;
import com.sicimike.mayserver.repository.BookRepository;
import com.sicimike.mayserver.repository.HotWordRepository;
import com.sicimike.mayserver.repository.ShortCommentRepository;
import com.sicimike.mayserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sicimike
 * @create 2018-12-18 19:02
 */
@Service
public class BookServiceImpl implements BookService {

    @Value("${book.fav.number}")
    private Integer favNumber;

    @Value("${book.hot.word}")
    private Integer hotWord;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShortCommentRepository shortCommentRepository;

    @Autowired
    private HotWordRepository hotWordRepository;

    @Override
    public List<Book> findHotList() {
        List<Book> bookList = new ArrayList<>();
        Iterable<Book> iterable = bookRepository.findHotList(favNumber);
        iterable.forEach(book -> {
            bookList.add(book);
        });
        return bookList;
    }

    @Override
    public Book findDetail(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(EnumResponseCode.RESOURCE_NOT_FOUND.getMessage());
        }
        Book book = optional.get();
        return book;
    }

    @Override
    public List<ShortComment> findShortComment(Long bookId) {
        return shortCommentRepository.findByBookIdOrderByNumsDesc(bookId);
    }

    @Override
    public Book findBookFavor(Long bookId) {
        return bookRepository.findBookFavor(bookId);
    }

    @Override
    public List<HotWord> findHotWordList() {
        Pageable pageable = PageRequest.of(0, hotWord);
        return hotWordRepository.findHotWordList(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Book> findBookList(String content, Integer start, Integer size) {
        Pageable pageable = PageRequest.of(start, size);

        Specification<Book> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + content + "%");
            predicate = criteriaBuilder.or(predicate, criteriaBuilder.like(root.get("author"), "%" + content + "%"));
            return predicate;
        };
        Page<Book> page = bookRepository.findAll(specification, pageable);
        /**
         * 热词不存在则加入，存在则更新热度
         */
        HotWord hotWord = hotWordRepository.findByWord(content);
        if (hotWord == null) {
            hotWord = new HotWord();
            hotWord.setWord(content);
            hotWord.setSearchCount(1);
            hotWordRepository.save(hotWord);
        } else {
            hotWordRepository.update(hotWord.getSearchCount() + 1, hotWord.getId());
        }
        return page;
    }
}
