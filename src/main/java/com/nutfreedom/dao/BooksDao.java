package com.nutfreedom.dao;

import com.nutfreedom.model.Books;

import java.util.List;

public interface BooksDao {
    // 1. ดึงข้อมูลหนังสือ
    List<Books> getAllBooks();
    // 2. เพิ่มข้อมูลหนังสือ
    boolean isAddBook(Books book);
}
