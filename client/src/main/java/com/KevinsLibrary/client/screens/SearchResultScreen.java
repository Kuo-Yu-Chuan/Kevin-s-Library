package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchResultScreen extends JFrame {
    public SearchResultScreen (String[] results, byte pages) {    //results 不是 String[]

        byte currentPage = 1;

        setTitle ("興老大圖書館 搜尋結果");    //視窗名稱
        setSize (1200, 700);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        Font font = new Font ("微軟正黑體", Font.PLAIN, 14);    //設定字型

        JButton prevPageButton = new JButton ("<");
        JButton nextPageButton = new JButton (">");
        JLabel pageLabel = new JLabel ("Page " + Byte.toString (currentPage) + "/" + Byte.toString (pages));
        prevPageButton.setFont (font);
        add (prevPageButton, BorderLayout.SOUTH);
        pageLabel.setFont (font);
        add (pageLabel, BorderLayout.SOUTH);
        nextPageButton.setFont (font);
        add (nextPageButton, BorderLayout.SOUTH);

        JPanel listPanel = new JPanel (new GridLayout (5, 3, 10, 10));

        //設定按鈕功能
        prevPageButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (currentPage != 1) {
                    
                }
            }
        });
        nextPageButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (currentPage != pages) {
                    
                }
            }
        });

        //跳出搜尋畫面
        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible (true);
    }
}
