package com.nutfreedom.view;

import com.nutfreedom.dao.BooksDao;
import com.nutfreedom.dao.BooksDaoImpl;
import com.nutfreedom.db.Database;
import com.nutfreedom.model.Books;
import com.nutfreedom.util.FileImages;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook extends JFrame {

    private JTextField txtPrice;
    private JTextField txtTitle;

    public AddBook() {
        initComponents();
    }

    private void initComponents() {
        JPanel jPanel = new JPanel();
        txtPrice = new JTextField();
        txtTitle = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JButton btnSave = new JButton();
        JLabel bg = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("เพิ่มหนังสือ");

        jPanel.setLayout(new AbsoluteLayout());

        txtPrice.setFont(new Font("TH K2D July8", Font.PLAIN, 18));
        txtPrice.setForeground(new Color(0, 101, 68));
        txtPrice.setHorizontalAlignment(JTextField.CENTER);
        txtPrice.setBorder(null);
        jPanel.add(txtPrice, new AbsoluteConstraints(40, 190, 320, 40));

        txtTitle.setFont(new Font("TH K2D July8", Font.PLAIN, 18));
        txtTitle.setForeground(new Color(0, 101, 68));
        txtTitle.setHorizontalAlignment(JTextField.CENTER);
        txtTitle.setBorder(null);
        jPanel.add(txtTitle, new AbsoluteConstraints(40, 120, 320, 40));

        jLabel1.setFont(new java.awt.Font("TH K2D July8", Font.PLAIN, 24));
        jLabel1.setForeground(new java.awt.Color(0, 2, 98));
        jLabel1.setText("ชื่อหนังสือ");
        jPanel.add(jLabel1, new AbsoluteConstraints(40, 90, 320, 30));

        jLabel2.setFont(new java.awt.Font("TH K2D July8", Font.PLAIN, 24));
        jLabel2.setForeground(new java.awt.Color(0, 2, 98));
        jLabel2.setText("ราคา");
        jPanel.add(jLabel2, new AbsoluteConstraints(40, 160, 320, 30));

        btnSave.setBackground(new Color(177, 231, 223));
        btnSave.setFont(new Font("TH K2D July8", Font.PLAIN, 24));
        btnSave.setForeground(new Color(0, 101, 68));
        btnSave.setText("บันทึก");
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionPerformed();
            }
        });
        jPanel.add(btnSave, new AbsoluteConstraints(140, 250, 120, 40));

        String imageIcon = FileImages.getFileImages("bg.png");
        if (!imageIcon.equals("")) {
            bg.setIcon(new ImageIcon(imageIcon));
            jPanel.add(bg, new AbsoluteConstraints(0, 0, -1, -1));
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addBook() {
        String title = txtTitle.getText().trim();
        String price = txtPrice.getText().trim();

        Books book = new Books(0, title, Integer.parseInt(price));

        Database database = new Database();
        BooksDao booksDao = new BooksDaoImpl(database);

        boolean result = booksDao.isAddBook(book);
        if (result) {
            JOptionPane.showMessageDialog(this, "บันทึกสำเร็จ", "ผลลัพธ์", 1);
            txtTitle.setText("");
            txtPrice.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "บันทึกไม่สำเร็จ", "ผลลัพธ์", 0);
            System.exit(0);
        }
        database.close();
    }

    private void btnSaveActionPerformed() {
        addBook();
    }
}
