/*
 * Program: CS485_lab4
 * Purpose: The program is designed to illustrate 1) the use of SQL connector 
 * to connect to and update on MySQL database; 2) the use of JSP to create a
 * Web page for data entry. To make the program work, both JDK, Apache, MySQL
 * need to be installed.
 * @copyright the program is intended for class use, it should not be distributed
 * outside the class without permission from the instructor, Dr. Mingrui Zhang  
 */
package db;
import java.sql.*;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBentry {
	static DBentry instance = new DBentry();
	Connection dbconn;
	ResultSet results = null;
	PreparedStatement sql;
	String dpwd = null;
	StringBuilder sb = new StringBuilder();
	
	//change URL to your database server as needed
	String dbPath="jdbc:mysql://localhost:3306/lab5";
	
	public static DBentry getInstance() {
		if (instance==null) {
			instance = new DBentry();
		}
		return instance;
	}
	
	//Establish connection to MySQL server
	public Connection newConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {	
				Properties cProps = new Properties();
				cProps.setProperty("user", "root");
				cProps.setProperty("password", "CCrun123");
				cProps.setProperty("useSSL", "false");
				cProps.setProperty("serverTimezone", "UTC");
				dbconn = DriverManager.getConnection(dbPath, cProps);
				System.out.println("gain the connection");
				return dbconn;
			}
			catch (Exception s){
				s.printStackTrace();} //Changed to printStackTrace()
		}
		catch (Exception err){
			err.printStackTrace(); //Changed to printStackTrace()
		}
		return null;
	}
	
	public ResultSet selectStatement( String query ) {
		try {
			
			dbconn=instance.newConnection();
			sql=dbconn.prepareStatement(query);
			ResultSet results;
			results=sql.executeQuery();
			System.out.println("query="+query);
	
			//WARNING!
			//Need to process ResultSet before closing connection
			return results;
		}
		catch (Exception err) {
			System.out.println(err.getMessage());
			return null;
		}
	}

	public boolean insert( String query) throws Exception { //removed Exception handling
			System.out.println("query="+query);
			instance.newConnection();
			sql=dbconn.prepareStatement(query);
			sql.executeUpdate(query);
			dbconn.close();
			return true;
	}
	
	public boolean entry( String credit ) {
		Session session = null;
		SessionFactory factory = null;
		boolean success = false;
				
		try {
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.getCurrentSession();
			/*
			instance.DBentry("INSERT INTO cs485_lab.sports (`sports`) " +
								"VALUES ( '"+sports+"');");	 */
			Purchase purchase = new Purchase(credit);
			
			Transaction tx=null;
			try {
				tx = session.beginTransaction();
				//Save java object Sport to database
				session.save(purchase);
				session.flush();
				tx.commit();
				success = true;
			}catch(Exception se) {
				if (tx != null) tx.rollback();
				success = false;
				se.printStackTrace();
			}
		}
		catch ( Exception err ) {
			err.printStackTrace(); //Changed to printStackTrace()
			success = false;
		} finally {
			if (factory != null) factory.close();
			if (session != null && session.isOpen()) session.close();
		}
		return success;
	}
	
	public boolean addUser(String username, String password) {
		Session session = null;
		SessionFactory factory = null;
		boolean success = false;
				
		try {
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.getCurrentSession();
			/*
			instance.DBentry("INSERT INTO cs485_lab.sports (`sports`) " +
								"VALUES ( '"+sports+"');");	 */
			User user = new User(username, password);
			System.out.println("hey hey hey");
			
			Transaction tx=null;
			try {
				tx = session.beginTransaction();
				//Save java object Sport to database
				session.save(user);
				session.flush();
				tx.commit();
				success = true;
			}catch(Exception se) {
				if (tx != null) tx.rollback();
				success = false;
				se.printStackTrace();
			}
		}
		catch ( Exception err ) {
			err.printStackTrace(); //Changed to printStackTrace()
			success = false;
		} finally {
			if (factory != null) factory.close();
			if (session != null && session.isOpen()) session.close();
		}
		return success;
	}
	
	public boolean userLookup(String username, String password) throws SQLException {
		try {
		ResultSet results = this.selectStatement("select * from ouruser where username='" + username + 
				"' and password='" + password + "';");
		if (results.next()) {
			return true;
		} else {
			return false;
		}
		} catch (Exception e) {
			throw e;
		} finally {
			dbconn.close();
		}
	}
	
	public static void main(String[] args) {	
		instance.entry("Golf");
	}
}