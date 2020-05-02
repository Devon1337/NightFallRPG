package com.devon1337.RPG.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.devon1337.RPG.Utils.JDBCClassLoader;

public class DatabaseAccess {

	public JDBCClassLoader jdbc = new JDBCClassLoader();
	
	public void testConn() {
		// TODO Auto-generated method stub

		String url = "jdbc:sqlserver://localhost:1433;databaseName=nightfall;user=sa;password=a4Ce!lEr";
		
		jdbc.test();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement();) {
			 createProfile(stmt);
			 
			 /*
	            String SQL = "SELECT TOP 10 * FROM dbo.player_stats";
	            ResultSet rs = stmt.executeQuery(SQL);

	            // Iterate through the data in the result set and display it.
	            while (rs.next()) {
	                System.out.println("Player Name:" + rs.getString("player_name") + " Player Level:" + rs.getInt(3));
	            }
	            */
	        }
	        // Handle any errors that may have occurred.
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	public void createProfile(Statement stmt) {

		System.out.println("Creating Profile");
		String SQL = "INSERT INTO dbo.player_stats " + "VALUES (" + quote("abc124a") + ", "
				+ quote("CharlesTree") + ", " + "3" + ", " + "300" + ", " + "1500" + " )";

		try {
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String quote(String s) {
		return new StringBuilder().append('\'').append(s).append('\'').toString();
	}
	
}
