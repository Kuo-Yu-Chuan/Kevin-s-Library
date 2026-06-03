package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HowScreen extends JFrame {
    public HowScreen () {

        setTitle ("怎麼搜尋？");    //視窗名稱
        setSize (300, 300);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 14);    //設定字型

        JLabel label = new JLabel ("<html>一般搜尋：<br>輸入書名關鍵字或作者。<br><br>出版年份：（可空白）<br>目標書籍的出版年份在此區間內。<br><br>語言：（可空白）<br>以英文完整輸入目標書籍的語言。<br>例如：Chinese、English、Japanese。<br><br>ＩＳＢＮ搜尋：<br>一字不差的輸入ＩＳＢＮ編號。<br>不接受數字以外的字元。</html>");
        label.setFont (font);
        add (label);

        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible (true);
    }
}
