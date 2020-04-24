package com.devon1337.RPG.Player;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.SDBCClassLoader;

public class DatabaseAccess {

	public static String dbURL = "jdbc:sqlserver://144.217.84.107:1433;databaseName=nightfall";
	public static String user = "sa";
	public static String pass = "a4Ce!lEr";
	public final static int GEN_LENGTH = 10;
	public final static String SERVER_NAME = "fac";
	public final static String ABC = "abcdefghijklmnopqrstuvwxyz";
	public static Random rand = new Random();
	public SDBCClassLoader classLoader = new SDBCClassLoader(new URL[0], this.getClass().getClassLoader());
	
	//public static Connection conn = DatabaseConnection.getConnection();

	public String generateREADCommand(String selectFrom, String Table, String playerName) {
		String SQL = "select " + selectFrom + " from " + Table + " where PLAYER_NAME = " + quote(playerName);
		System.out.println(SQL);
		return SQL;
	}
	
	public void initClassLoader() {
		String path = "/root/chad/minecraft/plugins/NightFallRPG/mssql-jdbc-8.2.2.jre11.jar";
		File file = new File(path);
		if(file.exists()) {
			URL url = null;
			try {
				url = file.toURI().toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			classLoader.addURL(url);
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, getClassLoader());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public SDBCClassLoader getClassLoader() { return classLoader; }
	
	/*
	public String generatePOSTLevelCommand() {
		
	}
	
	public String generatePOSTXPCommand() {
		
	}
	
	public String generatePOSTMMRCommand() {
		
	}
	*/
	
	public void createProfile(Player player) throws SQLException, ClassNotFoundException {
		int player_level = 0;
		int player_xp = 0;
		int player_mmr = 0;
		Connection conn = null;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, getClassLoader());
		
		conn = DriverManager.getConnection(getdbURL() + ";" + getUsername() + ";" + getPassword());
		
		Statement stmt = conn.createStatement();
		
		String SQL = "INSERT INTO dbo.player_stats " + "VALUES (" + generateUserID(player) + ", "
				+ "," + quote(player.getName()) + ", " + player_level + ", " + player_xp + ","
						+ player_mmr + ")";

		try {
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean exists(String userId) throws ClassNotFoundException {
		boolean e = false;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, getClassLoader());
			conn = DriverManager.getConnection(getdbURL(), getUsername(), getPassword());
			if (conn != null) {
				String SQL = "select * from dbo.player_stats where player_name = " + quote(userId);
				System.out.println(SQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				return rs.next();
			}
			/*
			 * if (isFilled(rs)) { e = true; } else { e = false; }
			 */
		} catch (SQLException l) {
			l.printStackTrace();
			return false;
		}
		return e;
	}
	
	public String generateUserID(Player player) throws SQLException, ClassNotFoundException {
		String genID = null;
		boolean unused = false;
		
		while(!unused) {
		for(int i = 0; i < GEN_LENGTH; i++) {
			int c = rand.nextInt(2);
			if(c == 0) {
				genID = genID + "" + getLetter(true, rand.nextInt(ABC.length()));
			} else {
				
			}
		}
		
		if(playerIdExists(player)) {
			unused = false;
		} else {
			unused = true;
		}
		}
		
		return SERVER_NAME + genID;
	}
	
	public static char getLetter(boolean capital, int number) {
		if(capital) {
			return Character.toUpperCase(ABC.charAt(number));
		} else {
			return ABC.charAt(number);
		}
	}
	
	public static String quote(String s) {
		return new StringBuilder().append('\'').append(s).append('\'').toString();
	}
	
	public int getLevel(Player player) throws ClassNotFoundException, SQLException {
		//Connection conn = null;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, getClassLoader());
		Connection conn = null;
		conn = DriverManager.getConnection(getdbURL(), getUsername(), getPassword());
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						generateREADCommand("player_level", "dbo.player_stats", player.getName()));
				while (rs.next()) {
					return rs.getInt("player_level");
				}
			}
		return -1;
	}
	
	public boolean playerIdExists(Player player) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, getClassLoader());
		Connection conn = null;
		conn = DriverManager.getConnection(getdbURL(), getUsername(), getPassword());
		
		if (conn != null) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					generateREADCommand("player_id", "dbo.player_stats", player.getName()));
			return rs.next();
		}
		
		return false;
	}
	
	public String getPassword() { return pass; }
	public String getUsername() { return user; }
	public String getdbURL() { return dbURL; }
	
}
