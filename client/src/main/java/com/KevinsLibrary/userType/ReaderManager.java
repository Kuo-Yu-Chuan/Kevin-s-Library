package main.java.com.KevinsLibrary.userType;

import main.java.com.KevinsLibrary.Book.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class ReaderManager {

    private List<Loan> loans = new ArrayList<>();

    public ReaderManager () {
        loans = LoanDAO.getAllLoans();
    }

    public boolean canBorrow(Reader reader) {
        return reader.getFoul() < 2
                && reader.getBooksBorrowed() < 3;
    }

    public byte borrowBook(Reader reader, Book book) {
        if (!canBorrow(reader)) {
            return 0;
        }

        if (book.getAvailable() <= 0) {
            return 2;
        }

        book.borrowBook();
        reader.setBooksBorrowed(reader.getBooksBorrowed() + 1);

        Loan loan = new Loan(book, reader);
        loans.add(loan);

        // ===== 👇 新增：真正寫入資料庫 =====
        LoanDAO.addLoan(loan);           // 在 loans 表格新增一筆紀錄
        UserDAO.updateReader(reader);    // 更新 users 表格裡，這個人的借書數量
        BookDAO.updateAvailable (book);
        // ===================================

        return 1;
    }

    public byte returnBook(Reader reader, Book book) {
        for (Loan loan : loans) {
            if (loan.getReader() == reader
                    && loan.getBook() == book
                    && !loan.isReturned()) {

                loan.markReturned();
                book.returnBook();
                reader.setBooksBorrowed(reader.getBooksBorrowed() - 1);

                // ===== 👇 新增：真正寫入資料庫 =====
                LoanDAO.markReturned(reader.getUserID(), book.getBarCode()); // 更新紀錄為已還
                BookDAO.updateAvailable (book);
                // ===================================

                if (LocalDate.now ().isAfter (loan.getDueDate ())) {
                    reader.addFoul();
                    UserDAO.updateReader(reader); // 更新讀者資料庫 (扣點 + 借書量-1)
                    return (byte) 2;
                }

                UserDAO.updateReader(reader); // 更新讀者資料庫 (借書量-1)
                return (byte) 1;
            }
        }

        return (byte) 0;
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