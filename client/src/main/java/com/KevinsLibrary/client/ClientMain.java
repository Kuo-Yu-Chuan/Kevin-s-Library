package main.java.com.KevinsLibrary.client;

import javax.swing.*;

import main.java.com.KevinsLibrary.client.screens.*;

public class ClientMain {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run () {
                new HomeScreen (null);    //呼叫主畫面
            }
        });
    }
}
