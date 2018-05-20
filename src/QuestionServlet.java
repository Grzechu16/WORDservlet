import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionServlet extends HttpServlet {
    int id, answer;
    String text, photo;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/WORD", "postgres", "postgres"); // <== Check!
            // conn = DriverManager.getConnection(
             //       "jdbc:postgresql://http://127.0.0.1:56162/WORD", "postgres", "postgres"); // <== Check!
            stmt = conn.createStatement();

            String sqlStr = "select * from questions where id=1 ";
            ResultSet rset = stmt.executeQuery(sqlStr);
            List<Question> questions = new ArrayList<>();
            int count = 0;
            while(rset.next()) {

                id = rset.getInt("id");
                answer = rset.getInt("answer");
                text = rset.getString("text");
                photo = rset.getString("photo");

                Question question = new Question(id,text,photo,answer);
                questions.add(question);
                count++;
            }
            out.print(new Gson().toJson(questions));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            out.close();  // Close the output writer
            try {
                // Step 5: Close the resources
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}