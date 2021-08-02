package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
  static Connection con;

  public static Boolean startConnection() {
    String Host = "jdbc:mysql://localhost:3306/db";
    String username = "root";
    String password = "pass";

    Boolean success = false;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(Host, username, password);
      System.out.println("Database Connected.");
      success = true;
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    }
    return success;
  }

  public static ResultSet login(String username, String password) {
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM users WHERE username='" + username + "' and password='" + password + "';";
      Statement stmt = con.createStatement();
      rs = stmt.executeQuery(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static int register(String id, String username, String password, int role) {
    int rs = 0;
    try {
      String query = "INSERT INTO users VALUES ('" + id + "', '" + username + "', '" + password + "', " + role + ");";
      String attendanceQuery = "INSERT INTO attendance VALUES ('" + id + "', 0);";
      Statement stmt = con.createStatement();
      stmt.executeUpdate(attendanceQuery);
      rs = stmt.executeUpdate(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static ResultSet getMarks(String id) {
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM marks WHERE id='" + id + "';";
      Statement stmt = con.createStatement();
      rs = stmt.executeQuery(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static int updateMarks(String id, String subject, String marks) {
    int rs = -1;
    try {
      String query = "INSERT INTO marks VALUES ('" + id + "', '" + subject + "', " + marks + ");";
      Statement stmt = con.createStatement();
      rs = stmt.executeUpdate(query);
      System.out.println(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static ResultSet getAttendance(String id) {
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM attendance WHERE id='" + id + "';";
      Statement stmt = con.createStatement();
      rs = stmt.executeQuery(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static int updateAttendance(String id, String attendance) {
    int rs = -1;
    try {
      String query = "UPDATE `db`.`attendance` SET `attendance` = '" + attendance + "' WHERE (`id` = '" + id + "');";
      Statement stmt = con.createStatement();
      rs = stmt.executeUpdate(query);
      System.out.println(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static ResultSet getStudents() {
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM users WHERE role='1';";
      Statement stmt = con.createStatement();
      rs = stmt.executeQuery(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public static void closeConnection() {
    try {
      con.close();
      System.out.println("Database Connection Closed.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
