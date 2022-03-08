package com.azubike.ellipsis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    private static final String JDBC_STRING = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
    private static final String USER_NAME = "student";
    private static  final String PASSWORD ="azubike88";

    public static void main(String[] args) {
        try{

            System.out.println("Connecting to database");
            Connection connection = DriverManager.getConnection(JDBC_STRING, USER_NAME, PASSWORD);
            System.out.println("Connection Successful");


        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }
}

