package main.java.com.KevinsLibrary.client;

import javax.swing.*;

import main.java.com.KevinsLibrary.client.screens.*;
import main.java.com.KevinsLibrary.Book.BookDAO;
import main.java.com.KevinsLibrary.userType.UserDAO;
import main.java.com.KevinsLibrary.userType.LoanDAO;
import main.java.com.KevinsLibrary.userType.ReaderManager;

public class ClientMain {
    public static void main (String[] args) {
        Global.bookDAO = new BookDAO ();
        Global.userDAO = new UserDAO ();
        Global.loanDAO = new LoanDAO ();
        Global.readerManager = new ReaderManager ();
        UserDAO.defaultuser();
        BookDAO.defaultBook ();
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run () {
                new HomeScreen (null);    //呼叫主畫面
            }
        });
    }
}
