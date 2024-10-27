package mysql.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                // Проверка на наличие символов верхнего и нижнего регистра
                if (!isPasswordValid(password)) {
                    out.println(generateHtmlResponse("Пароль должен содержать как минимум одну заглавную и одну строчную букву.", "error"));
                    return; // Прерываем выполнение, если пароль недействителен
                }

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

                String checkSql = "SELECT * FROM users WHERE username = ?";
                PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
                checkPstmt.setString(1, username);
                if (checkPstmt.executeQuery().next()) {
                    out.println(generateHtmlResponse("Такой пользователь уже существует. Выберите другой логин", "error"));
                } else {
                    String hashedPassword = hashPassword(password); // Хешируем пароль
                    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, hashedPassword);
                    pstmt.executeUpdate();
                    out.println(generateHtmlResponse("Регистрация прошла успешно! Теперь вы можете залогиниться.", "success"));
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println(generateHtmlResponse("Ошибка: " + e.getMessage(), "error"));
            }
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании пароля", e);
        }
    }

    private boolean isPasswordValid(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
        }
        return hasUpperCase && hasLowerCase;
    }

    private String generateHtmlResponse(String message, String messageType) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Response</title>" +
                "    <link rel=\"stylesheet\" href=\"css/register.css\">" +
                "</head>" +
                "<body>" +
                "    <div class=\"message-container\">" +
                "        <div class=\"message-box " + messageType + "\">" +
                "            <p>" + message + "</p>" +
                "            <a href=\"login.html\">Вернуться ко входу</a>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}
