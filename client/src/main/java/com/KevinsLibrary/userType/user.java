package model;

/**
 * User 類別：代表圖書館系統的基礎使用者帳號資料
 */
public class User {
    protected String userID;   // 使用者帳號/身分證字號
    protected String password; // 使用者密碼
    protected String userName; // 使用者名稱
    protected String role;     // 角色 (Reader, Staff, Admin)

    /**
     * 基礎建構子
     */
    public User(String userID, String password, String userName, String role) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    // Getters
    public String getUserID() { return userID; }
    public String getPassword() { return password; }
    public String getUserName() { return userName; }
    public String getRole() { return role; }
}

/**
 * Reader 類別：一般讀者
 */
class Reader extends User {
    private int foul;           // 不良紀錄點數 (為了與JDBC相容，使用int代替byte)
    private long donate;        // 累計捐贈金額
    private int booksBorrowed;  // 已借閱數量

    // 供註冊用的建構子 (預設數值為0)
    public Reader(String userID, String password, String userName) {
        super(userID, password, userName, "Reader");
        this.foul = 0;
        this.donate = 0;
        this.booksBorrowed = 0;
    }

    // 供 DAO 從資料庫讀取資料用的建構子
    public Reader(String userID, String password, String userName, int foul, long donate, int booksBorrowed) {
        super(userID, password, userName, "Reader");
        this.foul = foul;
        this.donate = donate;
        this.booksBorrowed = booksBorrowed;
    }

    public int getFoul() { return foul; }
    public long getDonate() { return donate; }
    public int getBooksBorrowed() { return booksBorrowed; }
}

/**
 * Staff 類別：圖書館職員
 */
class Staff extends User {
    public Staff(String userID, String password, String userName) {
        super(userID, password, userName, "Staff");
    }
}

/**
 * Admin 類別：系統管理員
 */
class Admin extends User {
    public Admin(String userID, String password, String userName) {
        super(userID, password, userName, "Admin");
    }
}