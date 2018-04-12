package com.nutfreedom.view;

import com.nutfreedom.dao.BooksDao;
import com.nutfreedom.dao.BooksDaoImpl;
import com.nutfreedom.db.Database;
import com.nutfreedom.model.Books;
import com.nutfreedom.util.FileImages;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.util.List;

public class ShowBooks extends JFrame {

    private JTextArea txtAreaBooks;

    public ShowBooks() {
        initComponents();
        showBooks();
    }

    private void initComponents() {
        JPanel jPanel = new JPanel();
        JScrollPane jScrollPanel = new JScrollPane();
        txtAreaBooks = new JTextArea();
        JLabel bg = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายการหนังสือ");

        jPanel.setLayout(new AbsoluteLayout());

        txtAreaBooks.setEditable(false);
        txtAreaBooks.setColumns(20);
        txtAreaBooks.setRows(5);
        txtAreaBooks.setFocusable(false);
        jScrollPanel.setViewportView(txtAreaBooks);
        jPanel.add(jScrollPanel, new AbsoluteConstraints(30, 90, 340, 200));

        String imageIcon = FileImages.getFileImages("bg.png");
        if (!imageIcon.equals("")) {
            bg.setIcon(new ImageIcon(imageIcon));
            jPanel.add(bg, new AbsoluteConstraints(0, 0, 400, 300));
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public void showBooks() {
        Database database = new Database();
        BooksDao booksDao = new BooksDaoImpl(database);
        List<Books> booksList = booksDao.getAllBooks();
        for (Books book: booksList) {
            String text = "รหัส: " + book.getId() +
                    " ชื่อ: " + book.getTitle() +
                    " ราคา: " + book.getPrice() + "\n";
            txtAreaBooks.append(text);
        }
        database.close();
    }
}
