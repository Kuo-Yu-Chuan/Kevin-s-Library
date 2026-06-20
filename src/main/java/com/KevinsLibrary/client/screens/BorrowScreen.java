package main.java.com.KevinsLibrary.client.screens;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.Book;
import main.java.com.KevinsLibrary.userType.Reader;
import main.java.com.KevinsLibrary.userType.UserDAO;

public class BorrowScreen extends JFrame {
    public BorrowScreen (Book book) {
        
        setTitle ("借書");    //視窗名稱
        setSize (700, 400);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        //新增按鈕
        JButton borrowButton = new JButton ("借書");
        borrowButton.setFont (font);
        JButton cancelButton = new JButton ("取消");
        cancelButton.setFont (font);

        //新增文字
        JLabel accountLabel = new JLabel ("借閱者帳號：");
        accountLabel.setFont (font);

        //新增打字空間
        JTextField accountField = new JTextField ();
        accountField.setFont (font);

        //Panel 用於整合
        JPanel inputPanel = new JPanel (new GridLayout (1, 2, 10, 10));
        inputPanel.add (accountLabel);
        inputPanel.add (accountField);
        JPanel buttonPanel = new JPanel (new GridLayout (2, 1, 5, 5));
        buttonPanel.add (borrowButton);
        buttonPanel.add (cancelButton);

        //全部整合
        JPanel wholePanel = new JPanel (new BorderLayout (10, 10));
        wholePanel.setBorder (BorderFactory.createEmptyBorder (80, 80, 80, 80));
        wholePanel.add (inputPanel, BorderLayout.NORTH);
        wholePanel.add (buttonPanel, BorderLayout.SOUTH);
        add(wholePanel);

        //設定按鈕功能
        borrowButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String userID = accountField.getText ().trim ();
                Reader reader = UserDAO.getUserByID (userID);
                new BorrowResultScreen (main.java.com.KevinsLibrary.client.Global.readerManager.borrowBook (reader, book));
            }
        });
        cancelButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
            }
        });

        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible (true);    //顯示視窗
    }
}
