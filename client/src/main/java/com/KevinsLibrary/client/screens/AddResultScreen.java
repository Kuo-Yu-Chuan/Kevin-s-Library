package main.java.com.KevinsLibrary.client.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddResultScreen extends JFrame {
    public AddResultScreen (boolean bool) {
        setTitle ("新增圖書");    //視窗名稱
        setSize (100, 20);    //視窗大小
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);    //跳出登入的X
        setLocationRelativeTo (null);    //視窗在螢幕正中間
        Font font = new Font ("微軟正黑體", Font.PLAIN, 16);    //設定字型

        JLabel label = new JLabel ("");
        label.setFont (font);
        label.setHorizontalAlignment (SwingConstants.CENTER);
        if (bool) { label.setText ("新增成功！"); }
        else { label.setText ("該書已在館藏！"); }

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
