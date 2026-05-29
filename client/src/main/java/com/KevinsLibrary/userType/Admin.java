package main.java.com.KevinsLibrary.userType;
import main.java.com.KevinsLibrary.Book.Book;
/**
 * Admin 類別：系統管理員
 * 具備最高權限，與學生/職員帳號分離。負責書目資料維護與特定學號停權操作。
 */
public class Admin extends User {

    /**
     * 管理員建構子
     * @param userID   管理員登入帳號
     * @param password 管理員密碼
     * @param userName 管理員名稱
     */
    public Admin(String userID, String password, String userName) {
        // 呼叫父類別 User 的建構子，並固定角色為 "Admin"
        super(userID, password, userName, "Admin");
    }

    /**
     * 修改書籍標題 (由管理員修改書本資料)
     * @param book     欲修改的書籍物件
     * @param newTitle 新的書名
     */
    public void modifyBookTitle(Book book, String newTitle) {
        book.modifyTitle(newTitle);
        System.out.println("管理員 [" + this.getUserName() + "] 已將書籍修改新書名為：" + newTitle);
    }

    /**
     * 修改書籍作者 (由管理員修改書本資料)
     * @param book       欲修改的書籍物件
     * @param newAuthor  新的作者名稱
     */
    public void modifyBookAuthor(Book book, String newAuthor) {
        book.modifyAuthor(newAuthor);
        System.out.println("管理員 [" + this.getUserName() + "] 已將書籍修改新作者為：" + newAuthor);
    }

    /**
     * 即時新增/上架全新書目資料
     * @param title    書名
     * @param author   作者
     * @param isbn     國際標準書號
     */
    public void newBook(String title, String author, String isbn) {
        // 這裡未來會連動 BookDAO 執行 SQL 的 INSERT 指令將新書寫入資料庫
        System.out.println("管理員 [" + this.getUserName() + "] 成功上架全新書目：" + title + " (ISBN: " + isbn + ")");
    }

    /**
     * 建立新的書籍主題分類
     * @param categoryName 分類名稱 (如: 文學、科學)
     */
    public void newCategory(String categoryName) {
        System.out.println("管理員 [" + this.getUserName() + "] 成功建立新主題分類：" + categoryName);
    }

    /**
     * 管理端維運：針對特定學號/帳號進行帳號停權操作 (SUSPENDED)
     * @param targetUserID 欲停權的學生學號或讀者帳號
     */
    public void suspendAccount(String targetUserID) {
        // 這裡未來會連動 UserDAO 執行 SQL UPDATE，將該帳號 status 改為 "SUSPENDED"
        System.out.println("最高權限管制：管理員已即時將違規帳號 [" + targetUserID + "] 執行停權(SUSPENDED)操作。");
    }
}