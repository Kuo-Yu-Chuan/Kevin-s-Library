package main.java.com.KevinsLibrary.userType;

/**
 * Staff 類別：圖書館職員
 * 負責第一線行政操作，具備執行借書與還書之行政權。
 */
public class Staff extends User {

    /**
     * 職員建構子
     * @param userID   職員登入帳號
     * @param password 職員登入密碼
     * @param userName 職員真實姓名
     */
    public Staff(String userID, String password, String userName) {
        // 呼叫父類別 User 的建構子，並固定角色為 "Staff"
        super(userID, password, userName, "Staff");
    }

    /**
     * 辦理借書行政作業
     * 實際執行時，必須由職員操作，並交由 ReaderManager 自動化管理中心判斷可否借閱
     * * @param reader        前來借書的讀者物件
     * @param book          欲借閱的書籍物件
     * @param readerManager 系統的核心邏輯管理中心
     * @return 借閱成功回傳 true，失敗回傳 false
     */
    public boolean borrowBook(Reader reader, Book book, ReaderManager readerManager) {
        // 由管理中心統一檢查讀者的違規紀錄(foul)與借書數量是否超過2本
        if (readerManager.canBorrow(reader)) {
            // 核准借閱，動態更新該讀者的借書紀錄清單與數量
            readerManager.modifyLoans(reader, book, true); // true 代表借書行為

            // 這裡未來會觸發 Book 類別內部的遞減在庫數邏輯
            // book.borrowBook();

            System.out.println("職員 [" + this.getUserName() + "] 成功幫讀者 [" + reader.getUserName() + "] 辦理借書：" + book.getTitle());
            return true;
        } else {
            System.out.println("行政中止：該讀者不符合借書權限。");
            return false;
        }
    }

    /**
     * 辦理還書行政作業
     * * @param reader        前來還書的讀者物件
     * @param book          欲歸還的書籍物件
     * @param readerManager 系統的核心邏輯管理中心
     */
    public void returnBook(Reader reader, Book book, ReaderManager readerManager) {
        // 更新借書紀錄清單，將該筆 Loan 標記為已歸還，並遞減讀者的 booksBorrowed 數量
        readerManager.modifyLoans(reader, book, false); // false 代表還書行為

        // 這裡未來會觸發 Book 類別內部的遞增在庫數邏輯
        // book.returnBook();

        System.out.println("職員 [" + this.getUserName() + "] 成功幫讀者 [" + reader.getUserName() + "] 辦理還書：" + book.getTitle());
    }
}