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

    public static void addCustomer(String id, String name, String phone, String email, boolean Member){
        try {
            conn = connect();
            stmt = conn.createStatement();

            int mem = 0;
            if (Member) {
                mem = 1;
            }

            String sql = "INSERT INTO customers(CustomerID, Name, PhoneNo, Email, Member) VALUE('%s', '%s', '%s', '%s', '%d')";
            sql = String.format(sql, id, name, phone, email, mem);
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editCustomer(String id, String name, String phone, String email, boolean Member){
        try {
            conn = connect();
            stmt = conn.createStatement();

            int mem = 0;
            if (Member) {
                mem = 1;
            }

            String sql = "UPDATE customers SET Name='%s', PhoneNo='%s', Email='%s', Member='%d' WHERE CustomerID='%s'";
            sql = String.format(sql, name, phone, email, mem, id);
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String id){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = String.format("DELETE FROM customers where CustomerID = '%s'", id);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(String ProductID, String ProductName, String Type, int Price){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO products(ProductID, ProductName, Type, Price) VALUE('%s', '%s', '%s', '%d')";
            sql = String.format(sql, ProductID, ProductName, Type, Price);
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editProduct(String ProductID, String ProductName, String Type, int Price){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "UPDATE products SET ProductName='%s', Type='%s', Price='%d' WHERE ProductID='%s'";
            sql = String.format(sql, ProductName, Type, Price, ProductID);
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(String ProductID){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = String.format("DELETE FROM products where ProductID = '%s'", ProductID);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Not Done
    // Convert Image to ByteStreams then store in database
//    public static void addSubOrder(String OrderID, String ProductID, int Quantity, String Description){
//
//    }

    // Not Done
    // Convert java.util's date to java.sql's date format.
//    public static void addorder(String OrderID, String CustomerID, String OrderType, String DeliveryAddress,
//    int DeliveryPrice,)

}
