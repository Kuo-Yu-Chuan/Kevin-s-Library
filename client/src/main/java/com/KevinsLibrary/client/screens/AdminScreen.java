package main.java.com.KevinsLibrary.client.screens;

import java.util.Set;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.*;

public class AdminScreen extends JFrame {
    public AdminScreen () {
        
        setTitle ("新增圖書");    //視窗名稱
        setSize (500, 500);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JPanel panel = new JPanel (new GridBagLayout ());
        panel.setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints ();
        
        JLabel[] labels = new JLabel[11];
        labels[0] = new JLabel ("書名：");
        labels[1] = new JLabel ("作者：");
        labels[2] = new JLabel ("出版年份：");
        labels[3] = new JLabel ("語言：");
        labels[4] = new JLabel ("ＩＳＢＮ：");
        labels[5] = new JLabel ("標籤：");
        labels[6] = new JLabel ("索書號：");
        labels[7] = new JLabel ("條碼號：");
        labels[8] = new JLabel ("館藏位置：");
        labels[9] = new JLabel ("館藏數：");
        labels[10] = new JLabel ("電子書：");
        JTextField[] fields = new JTextField[11];
        
        for (int i = 0; i < 11; i++) {
            labels[i].setFont (font);
            gbc.gridx = 0;    gbc.gridy = i;    //GridLayout中的位置
            gbc.gridwidth = 1;    //寬度1
            gbc.anchor = GridBagConstraints.EAST;    //向右對齊
            gbc.insets = new Insets (5, 5, 5, 5);
            panel.add (labels[i], gbc);
            
            fields[i] = new JTextField (20);
            fields[i].setFont (font);
            gbc.gridx = 1;    gbc.gridy = i;    //GridLayout中的位置
            gbc.gridwidth = 1;    //寬度1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;    //靠左對齊
            panel.add(fields[i], gbc);
        }
        
        //新增按鈕
        JPanel btnPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 15, 0));
        JButton saveBtn = new JButton ("新增");
        saveBtn.setFont (font);
        JButton cancelBtn = new JButton ("取消");
        cancelBtn.setFont (font);
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        saveBtn.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                String[] strings = fields[5].getText ().trim ().split (" ");
                Set<String> categories = new HashSet<> ();
                for (String str : strings) { categories.add (str); }
                Position position = new Position ((byte) 1, (byte) 1, "");
                Book book = new Book (
                    fields[0].getText ().trim (), 
                    fields[1].getText ().trim (), 
                    Integer.parseInt (fields[2].getText ().trim ()), 
                    fields[3].getText ().trim (), 
                    fields[4].getText ().trim (), 
                    categories, 
                    fields[6].getText ().trim (), 
                    fields[7].getText ().trim (), 
                    position, 
                    Byte.parseByte (fields[9].getText ().trim ()), 
                    fields[10].getText ().trim ()
                );
                book.setPosition (fields[8].getText ().trim ());
                new AddResultScreen (BookDAO.addBook (book));
            }
        });
        cancelBtn.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;    //寬度2
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;    //置中
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(btnPanel, gbc);
        add(panel);
        
        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible(true);
    }
}
