package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReturnResultScreen extends JFrame {
    public ReturnResultScreen (byte n) {
        setTitle ("還書結果");    //視窗名稱
        setSize (100, 20);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JLabel label = new JLabel ("");
        label.setFont (font);
        label.setHorizontalAlignment (SwingConstants.CENTER);
        switch (n) {
            case 1 :
                label.setText ("還書成功！");
                break;
            case 2 :
                label.setText ("逾期歸還！");
                break;
            default :
                label.setText ("沒有這筆借閱紀錄，他是偷書賊！");
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
