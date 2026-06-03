package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.Book;

public class BookScreen extends JFrame {
    public BookScreen (Book book) {
        setTitle ("書籍資料");    //視窗名稱
        setSize (900, 600);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JLabel label = new JLabel ("<html>書名：<br>" + book.getTitle () + "<br><br>作者：<br>" + book.getAuthor () + "<br><br>出版年份：<br>" + Integer.toString (book.getYear ()) + "<br><br>語言：<br>" + book.getLanguage () + "<br><br>ＩＳＢＮ：<br>" + book.getISBN () + "<br><br>標籤：<br>" + book.getCategoriesString () + "<br><br>館藏位置：<br>" + book.getPositionString () + "<br><br>目前館藏數目：" + Byte.toString (book.getAvailable ()) + "<br><br>電子書：<br>" + (book.isEbookAvailable () ? "網址" : "無") + "</html>");
        label.setFont (font);
        add (label);

        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible (true);
    }
}
