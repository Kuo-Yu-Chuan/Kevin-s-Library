package main.java.com.KevinsLibrary.userType;

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
