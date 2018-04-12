package com.nutfreedom.dao;

import com.nutfreedom.db.Database;
import com.nutfreedom.model.Books;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BooksDaoImpl implements BooksDao {
    private Database database;

    public List<Books> getAllBooks() {
        List<Books> booksList = new ArrayList<Books>();
        String sql = "select * from books";
        List<Map<String, Object>> result = database.queryList(sql);
        for (Map<String, Object> map : result) {
            Books books = new Books((Integer) map.get("id"), (String) map.get("title"), (Integer) map.get("price"));
            booksList.add(books);
        }
        return booksList;
    }

    public boolean isAddBook(Books book) {
        String sql = "insert into books(id,title,price) values(?,?,?)";
        int row = database.add(sql, book.getId(), book.getTitle(), book.getPrice());
        return row > 0;
    }
}
