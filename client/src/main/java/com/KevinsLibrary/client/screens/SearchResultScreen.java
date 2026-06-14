package main.java.com.KevinsLibrary.client.screens;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.*;
import main.java.com.KevinsLibrary.userType.*;

public class SearchResultScreen extends JFrame {

    int currentPage = 1;
    int booksPerPage = 10;

    public SearchResultScreen (ArrayList<Book> books, User user) {

        setTitle ("興老大圖書館 搜尋結果");    //視窗名稱
        setSize (800, 700);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        if (books != null && books.size () != 0) {

            int pages = (books.size () / booksPerPage) + (books.size () % booksPerPage == 0 ? 0 : 1);

            //翻頁按鈕
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

            //新增第一頁書目按鈕
            JPanel listPanel = new JPanel (new GridBagLayout ());
            JButton titleButtons[] = new JButton [booksPerPage];
            JLabel authorLabels[] = new JLabel [booksPerPage];
            JLabel yearLabels[] = new JLabel [booksPerPage];
            GridBagConstraints gbc = new GridBagConstraints ();    //控制位置
            int vGap = 15;
            gbc.insets = new Insets (vGap / 2, 10, vGap/2, 10);    //上左下右的空間
            int firstNumOfBooks = books.size () < booksPerPage ? books.size () : booksPerPage;    //避免超出陣列範圍
            for (int i = 0; i < firstNumOfBooks; i++) {

                Book currentBook = books.get (booksPerPage * currentPage - booksPerPage + i);

                //書名按鈕
                titleButtons[i] = new JButton (currentBook.getTitle ());
                titleButtons[i].setFont (font);
                titleButtons[i].setHorizontalAlignment (SwingConstants.LEFT);    //靠左
                gbc.fill = GridBagConstraints.HORIZONTAL;    //書名太長時會變成...
                gbc.gridx = 0; gbc.gridy = i;    //[i][0]的位置
                gbc.weightx = 0.6;    //長度佔60%
                listPanel.add (titleButtons[i], gbc);

                //作者文字
                authorLabels[i] = new JLabel ("作者：" + currentBook.getAuthor ());
                authorLabels[i].setFont (font);
                gbc.gridx = 1;
                gbc.weightx = 0.2;
                listPanel.add (authorLabels[i], gbc);

                //出版年份文字
                yearLabels[i] = new JLabel ("出版年份：" + Integer.toString (currentBook.getYear ()));
                yearLabels[i].setFont (font);
                gbc.gridx = 2;
                gbc.weightx = 0.2;
                listPanel.add (yearLabels[i], gbc);
            }
            add (listPanel);

            //設定按鈕功能
            for (int i = 0; i < firstNumOfBooks; i++) {
                int temp = i;    //就是要用，不然有錯。我也不知道為什麼。
                titleButtons[temp].addActionListener (new ActionListener () {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                        new BookScreen (books.get (booksPerPage * currentPage - booksPerPage + temp), user);
                    }
                });
            }
            prevPageButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (currentPage != 1) {
                        currentPage--;
                        pageLabel.setText ("Page " + Integer.toString (currentPage) + "/" + Integer.toString (pages));

                        for (int i = 0; i < booksPerPage; i++) {
                            titleButtons[i].setVisible (true);
                            authorLabels[i].setVisible (true);
                            yearLabels[i].setVisible (true);

                            Book currentBook = books.get (booksPerPage * currentPage - booksPerPage + i);
                            titleButtons[i].setText (currentBook.getTitle ());
                            authorLabels[i].setText ("作者：" + currentBook.getAuthor ());
                            yearLabels[i].setText ("出版年份：" + Integer.toString (currentBook.getYear ()));
                        }
                        revalidate ();  repaint ();
                    }
                }
            });
            nextPageButton.addActionListener (new ActionListener () {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (currentPage != pages) {
                        currentPage++;
                        pageLabel.setText ("Page " + Integer.toString (currentPage) + "/" + Integer.toString (pages));
                        
                        int numOfBooks = booksPerPage;
                        if (currentPage == pages - 1) {
                            numOfBooks = books.size () % booksPerPage;
                            for (int i = numOfBooks; i < booksPerPage; i++) {
                                titleButtons[i].setVisible (false);
                                authorLabels[i].setVisible (false);
                                yearLabels[i].setVisible (false);
                            }
                        }
                        for (int i = 0; i < numOfBooks; i++) {
                            Book currentBook = books.get (booksPerPage * currentPage - booksPerPage + i);
                            titleButtons[i].setText (currentBook.getTitle ());
                            authorLabels[i].setText ("作者：" + currentBook.getAuthor ());
                            yearLabels[i].setText ("出版年份：" + Integer.toString (currentBook.getYear ()));
                        }
                        revalidate ();  repaint ();
                    }
                }
            });
        }
        else {
            JLabel sorryLabel = new JLabel ("拍謝啦，沒有你要的書。");
            sorryLabel.setFont (font);
            sorryLabel.setForeground (Color.RED);
            sorryLabel.setHorizontalAlignment (SwingConstants.CENTER);
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
