package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.Book;
import main.java.com.KevinsLibrary.userType.*;

public class BookScreen extends JFrame {
    public BookScreen (Book book, User user) {
        setTitle ("書籍資料");    //視窗名稱
        setSize (700, 750);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JLabel label = new JLabel ("<html>書名：<br>" + book.getTitle () + "<br><br>作者：<br>" + book.getAuthor () + "<br><br>出版年份：<br>" + Integer.toString (book.getYear ()) + "<br><br>語言：<br>" + book.getLanguage () + "<br><br>ＩＳＢＮ：<br>" + book.getISBN () + "<br><br>標籤：<br>" + book.getCategoriesString () + "<br><br>館藏位置：<br>" + book.getPositionString () + "<br><br>索書號：<br>" + book.getBarCode () + "<br><br>目前館藏數目：" + Byte.toString (book.getAvailable ()) + "<br><br>電子書：<br>" + book.getEbook () + "</html>");
        label.setFont (font);
        add (label, BorderLayout.CENTER);

        if (user != null && user.getClass () == Admin.class) {
            JButton modifyButton = new JButton ("修改圖書資料");
            modifyButton.setFont (font);
            add (modifyButton, BorderLayout.SOUTH);

            modifyButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    new ModifyScreen (book);
                }
            });
        }
        else if (user != null && user.getClass () == Staff.class) {
            JPanel buttonPanel = new JPanel (new GridLayout (2, 1, 5, 5));
            JButton lendButton = new JButton ("借書");
            JButton returnButton = new JButton ("還書");
            lendButton.setFont (font);
            returnButton.setFont (font);
            buttonPanel.add (lendButton);
            buttonPanel.add (returnButton);
            add (buttonPanel, BorderLayout.SOUTH);
            
            lendButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    new BorrowScreen (book);
                }
            });
            returnButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    new ReturnScreen (book);
                }
            });
        }

        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible (true);
    }
}
