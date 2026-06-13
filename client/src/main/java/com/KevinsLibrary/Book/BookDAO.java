package main.java.com.KevinsLibrary.Book;

import java.sql.*;

public class BookDAO {
    private static final String URL = "jdbc:sqlite:library.db";

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                title TEXT NOT NULL,
                author TEXT NOT NULL,
                year INTEGER,
                language TEXT NOT NULL,
                ISBN TEXT,
                categories TEXT,
                callNumber TEXT,
                barCode TEXT PRIMARY KEY,
                library INTEGER NOT NULL,
                floor INTEGER NOT NULL,
                area TEXT NOT NULL,
                available INTEGER,
                ebook TEXT
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
            (title, author, year, language, ISBN, categories, callNumber, barCode, library, floor, area, available, ebook)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setString(4, book.getLanguage());
            stmt.setString(5, book.getISBN());
            stmt.setString(6, book.getCategoriesString ());
            stmt.setString(7, book.getCallNumber());
            stmt.setString(8, book.getBarCode());
            stmt.setInt(9, book.getPosition().getLibrary());
            stmt.setInt(10, book.getPosition().getFloor());
            stmt.setString(11, book.getPosition().getArea());
            stmt.setInt(12, book.getAvailable());
            stmt.setString(13, book.getEbook());

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