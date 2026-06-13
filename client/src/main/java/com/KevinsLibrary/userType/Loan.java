package main.java.com.KevinsLibrary.userType;

import main.java.com.KevinsLibrary.Book.Book;
import java.time.LocalDate;

public class Loan {

    private Book book;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
        this.returned = false;
    }

    public Loan(Book book, Reader reader, LocalDate borrowDate, LocalDate dueDate, boolean returned) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }
    
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
