package main.java.com.KevinsLibrary.userType;

import main.java.com.KevinsLibrary.Book.Book;
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

        // ===== 👇 新增：真正寫入資料庫 =====
        LoanDAO.addLoan(loan);           // 在 loans 表格新增一筆紀錄
        UserDAO.updateReader(reader);    // 更新 users 表格裡，這個人的借書數量
        // 若你有 BookDAO.updateBook(book)，記得也要加在這裡更新實體書數量！
        // ===================================

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

                // ===== 👇 新增：真正寫入資料庫 =====
                LoanDAO.markReturned(reader.getUserID(), book.getBarCode()); // 更新紀錄為已還
                // ===================================

                if (LocalDate.now ().isAfter (loan.getDueDate ())) {
                    reader.addFoul();
                    UserDAO.updateReader(reader); // 更新讀者資料庫 (扣點 + 借書量-1)
                    return "你下次最好給我準時還書";
                }

                UserDAO.updateReader(reader); // 更新讀者資料庫 (借書量-1)
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