package emailApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

class EmailValidator {
	
	private static Connection connection  = null;
	private static boolean doesNotExist;

	public static boolean validate(String emailAddress) {
		
		int count = 0;
		
		try {
			connection = EmailDb2.connect();
			
			String query = "SELECT * FROM users WHERE email_address = '" + emailAddress + "'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				count++;
			}
			
			System.out.println(count == 0);
			doesNotExist = count == 0;
			
		} catch (Exception e1) {
			throw new RuntimeException("Something went wrong");
		}		
		
		return doesNotExist;

	}
	
}
