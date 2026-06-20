package main.java.com.KevinsLibrary.client;

import main.java.com.KevinsLibrary.Book.BookDAO;
import main.java.com.KevinsLibrary.userType.UserDAO;
import main.java.com.KevinsLibrary.userType.LoanDAO;
import main.java.com.KevinsLibrary.userType.ReaderManager;

public class Global {
    public static BookDAO bookDAO;
    public static UserDAO userDAO;
    public static LoanDAO loanDAO;
    public static ReaderManager readerManager;
}
