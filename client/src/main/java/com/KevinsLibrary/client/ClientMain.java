package main.java.com.KevinsLibrary.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ==========================================
// 1. 程式起點：歡迎/主選單視窗
// ==========================================
public class ClientMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeWindow(); // 啟動第一個視窗
            }
        });
    }
}

class WelcomeWindow extends JFrame {
    public WelcomeWindow() {
        setTitle("歡迎系統");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));

        JLabel label = new JLabel("歡迎使用本系統！");
        label.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        
        JButton startLoginButton = new JButton("前往登入");
        startLoginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

        // 點擊「前往登入」的動作
        startLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWindow(); // 打開登入視窗
                dispose();         // 關閉目前的歡迎視窗，釋放記憶體
            }
        });

        add(label);
        add(startLoginButton);
        setVisible(true);
    }
}

// ==========================================
// 2. 第二關：登入視窗
// ==========================================
class LoginWindow extends JFrame {
    public LoginWindow() {
        setTitle("系統登入");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 面板排版與元件設定
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel userLabel = new JLabel("帳號:");
        JTextField userTextField = new JTextField();
        JLabel passLabel = new JLabel("密碼:");
        JPasswordField passwordField = new JPasswordField();

        inputPanel.add(userLabel);
        inputPanel.add(userTextField);
        inputPanel.add(passLabel);
        inputPanel.add(passwordField);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton loginButton = new JButton("登入");
        JLabel errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        bottomPanel.add(loginButton);
        bottomPanel.add(errorLabel);

        // 統一字型
        Font font = new Font("微軟正黑體", Font.PLAIN, 14);
        userLabel.setFont(font); userTextField.setFont(font);
        passLabel.setFont(font); passwordField.setFont(font);
        loginButton.setFont(font); errorLabel.setFont(font);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // 登入驗證邏輯
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText().trim();
                String password = new String(passwordField.getPassword());

                // 模擬驗證 (帳號: admin, 密碼: 1234)
                if (username.equals("admin") && password.equals("1234")) {
                    new MainWindow(username); // 驗證成功，打開主程式視窗，並把使用者名稱傳過去
                    dispose();                // 關閉登入視窗
                } else {
                    errorLabel.setText("帳號或密碼錯誤！");
                }
            }
        });

        setVisible(true);
    }
}

// ==========================================
// 3. 終點站：主程式視窗
// ==========================================
class MainWindow extends JFrame {
    // 構造函數可以接收參數，這讓你可以把登入者的資訊帶到主畫面
    public MainWindow(String username) {
        setTitle("系統主頁面");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("登入成功！歡迎進入主系統，" + username + " 監控中...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 102, 204));

        // 可以在這裡加一個登出按鈕，體驗返回的邏輯
        JButton logoutButton = new JButton("登出系統");
        logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeWindow(); // 返回起點
                dispose();           // 關閉主視窗
            }
        });

        add(welcomeLabel, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}