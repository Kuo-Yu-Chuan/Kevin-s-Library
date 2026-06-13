package main.java.com.KevinsLibrary.userType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import main.java.com.KevinsLibrary.Book.Book;

public class LoanDAO {
    private static final String URL = "jdbc:sqlite:library.db";

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS loans (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                userID TEXT NOT NULL,
                barCode TEXT NOT NULL,
                borrowDate TEXT NOT NULL,
                returnDate TEXT NOT NULL,
                returned INTEGER DEFAULT 0
            )
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addLoan(Loan loan) {
        String sql = """
            INSERT INTO loans 
            (userID, barCode, borrowDate, returnDate, returned)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loan.getReader().getUserID());
            stmt.setString(2, loan.getBook().getBarCode());
            stmt.setString(3, loan.getBorrowDate().toString());
            stmt.setString(4, loan.getDueDate().toString());
            stmt.setInt(5, loan.isReturned() ? 1 : 0);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void markReturned(String userID, String barCode) {
        String sql = """
            UPDATE loans
            SET returned = 1
            WHERE userID = ? AND barCode = ? AND returned = 0
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userID);
            stmt.setString(2, barCode);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reader reader = getUserByID (rs.getString("userID"));
                Book book = getBookByBar (rs.getString("barCode"));
                LocalDate borrowDate = LocalDate.parse (rs.getString("borrowDate"));
                LocalDate dueDate = LocalDate.parse (rs.getString("dueDate"));
                boolean returned = rs.getInt ("returned") > 0 ? true : false;

                loans.add (new Loan (book, reader, borrowDate, dueDate, returned));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
}
