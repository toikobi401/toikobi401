import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitText")
public class TextSubmitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");

        // Database connection parameters
        String url = "jdbc:sqlserver://<SERVER_NAME>:<PORT>;databaseName=<DATABASE_NAME>"; // Update with actual values
        String user = "<USERNAME>"; // Update with actual username
        String password = "<PASSWORD>"; // Update with actual password

        try {
            // Load SQL Server JDBC Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // SQL Insert Statement
            String sql = "INSERT INTO UserInput (text) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userInput);
            preparedStatement.executeUpdate();

            // Close connection
            preparedStatement.close();
            connection.close();

            // Response to user
            PrintWriter out = response.getWriter();
            out.println("<html><body><h2>Text submitted successfully!</h2></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<html><body><h2>Error occurred: " + e.getMessage() + "</h2></body></html>");
        }
    }
}