package com.wasan.book_manager.controller;

import com.wasan.book_manager.entity.Book;
import com.wasan.book_manager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> listBooks(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "false") boolean random) {
        return bookService.getAllBooks(page, size, random);
    }

    @GetMapping("/searchBy")
    public Page<Book> listBooksByTitle(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "") String title){
        return bookService.getBooksByTitle(page, size,title);
    }
}
