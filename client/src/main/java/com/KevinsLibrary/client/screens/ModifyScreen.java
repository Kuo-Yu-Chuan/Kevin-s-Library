package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.KevinsLibrary.Book.Book;
import main.java.com.KevinsLibrary.Book.BookDAO;

public class ModifyScreen extends JFrame {
    public ModifyScreen (Book book) {
        
        setTitle ("修改圖書資訊");    //視窗名稱
        setSize (700, 500);    //視窗大小
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
        fields[0] = new JTextField (book.getTitle ());
        fields[1] = new JTextField (book.getAuthor ());
        fields[2] = new JTextField (Integer.toString (book.getYear ()));
        fields[3] = new JTextField (book.getLanguage ());
        fields[4] = new JTextField (book.getISBN ());
        fields[5] = new JTextField (book.getCategoriesString ());
        fields[6] = new JTextField (book.getCallNumber ());
        fields[7] = new JTextField (book.getBarCode ());
        fields[8] = new JTextField (book.getPositionString ());
        fields[9] = new JTextField (Integer.toString (book.getAvailable ()));
        fields[10] = new JTextField (book.getEbook ());
        
        for (int i = 0; i < 11; i++) {
            labels[i].setFont (font);
            gbc.gridx = 0;    gbc.gridy = i;    //GridLayout中的位置
            gbc.gridwidth = 1;    //寬度1
            gbc.anchor = GridBagConstraints.EAST;    //向右對齊
            gbc.insets = new Insets (5, 5, 5, 5);
            panel.add (labels[i], gbc);
            
            fields[i].setFont (font);
            fields[i].setColumns (20);    //預設寬度
            gbc.gridx = 1;    gbc.gridy = i;    //GridLayout中的位置
            gbc.gridwidth = 1;    //寬度1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;    //靠左對齊
            panel.add(fields[i], gbc);
        }
        
        //新增按鈕
        JPanel btnPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 15, 0));
        JButton saveBtn = new JButton ("修改");
        JButton cancelBtn = new JButton ("取消");
        saveBtn.setFont (font);
        cancelBtn.setFont (font);
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        saveBtn.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                book.setInfo (
                    fields[0].getText ().trim (), 
                    fields[1].getText ().trim (), 
                    Integer.parseInt (fields[2].getText ().trim ()), 
                    fields[3].getText ().trim (), 
                    fields[4].getText ().trim (), 
                    fields[5].getText ().trim ().split (" "), 
                    fields[6].getText ().trim (), 
                    fields[7].getText ().trim (), 
                    fields[8].getText ().trim (), 
                    Byte.parseByte (fields[9].getText ().trim ()), 
                    fields[10].getText ().trim ()
                );
                BookDAO.updateInfo (book);
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
