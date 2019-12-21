package sample;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_databasesystem";
    static final String USER = "root";
//    passwordnya Aleep
//    static final String PASS = "2201798295Binus";
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

    public static void addOrder(){
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

    public static void addProductType(String TypeID, String Type){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO product_type(TypeID, Type) value('%s', '%s')";
            sql = String.format(sql, TypeID, Type);
            stmt.execute(sql);

            System.out.println(String.format("Added %s to product_type", TypeID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProductTypeByID(String TypeID){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = String.format("DELETE FROM product_type where TypeID = '%s'", TypeID);
            stmt.execute(sql);

            System.out.println(String.format("Deleted %s from product_type", TypeID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProductTypeByType(String Type){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = String.format("DELETE FROM product_type where Type = '%s'", Type);
            stmt.execute(sql);

            System.out.println(String.format("Deleted %s from product_type", Type));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAllTypes() throws NullPointerException {
        ArrayList<String> listofTypes = new ArrayList<>();
        try {
            conn = connect();
            String sql = "SELECT Type FROM product_type";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                listofTypes.add(rs.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofTypes;
    }

    public static boolean isProductTypeExist(String Type) throws SQLException {
        conn = connect();

        String sql = "SELECT * FROM product_type WHERE Type = '%s'";
        sql = String.format(sql, Type);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        // Checks if ProductType exists
        int counter = 0;
        while (rs.next()){
            counter++;
        }

        if (counter != 0) {
            System.out.println(String.format("%s exists in product_type", Type));
            return true;
        } else {
            System.out.println(String.format("%s doesn't exists in product_type", Type));
            return false;
        }
    }

    public static String getTypeID(String Type) throws SQLException{
        conn = connect();

        String sql = "SELECT * FROM product_type WHERE Type = '%s'";
        sql = String.format(sql, Type);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        rs.next();
        return rs.getString("TypeID");
    }

    public static String getType(String TypeID) throws SQLException{
        conn = connect();

        String sql = "SELECT * FROM product_type WHERE TypeID = '%s'";
        sql = String.format(sql, TypeID);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        rs.next();
        return rs.getString("Type");
    }

    public static String getLastTypeID() throws SQLException{
        try {
            conn = connect();

            String sql = "SELECT MAX(TypeID) FROM product_type";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            rs.next();

            // If no data exists yet
            if (rs.getString("MAX(TypeID)") == null){
                System.out.println("Last TypeID = TYP00000");
                return "TYP00000";
            }

            // If data exists
            String LastTypeID = rs.getString("MAX(TypeID)");
            System.out.println(String.format("Last TypeID = %s", LastTypeID));
            return LastTypeID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addProduct(String ProductID, String ProductName, String TypeID, int Price){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO products(ProductID, ProductName, TypeID, Price) VALUE('%s', '%s', '%s', '%d')";
            sql = String.format(sql, ProductID, ProductName, TypeID, Price);
            stmt.execute(sql);

            System.out.println(String.format("Added %s to product", ProductID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editProduct(String ProductID, String ProductName, String TypeID, int Price){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "UPDATE products SET ProductName='%s', TypeID='%s', Price='%d' WHERE ProductID='%s'";
            sql = String.format(sql, ProductName, TypeID, Price, ProductID);
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

            System.out.println(String.format("Deleted %s from product", ProductID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getProductName(String ProductID) throws SQLException{
        conn = connect();

        String sql = "SELECT ProductName FROM product WHERE ProductID = '%s'";
        sql = String.format(sql, ProductID);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        rs.next();
        return rs.getString("ProductName");
    }

    public static int getProductPrice(String ProductID) throws SQLException{
        conn = connect();

        String sql = "SELECT Price FROM product WHERE ProductID = '%s'";
        sql = String.format(sql, ProductID);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        rs.next();
        return rs.getInt("Price");
    }

    public static boolean isProductTypeExistInProduct(String TypeID) throws SQLException{
        conn = connect();

        String sql = "SELECT * FROM products WHERE TypeID = '%s'";
        sql = String.format(sql, TypeID);
        ResultSet rs = conn.createStatement().executeQuery(sql);

        // Checks if ProductType exists
        int counter = 0;
        while (rs.next()){
            counter++;
        }

        if (counter != 0) {
            System.out.println(String.format("TypeID %s exists in products", TypeID));
            return true;
        } else {
            System.out.println(String.format("TypeID %s doesn't exists in product_type", TypeID));
            return false;
        }
    }

    public static void addOrder(String OrderID, String CustomerID, String OrderType, String DeliveryAddress, int DeliveryPrice, LocalDate OrderDate, LocalDate DeliveryDate, String OrderStatus, int Payment, int Discount){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO orders(OrderID, CustomerID, OrderType, DeliveryAddress, DeliveryPrice, OrderDateTime, DeliveryDateTime, OrderStatus, Payment, Discount) VALUE('%s', '%s', '%s', '%s', '%d', '%s', '%s', '%s', '%d', '%d')";
            sql = String.format(sql, OrderID, CustomerID, OrderType, DeliveryAddress, DeliveryPrice, OrderDate, DeliveryDate, OrderStatus, Payment, Discount);
            stmt.execute(sql);

            System.out.println(String.format("Added %s to orders", OrderID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSubOrder(String OrderID, String ProductID, int Qty, String Description, String DescriptionPhoto){
        try {
            conn = connect();
            stmt = conn.createStatement();

            String sql = "INSERT INTO suborders(OrderID, ProductID, Qty, Description, DescriptionPhoto) VALUE('%s', '%s', '%d', '%s', '%s')";
            sql = String.format(sql, OrderID, ProductID, Qty, Description, DescriptionPhoto);
            stmt.execute(sql);

            System.out.println(String.format("Added %s to sub-orders", OrderID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Get weekly sum
//    SELECT
//    SUM(nb_like) AS nb_like,
//    CONCAT
//            (
//                    STR_TO_DATE(CONCAT(YEARWEEK(date, 2), ' Sunday'), '%X%V %W'),
//            '-',
//    STR_TO_DATE(CONCAT(YEARWEEK(date, 2), ' Sunday'), '%X%V %W') + INTERVAL 6 DAY
//  ) AS week
//    FROM fb_stats
//    GROUP BY YEARWEEK(date, 2)
//    ORDER BY YEARWEEK(date, 2);
}