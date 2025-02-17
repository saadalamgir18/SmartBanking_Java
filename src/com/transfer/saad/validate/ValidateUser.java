package com.transfer.saad.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.transfer.saad.bean.User;

public class ValidateUser {

	private static Matcher matcher;
	private static Pattern pattern;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean checkLength(int length, String text, boolean lengthEqueals) {

		if (lengthEqueals) {
			if (text.length() == length && text != null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (text.length() > length && text != null) {
				return true;
			} else {
				return false;
			}

		}

	}

	public static boolean haveSpace(String userName) {

		return userName.contains(" ");

	}

	public static boolean validateMaxMobile(String mobile) {

		return mobile != null && mobile.length() > 10 ? true : false;

	}

	public static boolean validateMinMobile(String mobile) {

		return mobile != null && mobile.length() < 10 ? true : false;

	}

	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() > 0 ? true : false;
	}

	public static boolean validatePassword(String password) {
		if (password != null && password.length() > 3) {
			return true;

		}
		return false;
	}

	public static boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(email);

		return matcher.matches();

	}

	public static boolean verifyPin(int pin, User user) {

		if (pin == user.getAccountPin()) {
			return true;
		}
		return false;

	}

}
