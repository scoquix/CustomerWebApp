package com.scoquix.testDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name="TestDB",urlPatterns = "/testDB")
public class TestDbServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //setup connection variables
        String user = "springstudent";
        String pass = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        //get connection to the database
        try{
            PrintWriter out = response.getWriter();

            out.println("Connection to database: "+jdbcUrl);

            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user,pass);

            out.println("Success!");
            myConn.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
