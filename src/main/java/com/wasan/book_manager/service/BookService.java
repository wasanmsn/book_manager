package com.wasan.book_manager.service;

import com.wasan.book_manager.entity.Book;
import com.wasan.book_manager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(int page, int size, boolean random){

        if (random) {
            List<Book> books = bookRepository.findAll();
            Collections.shuffle(books);
            long start =  Math.min((long) page * size, books.size());
            long end = Math.min((start + size), books.size());
            return new PageImpl<>(books.subList((int) start, (int) end),
                    PageRequest.of(page, size),
                    books.size());
        }
        else {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("title"));
            return bookRepository.findAll(pageRequest);
        }
    }

    public Page<Book> getBooksByTitle(int page, int size, String title){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("title"));
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageRequest);
        }
        else {
            return bookRepository.findAll(pageRequest);
        }
    }
}
