package main.java.com.KevinsLibrary.userType;

import main.java.com.KevinsLibrary.Book.Book;
import java.util.ArrayList;
import java.util.List;

public class ReaderManager {

    private List<Loan> loans = new ArrayList<>();

    public boolean canBorrow(Reader reader) {
        return reader.getFoul() == 0
                && reader.getBooksBorrowed() < 2;
    }

    public String borrowBook(Reader reader, Book book) {
        if (!canBorrow(reader)) {
            return "Reader cannot borrow books";
        }

        if (book.getAvailable() <= 0) {
            return "Book not available";
        }

        book.borrowBook();
        reader.setBooksBorrowed(reader.getBooksBorrowed() + 1);

        Loan loan = new Loan(book, reader);
        loans.add(loan);

        return "Borrow success";
    }

    public String returnBook(Reader reader, Book book) {
        for (Loan loan : loans) {
            if (loan.getReader() == reader
                    && loan.getBook() == book
                    && !loan.isReturned()) {

                loan.markReturned();
                book.returnBook();
                reader.setBooksBorrowed(reader.getBooksBorrowed() - 1);

                return "Return success";
            }
        }

        return "Loan record not found";
    }

    public List<Loan> getLoans() {
        return loans;
    }
    public void modifyLoans(Reader reader, Book book, boolean behavior) {
        if (behavior) {
            // 借書
            book.borrowBook();
            reader.setBooksBorrowed(reader.getBooksBorrowed() + 1);
        } else {
            // 還書
            book.returnBook();
            reader.setBooksBorrowed(reader.getBooksBorrowed() - 1);
        }
    }
}