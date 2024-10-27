import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Upload your text file</h1>");
        out.println("<form action='upload' method='post' enctype='multipart/form-data'>");
        out.println("<input type='file' name='file' accept='.txt'>");
        out.println("<input type='submit' value='Upload'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        if (filePart != null && filePart.getSubmittedFileName().endsWith(".txt")) {
            InputStream inputStream = filePart.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder formattedTextBuilder = new StringBuilder();
            String line;

            // Форматируем текст
            while ((line = reader.readLine()) != null) {
                formattedTextBuilder.append(formatText(line)).append("\n");
            }

            // Сохраняем отформатированный текст в переменную
            // Переменная для хранения отформатированного текста
            String formattedText = formattedTextBuilder.toString();
            response.getWriter().println(formattedText);
        } else {
            response.getWriter().println("Please upload a valid .txt file.");
        }
    }

    // Метод для форматирования текста
    private String formatText(String text) {
        StringBuilder formatted = new StringBuilder();
        text = text.trim(); // Убираем начальные и конечные пробелы
        String indentedText = "   " + text; // Добавляем отступ 3 пробела

        // Разбиваем текст на строки длиной 80 символов
        for (int i = 0; i < indentedText.length(); i += 80) {
            int endIndex = Math.min(i + 80, indentedText.length());
            formatted.append(indentedText, i, endIndex).append("\n");
        }

        return formatted.toString();
    }
}
