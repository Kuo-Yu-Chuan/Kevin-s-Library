package main.java.com.KevinsLibrary.userType;

import java.util.HashSet;
import java.util.Set;
import main.java.com.KevinsLibrary.Book.Book;
import main.java.com.KevinsLibrary.Book.BookDAO;
import main.java.com.KevinsLibrary.Book.Position;
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
     * 即時新增/上架全新書目資料
     * @param title    書名
     * @param author   作者
     * @param isbn     國際標準書號
     */
    public void newBook(
        String title,
        String author,
        int year,
        String language,
        String isbn,
        String[] categorieString,
        String callNumber,
        String BarCode,
        byte library,
        byte floor,
        String area,
        byte available,
        String ebook
    ) {
        Set<String> categories = new HashSet<> ();
        for (int i = 0; i < categorieString.length; i++) {
            categories.add (categorieString[i]);
        }
        BookDAO.addBook (new Book (
            title,
            author,
            year,
            language,
            isbn,
            categories,
            callNumber,
            BarCode,
            new Position (library, floor, area),
            available,
            ebook
        ));
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