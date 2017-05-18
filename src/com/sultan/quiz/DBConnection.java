package com.sultan.quiz;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class DBConnection {

	/*
	 * Method to create DB Connection
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return con;
		}
	}

	/*
	 * Methods to check whether uname and password are correct
	 * 
	 * @param uname
	 * 
	 * @param password
	 * 
	 * @returns
	 * 
	 * @throws Exception
	 */
	public static boolean checkLogin(String uname, String password) throws Exception {
		boolean isUserAvailable = false;
		Connection dbCon = null;
		try {
			try {
				dbCon = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();

			}
			Statement statement = dbCon.createStatement();
			String query = "SELECT * FROM user WHERE username= '" + uname + "' AND password='" + password + "'";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				isUserAvailable = true;
			}

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO: handle exception
			if (dbCon != null) {
				dbCon.close();
			}
			throw e;
		} finally {
			if (dbCon != null) {
				dbCon.close();
			}
		}
		return isUserAvailable;
	}

	/*
	 * Method to insert username and password in db
	 * 
	 * @param name
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */

	public static boolean insertUser(String name, String userName, String password) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbCon = null;
		try {
			try {
				dbCon = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Statement statement = dbCon.createStatement();
			String query = "INSERT into user(name, username, password) values('" + name + "','" + userName + "','"
					+ password + "')";
			int records = statement.executeUpdate(query);
			// When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbCon != null) {
				dbCon.close();

			}
			throw e;
		} finally {
			if (dbCon != null) {
				dbCon.close();
			}
		}
		return insertStatus;
	}
}
