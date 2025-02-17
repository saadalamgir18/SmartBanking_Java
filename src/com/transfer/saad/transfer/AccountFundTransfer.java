package com.transfer.saad.transfer;

import java.util.Scanner;

import com.transfer.saad.bean.User;
import com.transfer.saad.service.UserService;
import com.transfer.saad.utils.Utils;
import com.transfer.saad.validate.ValidateUser;

public class AccountFundTransfer implements UserService {

	User user1, user2;
	int user, activeAcc;
	Scanner scanner = new Scanner(System.in);

	private String accountType;

	{
		user1 = new User();
		user2 = new User();

	}

	public static void main(String ss[]) {
		AccountFundTransfer fundTransfer = new AccountFundTransfer();
		fundTransfer.mainMenu();

//		scanner.close();

	}

	private void mainMenu() {

		System.out.println("");

		if (activeAcc != 0) {
			System.out.println("1. Logout");
			System.out.println("2. My Account Details");
			System.out.println("3. Account Activity");
			System.out.println("4. Fund Transfer");
			System.out.println("5. Withdraw");
			System.out.println("6. Change Pin");
		} else {
			System.out.println("1. Login");
			System.out.println("2. Create Account");

		}
		System.out.println("");

		int menuChoice = scanner.nextInt();
		if (menuChoice == 1) {
			this.login();
		} else {
			this.createAccount();
		}

	}

	@Override
	public void login() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createAccount() {
		System.out.println("----------Fill Details to Continue-------");

		System.out.println("=======| Enter Bank Name |=======");
		String bankName = scanner.next();

		if (!ValidateUser.checkLength(3, bankName, false)) {
			print("!! Bank Name is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter Full Name |=======");

		String name = scanner.next();

		if (!ValidateUser.checkLength(2, name, false)) {
			print("!! Name is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter EMAIl Address |=======");

		String email = scanner.next();

		if (!(ValidateUser.checkLength(10, email, false)) && !(ValidateUser.validateEmail(email))) {
			print("!! Email is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter Mobile Number  |=======");
		String mobile = scanner.next();

		if (ValidateUser.validateMaxMobile(mobile) || ValidateUser.validateMinMobile(mobile)) {
			print("!! Phone Number is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter IFSC Code |=======");
		String ifsc = scanner.next();

		if (!ValidateUser.checkLength(11, ifsc, true)) {
			print("!! IFSC is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter Account Type |=======");
		System.out.println("1. Saving");
		System.out.println("2. Current");

		int accType = scanner.nextInt();

		if (accType == 1) {
			accountType = "Saving";
		} else if (accType == 2) {
			accountType = "Current";
		} else {
			print("!! Account type is not valid or empty !!");
			createAccount();

		}

		System.out.println("=======| Enter Amount( > 0 ) you want to save |=======");
		int amount = scanner.nextInt();
		
		if(amount < 0) {
			print("!! Sorry you can not open an account with 0(Zero) balance !!");
			createAccount();
			
		}
			

		System.out.println("=======| Create 6 Digit Pin |=======");
		int pin = scanner.nextInt();
		
		if (!ValidateUser.checkLength(6, String.valueOf(pin), true)) {
			print("!! Pin must be 6 digits !!");
			createAccount();

		}

//		user1.setBankName(bankName);

		System.out.println("=======| Generating 11 Digit Account Number |=======");
		
		String accNumber = Utils.generateAccNum();
		
		user1.setAccountNumber(accNumber);
		user1.setUserName(name);
		user1.setAccountBalance(amount);
		user1.setBankName(bankName);
		user1.setAccountPin(pin);
		user1.setEmail(email);
		user1.setAccountNumber(accNumber);
		user1.setHistory(Utils.getTimeStamp());
		user1.setIfscCode(ifsc);
		user1.setMobile(mobile);

	}

	private void print(String s) {
		System.out.println(s);

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void myAccountDetails() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountActivity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FundTransfer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePin() {
		// TODO Auto-generated method stub

	}

}
