package com.jw.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jw.dao.UsersDAOImplement;
import com.jw.service.UserService;
import com.jw.utility.PasswordUtils;

public class MenuController {

	static Scanner scanner = new Scanner(System.in);
	private UserService userService = new UserService();
	public static Logger log = LoggerFactory.getLogger(UsersDAOImplement.class);

	public MenuController() {
		// TODO Auto-generated constructor stub
		while (true) {
			this.showMainMenu();
		}

	}

	public static void closeScanner() {
		scanner.close();
	}

	private void showMainMenu() {
		System.out.println("Purchase Order System");
		System.out.println("1. Sign-in");
		System.out.println("2. Sign-up");
		System.out.println("3. About");

		String input = waitingForInput();

		String menuSelected = "Error";
		System.out.println(input);
		switch (input) {
		case "1":
			menuSelected = "1";
			break;
		case "2":
			menuSelected = "2";
			break;
		case "3":
			menuSelected = "3";
			break;
		default:
			menuSelected = "Error";
			break;
		}

		if (menuSelected.equals("1")) {
			showSignInPage();
		} else if (menuSelected.equals("2")) {
			showSignUpPage();
		} else if (menuSelected.equals("3")) {
			showAboutPage();
			showMainMenu();
		} else {
			System.out.println("Input Error, Please input 1, 2, or 3");
			showMainMenu();
		}

	}

	private String waitingForInput() {
		String input = scanner.nextLine();
		return input;
	}

	public static boolean isValidUserName(String userName) {
		Pattern USER_NAME = Pattern.compile("^([a-zA-Z]+[a-zA-Z0-9]*)([\\w@.]*{2,})+$");
		// Rule: 1. Start with a English Alphabet 2. Could be an email
		Matcher matcher = USER_NAME.matcher(userName);
		return matcher.matches();
	}

	public static boolean isValidPassword(String password) {
		Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,30}$");
		// Rule: 1. Start with a English Alphabet 2. Has a lower case 3. Has an upper
		// case 4. Can contain other symbols 4. 4~30 characters long
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		Pattern emailPattern = Pattern.compile("([\\w@.]*)+$");
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	private void showSignInPage() {
		System.out.println("If you want to return to last screen, please enter \"<-\"");
		System.out.println("Please Enter User Name:");
		String input = waitingForInput();
		switch (input) {
		case "<-":
			return;
		}

		if (isExistedinDB(input)) {
			System.out.println("Please Enter the Password:");
			String password = waitingForInput();
			ResultSet rs = userService.findInfoWithUserName(input);
			boolean isCorrectPassword = isCorrectPassword(rs, password);
			try {

				if (isCorrectPassword) {
					String accountType = rs.getString("account_type");
					int user_id = rs.getInt("user_id");
					LandingPageController landingPage = new LandingPageController(accountType, user_id);
				} else {
					System.out.println("Password is incorrect.");
					showSignInPage();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("User doesn't exist.");
			showSignInPage();
		}

	}

	private boolean isCorrectPassword(ResultSet rs, String password) {
		try {
			if (rs.next()) {
				String salt = rs.getString("salt");
				String hashedPassword = rs.getString("hashed_password");
				return PasswordUtils.verifyThePlainTextPassword(password, hashedPassword, salt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	private boolean isExistedinDB(String userName) {
		boolean isExisted = userService.findWithUserName(userName);
		return isExisted;
	}

	private void showSignUpPage() {

		System.out.println("Please Enter An User Name or Email for Signup:");
		String input = waitingForInput();
		switch (input) {
		case "<-":
			return;
		}
		boolean valid = isValidUserName(input);
		// search the database whether the user name is registered
//		boolean isExistedinDB = 
		if (valid == true) {
			valid = !isExistedinDB(input);
			if (valid == false) {
				System.out.println("Username Aready Exists in the database. Please give it another username.");
				showSignUpPage();
			} else {
				String password = passwordSignup();
				if (password == null) {
					return;
				}
				String email = enterEmail();
				if (email != null) {
					String salt = PasswordUtils.generateSalt(512).get();
					Optional<String> hashedPassword = PasswordUtils.hashThePlainTextPassword(password, salt);
					String hashedPasswordStr = hashedPassword.get();
					boolean isSuccessful = userService.insertAnUser(input, salt, hashedPasswordStr, email);
					ResultSet rs = userService.findInfoWithUserName(input);
					try {
						if (isSuccessful && rs.next()) {
							int user_id;

							user_id = rs.getInt("user_id");
							LandingPageController landingPageController = new LandingPageController("Customer",
									user_id);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Rules: " + "1. Start with a English Alphabet " + "2. It can be an email "
					+ "3.It doesn't allow other symbols like `~!@#$%^&*()");
			showSignUpPage();
		}

		// check the db whether the user name is in the table
//		Boolean userNameExist = checkUserNameInDB();
	}

	private String passwordSignup() {
		System.out.println("Please Enter A Password for Signup:");
		String passwordInput = waitingForInput();
		switch (passwordInput) {
		case "<-":
			return null;
		}
		boolean validPassword = isValidPassword(passwordInput);

		if (validPassword == true) {
			boolean passwordSuccess = passwordConfirmation(passwordInput);
			log.info("passwordSuccess: " + passwordSuccess);
			if (passwordSuccess) {
				log.debug("password input: " + passwordInput);
				return passwordInput;
			} else {
				return null;
			}
		} else {
			System.out.println(
					"Password is invalid. " + "Rule: 1. Start with a English Alphabet " + "2. Has a lower case "
							+ "3. Has an upper case " + "4. Can contain other symbols" + "5. 4~30 characters long");
			return passwordSignup();
		}
	}

	private String enterEmail() {
		System.out.println("Please enter your email:");
		String email = waitingForInput();
		switch (email) {
		case "<-":
			return null;
		}
		boolean isValidEmail = isValidEmail(email);
		if (isValidEmail)
			return email;
		return null;
	}

	private boolean passwordConfirmation(String password) {
		System.out.println("Please Re-enter the Password for Confirmation:");
		String passwordREInput = waitingForInput();
		switch (passwordREInput) {
		case "<-":
			return false;
		}
		if (password.equals(passwordREInput)) {
			return true;
		} else {
			return passwordConfirmation(password);
		}
	}

	private void showAboutPage() {
		System.out.println("This is a JW LLC's Product Order System");
	}

}
