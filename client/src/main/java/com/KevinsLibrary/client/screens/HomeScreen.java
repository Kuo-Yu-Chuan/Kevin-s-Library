package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.userType.User;

//主畫面
public class HomeScreen extends JFrame{
    public HomeScreen (User user) {

        setTitle ("興老大圖書館");    //視窗名稱
        setSize (900, 600);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        getContentPane ().setBackground (Color.decode ("#FFFFFF"));    //視窗背景純白
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        //整合中間部分
        JPanel middlePanel = new JPanel ();
        middlePanel.setLayout (new BoxLayout (middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));    //上下左右空著

        //中間大標誌
        ImageIcon libraryIcon = new ImageIcon ("images\\libraryIcon.png");
        Image libraryIconScaled = libraryIcon.getImage ().getScaledInstance (317, 112, Image.SCALE_SMOOTH);    //縮小圖片
        JLabel libraryLabel = new JLabel (new ImageIcon (libraryIconScaled));
        libraryLabel.setAlignmentX (Component.CENTER_ALIGNMENT);    //置中
        middlePanel.add (libraryLabel);
        middlePanel.add (Box.createVerticalStrut (5));    //加上間隔

        //中間第一行的文字
        JLabel text1Label = new JLabel ("Your gate way to knowledge and culture.");
        text1Label.setFont (font);
        text1Label.setForeground (Color.decode ("#696969"));
        text1Label.setAlignmentX (Component.CENTER_ALIGNMENT);
        middlePanel.add (text1Label);
        middlePanel.add (Box.createVerticalStrut (5));    //加上間隔

        //中間第二行的文字
        JLabel text2Label = new JLabel ("Accessible for everyone.");
        text2Label.setFont (font);
        text2Label.setForeground (Color.decode ("#696969"));
        text2Label.setAlignmentX (Component.CENTER_ALIGNMENT);
        middlePanel.add (text2Label);
        middlePanel.add (Box.createVerticalStrut (20));    //加上間隔

        //搜尋
        JPanel searchPanel = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JLabel searchLabel = new JLabel ("　　書名搜尋：");
        searchLabel.setFont (font);
        JTextField searchField = new JTextField (20);
        searchField.setFont (font);
        ImageIcon searchIcon = new ImageIcon ("images\\searchIcon.jpg");
        Image searchIconScaled = searchIcon.getImage ().getScaledInstance (25, 25, Image.SCALE_SMOOTH);
        JButton searchButton = new JButton (new ImageIcon (searchIconScaled));
        searchPanel.add (searchLabel);
        searchPanel.add (searchField);
        searchPanel.add (searchButton);
        searchPanel.setMaximumSize (new Dimension (Integer.MAX_VALUE, 5));
        searchPanel.setBackground(Color.decode ("#FFFFFF"));
        middlePanel.add (searchPanel);

        //ISBN搜尋
        JPanel ISBNPanel = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JLabel ISBNLabel = new JLabel ("ＩＳＢＮ搜尋：");
        ISBNLabel.setFont (font);
        JTextField ISBNField = new JTextField (20);
        ISBNField.setFont (font);
        JButton ISBNButton = new JButton (new ImageIcon (searchIconScaled));
        ISBNPanel.add (ISBNLabel);
        ISBNPanel.add (ISBNField);
        ISBNPanel.add (ISBNButton);
        ISBNPanel.setBackground(Color.decode ("#FFFFFF"));
        middlePanel.add (ISBNPanel);

        //中間整合完畢
        middlePanel.setBackground(Color.decode ("#FFFFFF"));
        add (middlePanel, BorderLayout.CENTER);

        //新增右上文字和按鈕
        JPanel rightPanel = new JPanel (new FlowLayout (FlowLayout.RIGHT, 10, 40));
        JLabel nameLabel = new JLabel ("");
        JButton loginButton = new JButton ("");
        if (user != null) {
            nameLabel.setText ("哈囉 " + user.getUserName ());
            loginButton.setText ("切換帳號");
        }
        else {
            loginButton.setText ("登入");
        }
        nameLabel.setFont (font);
        rightPanel.add (nameLabel, BorderLayout.NORTH);
        loginButton.setFont (font);
        rightPanel.add (loginButton, BorderLayout.NORTH);
        JButton registerButton = new JButton ("註冊");
        registerButton.setFont (font);
        rightPanel.add (registerButton, BorderLayout.NORTH);
        rightPanel.setBackground (Color.decode ("#FFFFFF"));
        add (rightPanel, BorderLayout.NORTH);

        //新增下方按鈕
        JButton donateButton = new JButton ("贊助我們");
        donateButton.setFont (font);
        add (donateButton, BorderLayout.SOUTH);
        
        //設定按鈕功能
        searchButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new SearchResultScreen (null);
            }
        });
        ISBNButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new SearchResultScreen (null);
            }
        });
        loginButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new LoginScreen (user);
                dispose ();
            }
        });
        registerButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new RegisterScreen (user);
                dispose ();
            }
        });
        donateButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                System.out.println ("謝謝啦！");
            }
        });

        //結束應用程式
        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                //.....這邊要登出.....這邊要登出.....這邊要登出.....這邊要登出.....這邊要登出.....這邊要登出.....這邊要登出.....這邊要登出.....
                System.exit (0);
            }
        });
        
        setVisible (true);    //顯示視窗
    }
}
