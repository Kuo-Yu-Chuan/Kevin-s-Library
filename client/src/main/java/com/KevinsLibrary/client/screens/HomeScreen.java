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

        setTitle ("興老大圖書館");    //視窗名稱
        setSize (900, 600);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //結束應用程式的X
        setLocationRelativeTo (null);    //視窗在螢幕正中央
        getContentPane ().setBackground (Color.decode ("#FFFFFF"));
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);

// 將原本的 GridLayout 改為垂直排列的 BoxLayout
JPanel middlePanel = new JPanel();
middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

// 【解決上方留白】設定內邊距：上、左、下、右（這裡設定上方留白 40 像素，可自行調整）
middlePanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));

// --- 圖片區 ---
ImageIcon libraryIcon = new ImageIcon("images\\libraryIcon.png");
Image scaledImage = libraryIcon.getImage().getScaledInstance(317, 112, Image.SCALE_SMOOTH);
JLabel libraryLabel = new JLabel(new ImageIcon(scaledImage));
libraryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // BoxLayout 置中方式
middlePanel.add(libraryLabel);

// 在元件之間加入微小的固定間隔（例如 10 像素），避免完全黏在一起
middlePanel.add(Box.createVerticalStrut(10)); 

// --- 文字 1 ---
JLabel text1Label = new JLabel("Your gate way to knowledge and culture.");
text1Label.setFont(font);
text1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
middlePanel.add(text1Label);

middlePanel.add(Box.createVerticalStrut(10));

// --- 文字 2 ---
JLabel text2Label = new JLabel("Accessible for everyone.");
text2Label.setFont(font);
text2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
middlePanel.add(text2Label);

middlePanel.add(Box.createVerticalStrut(15)); // 搜尋框上方可以稍微再多空一點點

// --- 搜尋區 ---
JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
// 提示：原本你用 searchPanel.add(..., BorderLayout.CENTER) 
// 但 searchPanel 初始化是 FlowLayout，傳入 BorderLayout 的參數是無效的，已順便幫你修正

JTextField searchField = new JTextField(20); // 建議給予欄位預設寬度（格數）
searchField.setFont(font);

ImageIcon searchIcon = new ImageIcon("images\\searchIcon.jpg");
Image scaledImage2 = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); 
JButton searchButton = new JButton(new ImageIcon(scaledImage2));

searchPanel.add(searchField);
searchPanel.add(searchButton);

// 限制 searchPanel 的最大高度，避免在 BoxLayout 中被垂直拉伸
searchPanel.setMaximumSize(searchPanel.getPreferredSize()); 
searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

searchPanel.setBackground (Color.decode ("#FFFFFF"));
middlePanel.add(searchPanel);

// 最後一樣放進主視窗的中央
middlePanel.setBackground (Color.decode ("#FFFFFF"));
add(middlePanel, BorderLayout.CENTER);
/*
        JPanel middlePanel = new JPanel (new BorderLayout());
        ImageIcon libraryIcon = new ImageIcon ("images\\libraryIcon.png");
        JLabel libraryLabel = new JLabel (libraryIcon);
        middlePanel.add (libraryLabel, BorderLayout.CENTER);
        JTextField searchField = new JTextField ();
        searchField.setFont (font);
        middlePanel.add (searchField, BorderLayout.SOUTH);
        ImageIcon searchIcon = new ImageIcon ("images\\searchIcon.jpg");
        JButton searchButton = new JButton (searchIcon);
        middlePanel.add (searchButton, BorderLayout.SOUTH);
        add (middlePanel);
*/

        //新增文字和按鈕
        JPanel rightPanel = new JPanel (new FlowLayout (FlowLayout.RIGHT, 10, 40));
        JLabel nameLabel = new JLabel ("");
        JButton loginButton = new JButton ("");
        if (userName != null) {
            nameLabel.setText ("哈囉 " + userName);
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
