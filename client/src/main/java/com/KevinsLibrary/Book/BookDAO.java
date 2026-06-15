package main.java.com.KevinsLibrary.Book;

import java.sql.*;
import java.util.*;
public class BookDAO {
    private static final String URL = "jdbc:sqlite:libraryBook.db";

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                title TEXT NOT NULL,
                author TEXT NOT NULL,
                year INTEGER,
                language TEXT NOT NULL,
                ISBN TEXT PRIMARY KEY,
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

    public static boolean addBook(Book book) {
        String ISBN = book.getISBN ();
        if (getBookByISBN (ISBN) != null) { return false; }
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
            stmt.setString(6, book.getCategoriesString());
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
        return true;
    }

    public static void updateInfo (Book book) {
        String sql = "UPDATE books SET title = ?,  author = ?, year = ?, language = ?, ISBN = ?, categories = ?, callNumber = ?, library = ?, floor = ?, area = ?, available = ?, ebook = ? WHERE barCode = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setString(4, book.getLanguage());
            stmt.setString(5, book.getISBN());
            stmt.setString(6, book.getCategoriesString());
            stmt.setString(7, book.getCallNumber());
            stmt.setInt(8, book.getPosition().getLibrary ());
            stmt.setInt(9, book.getPosition().getFloor ());
            stmt.setString(10, book.getPosition().getArea ());
            stmt.setInt(11, book.getAvailable());
            stmt.setString(12, book.getEbook());
            stmt.setString(13, book.getBarCode());

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

    public static Book getBookByBar(String barCode) {
        String sql = "SELECT * FROM books WHERE barCode = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, barCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return buildBook(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Book getBookByISBN(String ISBN) {
        String sql = "SELECT * FROM books WHERE ISBN = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ISBN);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return buildBook(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Book> getBookByKey(String keyword, int yearFrom, int yearTo) {
        ArrayList<Book> books = new ArrayList<>();
        String sql = """
        SELECT * FROM books
        WHERE (title LIKE ? OR author LIKE ?)
        AND year BETWEEN ? AND ?
    """;
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            stmt.setString(1, key);
            stmt.setString(2, key);
            stmt.setInt(3, yearFrom);
            stmt.setInt(4, yearTo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(buildBook(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private static Book buildBook(ResultSet rs) throws SQLException {
        Set<String> categories = new HashSet<>();

        String categoryText = rs.getString("categories");
        if (categoryText != null && !categoryText.isEmpty()) {
            String[] parts = categoryText.split(",");
            for (String part : parts) {
                categories.add(part.trim());
            }
        }

        Position position = new Position(
                (byte) rs.getInt("library"),
                (byte) rs.getInt("floor"),
                rs.getString("area")
        );

        return new Book(
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("year"),
                rs.getString("language"),
                rs.getString("ISBN"),
                categories,
                rs.getString("callNumber"),
                rs.getString("barCode"),
                position,
                (byte) rs.getInt("available"),
                rs.getString("ebook")
        );
    }
    /**
     * 透過 BarCode 取得資料庫中的 Book 物件
     */
    public static Book getBookByBarCode(String barCode) {
        // 這裡的 sql 語法請依照你實際的書籍資料表結構微調
        String sql = "SELECT * FROM books WHERE barCode = ?";

        // 假設你的資料庫連線 URL 一樣是這個
        String URL = "jdbc:sqlite:library.db";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(URL);
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, barCode);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // 這裡的寫法取決於你的 Book 建構子長怎樣
                    // 將資料庫撈出來的資料，打包成一個 Book 物件回傳
                    // 例如： return new Book(rs.getString("title"), rs.getString("author"), ...);

                    // 下面這行只是暫時讓你不報錯的假程式碼，請換成你真實的 Book 建構子！
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    int year = rs.getInt("year");
                    String language = rs.getString("language");
                    String isbn = rs.getString("isbn");
                    String callNumber = rs.getString("callNumber");
                    String dbBarCode = rs.getString("barCode"); // 其實就是傳進來的條碼
                    byte available = (byte) rs.getInt("available");
                    String ebook = rs.getString("ebook");

                    // 2. 處理 Categories (類別集合) - 暫時先給一個空的集合避免報錯
                    java.util.Set<String> categories = new java.util.HashSet<>();

                    // 3. 處理 Position (實體位置) - 假設資料庫裡有存這三個欄位
                    byte library = (byte) rs.getInt("library");
                    byte floor = (byte) rs.getInt("floor");
                    String area = rs.getString("area");
                    main.java.com.KevinsLibrary.Book.Position position = new main.java.com.KevinsLibrary.Book.Position(library, floor, area);

                    // 4. 湊齊 11 個參數了！正式把書本打包回傳
                    return new Book(title, author, year, language, isbn, categories, callNumber, dbBarCode, position, available, ebook);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null; // 找不到這本書
    }
}