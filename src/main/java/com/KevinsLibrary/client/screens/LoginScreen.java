package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.userType.User;
import main.java.com.KevinsLibrary.userType.UserDAO;

//登入畫面
public class LoginScreen extends JFrame {
    public LoginScreen (User user) {

        setTitle ("興老大圖書館 登入");    //視窗名稱
        setSize (700, 400);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        //新增按鈕
        JButton loginButton = new JButton ("登入");
        loginButton.setFont (font);
        JButton cancelButton = new JButton ("取消");
        cancelButton.setFont (font);

        //新增文字
        JLabel accountLabel = new JLabel ("帳號：");
        accountLabel.setFont (font);
        JLabel passwordLabel = new JLabel ("密碼：");
        passwordLabel.setFont (font);
        JLabel failLabel = new JLabel ("", SwingConstants.CENTER);
        failLabel.setForeground (Color.RED);
        failLabel.setFont (font);

        //新增打字空間
        JTextField accountField = new JTextField ();
        accountField.setFont (font);
        JPasswordField passwordField = new JPasswordField ();
        passwordField.setFont (font);

        //Panel 用於整合
        JPanel inputPanel = new JPanel (new GridLayout (2, 2, 10, 10));
        inputPanel.add (accountLabel);
        inputPanel.add (accountField);
        inputPanel.add (passwordLabel);
        inputPanel.add (passwordField);
        JPanel buttonPanel = new JPanel (new GridLayout (2, 1, 5, 5));
        buttonPanel.add (loginButton);
        buttonPanel.add (cancelButton);

        //全部整合
        JPanel wholePanel = new JPanel (new BorderLayout (10, 10));
        wholePanel.setBorder (BorderFactory.createEmptyBorder (80, 80, 80, 80));
        wholePanel.add (inputPanel, BorderLayout.NORTH);
        wholePanel.add (buttonPanel, BorderLayout.SOUTH);
        wholePanel.add (failLabel, BorderLayout.CENTER);
        add(wholePanel);

        //設定按鈕功能
        loginButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String userID = accountField.getText ().trim ();
                String password = new String (passwordField.getPassword ());

                //連接資料庫並登入
                User currentUser = UserDAO.loginUser (userID, password);
                if (currentUser != null) {
                    new HomeScreen (currentUser);
                    dispose ();
                }
                else {
                    failLabel.setText("帳號或密碼錯誤！");
                }
            }
        });
        cancelButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                new HomeScreen (user);
                dispose ();
            }
        });

        //跳出登入畫面
        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                new HomeScreen (user);
                dispose ();
            }
        });

        setVisible (true);    //顯示視窗
    }
}
