package main.java.com.KevinsLibrary.userType; // 依照你的專案結構

// 加上 public，讓其他資料夾的程式也能看見它
public class Reader extends User {
    private int foul;
    private long donate;
    private int booksBorrowed;

    public Reader(String userID, String password, String userName) {
        super(userID, password, userName, "Reader");
        this.foul = 0;
        this.donate = 0;
        this.booksBorrowed = 0;
    }

    public Reader(String userID, String password, String userName, int foul, long donate, int booksBorrowed) {
        super(userID, password, userName, "Reader");
        this.foul = foul;
        this.donate = donate;
        this.booksBorrowed = booksBorrowed;
    }

    public int getFoul() { return foul; }
    public long getDonate() { return donate; }
    public int getBooksBorrowed() { return booksBorrowed; }
    public String getUserID() { return userID; }
    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }
    public void addFoul () {
        this.foul += 1;
    }
    public void setFoul(int foul) {
        this.foul = foul;
    }
    public void setDonate(long donate) {
        this.donate = donate;
    }
}
