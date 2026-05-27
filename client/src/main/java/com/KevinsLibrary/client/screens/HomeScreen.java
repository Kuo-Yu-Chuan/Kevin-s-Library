package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//主畫面
public class HomeScreen extends JFrame{
    public HomeScreen (String userName) {

        setTitle ("歡迎來到興老大圖書館");    //視窗名稱
        setSize (1200, 700);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        setLayout (new FlowLayout (FlowLayout.RIGHT, 10, 40));    //視窗布局

        //新增文字和按鈕
        JLabel nameLabel = new JLabel ("");
        JButton loginButton = new JButton ("");
        if (userName != null) {
            nameLabel.setText ("哈囉 " + userName);
            loginButton.setText ("切換帳號");
        }
        else {
            loginButton.setText ("登入");
        }
        add (nameLabel, BorderLayout.NORTH);
        loginButton.setFont (new Font ("微軟正黑體", Font.PLAIN, 16));
        add (loginButton, BorderLayout.NORTH);
        JButton registerButton = new JButton ("註冊");
        registerButton.setFont (new Font ("微軟正黑體", Font.PLAIN, 16));
        add (registerButton, BorderLayout.NORTH);
        
        //設定按鈕功能
        loginButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new LoginScreen (userName);
                dispose ();
            }
        });
        registerButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new RegisterScreen (userName);
                dispose ();
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
