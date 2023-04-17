package emailApp;

import java.sql.SQLException;
import java.util.Scanner;

class EmailAddressApplication {
	
	private String fName;
	private String lName;
	private String emailAddress;
	private static Scanner scan = new Scanner(System.in);
	
	public EmailAddressApplication() {
		
	}
	
	public String getFName() {
		return fName;
	}
	
	private void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	private void setLName(String lName) {
		this.lName = lName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	private void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	public String toString() {
		return "EmailAddressApplication [fName=" + fName + ", lName=" + lName
				+ ", emailAddress=" + emailAddress + "]";
	}

	public void createAnEmailAddress() throws SQLException {
		
		System.out.println("Enter your first name");
		String firstName = scan.nextLine();
		this.setFName(firstName);
		
		System.out.println("Enter your last name");
		String lastName = scan.nextLine();
		this.setLName(lastName);
		
		System.out.println("Create a new email: ");
		String email = scan.nextLine();
		String site = "@fsc.com";
		this.setEmailAddress(email + site);
		
		boolean valid = EmailValidator.validate(this.getEmailAddress());
		while (!valid) {
			System.out.println("This email address already exists.");
			System.out.println("Create a new email address.");
			email = scan.nextLine();
			this.setEmailAddress(email + site);
			valid = EmailValidator.validate(this.getEmailAddress());
		}
		
		System.out.println("Email created.");
		System.out.println("Your new email: " + this.getEmailAddress());
		
		
		EmailDb2 db = new EmailDb2();
		db.addToDatabase(this);
		
	}
}
