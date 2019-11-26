package sample;

import java.sql.*;
import java.time.LocalDate;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_databasesystem";
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static Connection connect(){
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addCustomer(String id, String name, String phone, String email){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO customers(CustomerID, Name, PhoneNo, Email) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, name, phone, email);
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addMember(String MemberID, String CustomerID){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO members(MemberID, CustomerID) VALUE('%s', '%s')";
            sql = String.format(sql, MemberID, CustomerID);
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Not Done
    public static void addProduct(String MemberID, String CustomerID){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO members(MemberID, CustomerID) VALUE('%s', '%s')";
            sql = String.format(sql, MemberID, CustomerID);
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
