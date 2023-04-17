package emailApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class EmailDb2 {

	private static Connection connection = null;
	private static Scanner scan = new Scanner(System.in);

	public static Connection connect(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String dbURL= "jdbc:mysql://localhost:3306/emailDB";
			System.out.println("Enter your username: ");
			String username = scan.nextLine();
			System.out.println("Enter your passowrd: ");
			String password = scan.nextLine();

			connection = DriverManager.getConnection(dbURL, username, password);
			
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong.");
		}
		return connection;
	}

	public void addToDatabase(EmailAddressApplication e) throws SQLException {
		
		EmailDb2.connect();
		String emailAddress = e.getEmailAddress();
		
		String str = "INSERT INTO users(f_name, l_name, email_address) VALUES(?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(str);
		statement.setString(1, e.getFName());
		statement.setString(2, e.getLName());
		statement.setString(3, emailAddress);
		int rows = statement.executeUpdate();
		System.out.println("Record inserted successfully.");
		System.out.println("Number of rows affected: " + rows);

	}
	
	public void printUser() throws SQLException {
		System.out.println("Enter email address: ");
		String emailAddress = scan.nextLine();
		
		EmailDb2.connect();
		String query = "SELECT * FROM users WHERE email_address = " + "'" + emailAddress + "'";
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("f_name");
			String lastName = rs.getString("l_name");
			
			System.out.println("ID is " + id);
			System.out.println("Name is " + firstName +  " " + lastName);
			System.out.println("Email address is " + emailAddress);
		}
	}


	public void printAllUsers() throws SQLException {
		EmailDb2.connect();
		String query = "SELECT * FROM users";

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("f_name");
			String lastName = rs.getString("l_name");
			String emailAddress = rs.getString("email_address");
			System.out.println("ID is " + id);
			System.out.println("Name is " + firstName +  " " + lastName);
			System.out.println("Email address is " + emailAddress);
		}
	}

}
