package main.java.com.KevinsLibrary.userType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO 類別：負責與資料庫進行圖書館使用者相關的存取操作
 */
public class UserDAO {
    // 資料庫連線字串 (若使用 MySQL 可改為 "jdbc:mysql://localhost:3306/library")
    private static final String URL = "jdbc:sqlite:libraryUser.db";

    /**
     * 建立資料表並預設寫入管理員與一般讀者帳號（若不存在時）
     */
    public static void defaultuser() {
        // 1. 確保資料表存在 (包含讀者專用的 foul, donate, booksBorrowed)
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "userID TEXT PRIMARY KEY, "
                + "password TEXT NOT NULL, "
                + "userName TEXT NOT NULL, "
                + "role TEXT NOT NULL, "
                + "foul INTEGER DEFAULT 0, "
                + "donate INTEGER DEFAULT 0, "
                + "booksBorrowed INTEGER DEFAULT 0)";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 2. 建立管理員帳號 (配合你介面的 admin / 1234)
        insertDefaultAccount("admin", "1234", "管理員大老", "Admin", 0, 0, 0);

        insertDefaultAccount("staff", "4321", "櫃台", "Staff", 0, 0, 0);

        // 3. 建立測試用讀者帳號 (配合你介面的測試帳號)
        insertDefaultAccount("testUser", "0000", "測試讀者", "Reader", 0, 0, 0);
    }

    /**
     * 內部輔助方法：插入預設帳號
     */
    private static void insertDefaultAccount(String id, String pw, String name, String role, int foul, long donate, int borrowed) {
        String checkSQL = "SELECT COUNT(1) FROM users WHERE userID = ?";
        String insertSQL = "INSERT INTO users (userID, password, userName, role, foul, donate, booksBorrowed) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement checkStmt = conn.prepareStatement(checkSQL)) {

            checkStmt.setString(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && !rs.getBoolean(1)) { // 如果帳號不存在
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                        insertStmt.setString(1, id);
                        insertStmt.setString(2, pw);
                        insertStmt.setString(3, name);
                        insertStmt.setString(4, role);
                        insertStmt.setInt(5, foul);
                        insertStmt.setLong(6, donate);
                        insertStmt.setInt(7, borrowed);
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增讀者到資料庫 (對應 RegisterScreen 的註冊功能)
     * * @param reader 欲新增的讀者物件
     */
    public static boolean addReader(Reader reader) {
        String sql = "INSERT INTO users (userID, password, userName, role, foul, donate, booksBorrowed) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<User> users = getAllUsers ();
        for (User user : users) {
            if (user.getUserID ().equals (reader.getUserID ())) { return false; }
        }
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reader.getUserID());
            stmt.setString(2, reader.getPassword());
            stmt.setString(3, reader.getUserName());
            stmt.setString(4, reader.getRole());
            stmt.setInt(5, reader.getFoul());
            stmt.setLong(6, reader.getDonate());
            stmt.setInt(7, reader.getBooksBorrowed());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 驗證登入並回傳對應的 User 物件 (對應 LoginScreen 的登入功能)
     * * @param inputID 使用者輸入的帳號
     * @param inputPW 使用者輸入的密碼
     * @return 登入成功回傳 User (或其子類別)，失敗回傳 null
     */
    public static User loginUser(String inputID, String inputPW) {
        String sql = "SELECT * FROM users WHERE userID = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inputID);
            stmt.setString(2, inputPW);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    String name = rs.getString("userName");

                    // 依照資料庫中的 Role 欄位，實例化對應的物件
                    if ("Admin".equals(role)) {
                        return new Admin(inputID, inputPW, name);
                    } else if ("Staff".equals(role)) {
                        return new Staff(inputID, inputPW, name);
                    } else {
                        // 預設為 Reader
                        int foul = rs.getInt("foul");
                        long donate = rs.getLong("donate");
                        int borrowed = rs.getInt("booksBorrowed");
                        return new Reader(inputID, inputPW, name, foul, donate, borrowed);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 帳號密碼錯誤或不存在
    }

    /**
     * 取得系統內所有使用者清單
     */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("userID");
                String pw = rs.getString("password");
                String name = rs.getString("userName");
                String role = rs.getString("role");

                if ("Admin".equals(role)) {
                    users.add(new Admin(id, pw, name));
                } else if ("Staff".equals(role)) {
                    users.add(new Staff(id, pw, name));
                } else {
                    int foul = rs.getInt("foul");
                    long donate = rs.getLong("donate");
                    int borrowed = rs.getInt("booksBorrowed");
                    users.add(new Reader(id, pw, name, foul, donate, borrowed));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 1. 透過 userID 取得資料庫中的 Reader 物件
     */
    public static Reader getUserByID(String userID) {
        String sql = "SELECT * FROM users WHERE userID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reader(
                            rs.getString("userID"),
                            rs.getString("password"),
                            rs.getString("userName"),
                            rs.getInt("foul"),
                            rs.getLong("donate"),
                            rs.getInt("booksBorrowed")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 找不到該帳號
    }

    /**
     * 2. 更新資料庫中的讀者狀態 (借書數量、違規點數)
     */
    public static void updateReader(Reader reader) {
        String sql = "UPDATE users SET foul = ?, donate = ?, booksBorrowed = ? WHERE userID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reader.getFoul());
            stmt.setLong(2, reader.getDonate());
            stmt.setInt(3, reader.getBooksBorrowed());
            stmt.setString(4, reader.getUserID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}