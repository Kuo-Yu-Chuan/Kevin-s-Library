package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.userType.User;
import main.java.com.KevinsLibrary.userType.Reader;
import main.java.com.KevinsLibrary.userType.UserDAO;

//註冊畫面
public class RegisterScreen extends JFrame {
    public RegisterScreen (User user) {
        
        setTitle ("興老大圖書館 註冊");    //視窗名稱
        setSize (700, 500);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        //新增按鈕
        JButton registerButton = new JButton ("註冊");
        registerButton.setFont (font);
        JButton cancelButton = new JButton ("取消");
        cancelButton.setFont (font);

        //新增文字
        JLabel accountLabel = new JLabel ("帳號：");
        accountLabel.setFont (font);
        JLabel passwordLabel = new JLabel ("密碼：");
        passwordLabel.setFont (font);
        JLabel nameLabel = new JLabel ("使用者名稱：");
        nameLabel.setFont (font);
        JLabel failLabel = new JLabel ("", SwingConstants.CENTER);
        failLabel.setForeground (Color.RED);
        failLabel.setFont (font);

        //新增打字空間
        JTextField accountField = new JTextField ();
        accountField.setFont (font);
        JPasswordField passwordField = new JPasswordField ();
        passwordField.setFont (font);
        JTextField nameField = new JTextField ();
        nameField.setFont (font);

        //Panel 用於整合 Label 、 Field 、 Botton
        JPanel inputPanel = new JPanel (new GridLayout (4, 2, 10, 10));
        inputPanel.add (accountLabel);
        inputPanel.add (accountField);
        inputPanel.add (passwordLabel);
        inputPanel.add (passwordField);
        inputPanel.add (nameLabel);
        inputPanel.add (nameField);
        JPanel buttonPanel = new JPanel (new GridLayout (2, 1, 5, 5));
        buttonPanel.add (registerButton);
        buttonPanel.add (cancelButton);

        //全部整合
        JPanel wholePanel = new JPanel (new BorderLayout (10, 10));
        wholePanel.setBorder (BorderFactory.createEmptyBorder (80, 80, 80, 80));
        wholePanel.add (inputPanel, BorderLayout.NORTH);
        wholePanel.add (buttonPanel, BorderLayout.SOUTH);
        wholePanel.add (failLabel, BorderLayout.CENTER);
        add (wholePanel);

        //設定按鈕功能
        registerButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String userID = accountField.getText ().trim ();
                String password = new String (passwordField.getPassword ());
                String userName = nameField.getText ().trim ();
                if (userID.length () != 0 && password.length () != 0 && userName.length () != 0) {
                    Reader reader = new Reader (userID, password, userName);
                    if (UserDAO.addReader (reader)) {
                        new HomeScreen (reader);
                        dispose ();
                    }
                    else {
                        failLabel.setText("不讓你註冊！"); 
                    }
                }
                else {
                    failLabel.setText("不讓你註冊！");
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

        //跳出註冊畫面
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
