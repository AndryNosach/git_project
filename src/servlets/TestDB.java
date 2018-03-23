package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/hello22")
public class TestDB extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Hello world</h1>");
        out.println("<hr>");

        String name = "root";
        String pass = "android";
        String url = "jdbc:mysql://localhost:3306/books";
        String query = "select * from books where id='1'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection(url, name, pass);
             Statement state = con.createStatement();
             ResultSet rs = state.executeQuery(query)){

            rs.next();

            out.println("id = "+ rs.getInt("id")+"<br>");
            out.println("name = "+ rs.getString("name")+"<br>");
            out.println("author = "+ rs.getString("author")+"<br><hr>");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            out.println("DONE!");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}

