package main.java.com.KevinsLibrary.Book;

import java.sql.*;

public class BookDAO {
    private static final String URL = "jdbc:sqlite:library.db";

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                barCode TEXT PRIMARY KEY,
                title TEXT NOT NULL,
                author TEXT NOT NULL,
                ISBN TEXT,
                callNumber TEXT,
                available INTEGER,
                ebookAvailable INTEGER
            )
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBook(Book book) {
        String sql = """
            INSERT INTO books 
            (barCode, title, author, ISBN, callNumber, available, ebookAvailable)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getBarCode());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getISBN());
            stmt.setString(5, book.getCallNumber());
            stmt.setInt(6, book.getAvailable());
            stmt.setInt(7, book.isEbookAvailable() ? 1 : 0);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAvailable(Book book) {
        String sql = "UPDATE books SET available = ? WHERE barCode = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getAvailable());
            stmt.setString(2, book.getBarCode());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}