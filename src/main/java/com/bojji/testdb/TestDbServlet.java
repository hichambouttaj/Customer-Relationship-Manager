//package com.bojji.testdb;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//@WebServlet(name = "TestDbServlet", urlPatterns = "/TestDbServlet")
//public class TestDbServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // setup connection variables
//        String user = "root";
//        String pass = "toor";
//
//        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker";
//        String driver = "com.mysql.cj.jdbc.Driver";
//
//        // get connection to database
//        Connection connection = null;
//        try {
//            PrintWriter out = resp.getWriter();
//            out.println("Connection to database: " + jdbcUrl);
//            Class.forName(driver);
//            connection = DriverManager.getConnection(jdbcUrl, user, pass);
//            out.println("Success Connection!!!");
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new ServletException(e);
//        }finally {
//            try{
//                if(connection != null){
//                    connection.close();
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                throw new ServletException(e);
//            }
//        }
//    }
//}
