 package com.devon1337.RPG.Player;
 
 import com.devon1337.RPG.Utils.JDBCClassLoader;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 
 
 
 public class DatabaseAccess
 {
/* 12 */   public JDBCClassLoader jdbc = new JDBCClassLoader();
 
 
   
   public void testConn() {
    this.jdbc.test();
     
     try {
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } catch (ClassNotFoundException e1) {
       
      e1.printStackTrace();
     } 
   }
 
 
 
   
   public void createProfile(Statement stmt) {
     System.out.println("Creating Profile");
     String SQL = "INSERT INTO dbo.player_stats VALUES (" + quote("abc124a") + ", " + 
       quote("CharlesTree") + ", " + "3" + ", " + "300" + ", " + "1500" + " )";
     
     try {
      stmt.executeUpdate(SQL);
     } catch (SQLException e) {
       e.printStackTrace();
     } 
   }
   
   public String quote(String s) {
    return '\'' + s + '\'';
   }
 }