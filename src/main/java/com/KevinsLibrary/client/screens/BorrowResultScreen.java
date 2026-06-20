package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorrowResultScreen extends JFrame {
    public BorrowResultScreen (byte n) {
        setTitle ("借書結果");    //視窗名稱
        setSize (300, 90);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JLabel label = new JLabel ("");
        label.setFont (font);
        label.setHorizontalAlignment (SwingConstants.CENTER);
        switch (n) {
            case 1 :
                label.setText ("借書成功！");
                break;
            case 2 :
                label.setText ("館藏不夠了！");
                break;
            default :
                label.setText ("這個人是混球，不能借他！");
        }

        add (label);

        addWindowListener (new WindowAdapter () {
            @Override
            public void windowClosing (WindowEvent e) {
                dispose ();
            }
        });

        setVisible(true);
    }
}
