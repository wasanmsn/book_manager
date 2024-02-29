package com.wasan.book_manager.repository;

import com.wasan.book_manager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
