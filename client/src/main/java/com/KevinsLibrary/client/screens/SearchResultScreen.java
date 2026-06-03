package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.*;

public class SearchResultScreen extends JFrame {

    int currentPage = 1;

    public SearchResultScreen (Book[] books) {

        setTitle ("興老大圖書館 搜尋結果");    //視窗名稱
        setSize (1200, 700);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        Font font = new Font ("微軟正黑體", Font.PLAIN, 14);    //設定字型

        if (books != null && books.length != 0) {
            int pages = (books.length / 5) + (books.length % 5 == 0 ? 0 : 1);

            JPanel pagePanel = new JPanel (new FlowLayout ());
            JButton prevPageButton = new JButton ("<");
            JButton nextPageButton = new JButton (">");
            JLabel pageLabel = new JLabel ("Page " + Integer.toString (currentPage) + "/" + Integer.toString (pages));
            prevPageButton.setFont (font);
            pageLabel.setFont (font);
            nextPageButton.setFont (font);
            pagePanel.add (prevPageButton);
            pagePanel.add (pageLabel);
            pagePanel.add (nextPageButton);
            add (pagePanel, BorderLayout.SOUTH);

            //.....這邊要列出.....這邊要列出.....這邊要列出.....這邊要列出.....這邊要列出.....這邊要列出.....這邊要列出.....這邊要列出.....

            //設定按鈕功能
            prevPageButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (currentPage != 1) {
                        currentPage--;
                        pageLabel.setText ("Page " + Integer.toString (currentPage) + "/" + Integer.toString (pages));
                    }
                }
            });
            nextPageButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (currentPage != pages) {
                        currentPage++;
                        pageLabel.setText ("Page " + Integer.toString (currentPage) + "/" + Integer.toString (pages));
                    }
                }
            });
        }
        else {
            JLabel sorryLabel = new JLabel ("拍謝啦，沒有你要的書。");
            sorryLabel.setFont (font);
            sorryLabel.setForeground (Color.RED);
            add (sorryLabel, BorderLayout.CENTER);
        }

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
