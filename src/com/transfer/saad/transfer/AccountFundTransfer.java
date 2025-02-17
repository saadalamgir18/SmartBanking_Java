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
			if(activeAcc == 1) {
				this.logout(user1);
			}else if(activeAcc == 2) {
				this.logout(user2);
			}else {
				this.login();
				
			}
		}else if(menuChoice == 2){
			if(activeAcc != 0) {
				if(activeAcc == 1) {
					this.accountInfo(user1);
					
				}else if(activeAcc == 2) {
					this.accountInfo(user2);
				}
			}else {
				this.createAccount();
			}
			
		}else if(menuChoice == 3) {
			if(activeAcc != 0) {
				if(activeAcc == 1) {
					print(user1.getHistory());
					
				}else if(activeAcc == 2) {
					print(user2.getHistory());
				}
				this.mainMenu();
				
			}else {
				this.createAccount();
			}
		}else if(menuChoice == 4) {
			if(activeAcc == 1) {
				this.FundTransfer(100, 123456, user1, user2);
			}
			
		}
		
		 else {
			this.createAccount();
		}

	}

	@Override
	public void login() {
		System.out.println("Welcome to the bank.");
		System.out.println("Enter your bank acoount number");
		String accNumber = scanner.next();
		if (accNumber.equals(user1.getAccountNumber())) {
			System.out.println("Enter 6 digit pin !!!");
			int pin = scanner.nextInt();
			if (ValidateUser.verifyPin(pin, user1)) {

				this.activeAcc = 1;
				System.out.println("Login successfull...!!");
				this.createLog(user1, "Account Login");
				this.mainMenu();

			} else {
				System.out.println("Wrong pin try again...!");
				this.login();

			}

		} else if (accNumber.equalsIgnoreCase(user2.getAccountNumber())) {

			System.out.println("Enter 6 digit pin !!!");
			int pin = scanner.nextInt();
			if (ValidateUser.verifyPin(pin, user2)) {
				this.activeAcc = 2;

				System.out.println("Login successfull 😊 ...!!");
				this.createLog(user2, "Account Login");
				this.mainMenu();

			} else {
				System.out.println("🚫 Wrong pin try again...!");
				this.login();

			}

		}

	}

	@Override
	public void createAccount() {

		if (user1.getUserName() == null) {
			user = 1;
		} else if (user2.getUserName() == null) {
			user = 2;
		} else {
			System.out.println("!! OOps only 2 user can be created.... ");
		}

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

		if (amount < 0) {
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

		if (user == 1) {
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
			user1.setType(accountType);

			this.createLog(user1, " Account Created ");
			this.accountInfo(user1);

		} else {

			user2.setAccountNumber(accNumber);
			user2.setUserName(name);
			user2.setAccountBalance(amount);
			user2.setBankName(bankName);
			user2.setAccountPin(pin);
			user2.setEmail(email);
			user2.setAccountNumber(accNumber);
			user2.setHistory(Utils.getTimeStamp());
			user2.setIfscCode(ifsc);
			user2.setMobile(mobile);
			user2.setType(accountType);

			this.createLog(user2, " Account Created ");
			this.accountInfo(user2);
		}

	}

	private void accountInfo(User user) {
		System.out.println("-----------*******-------------");
		System.out.println("-----------***[ Account Created Successfully ]***-------------");
		System.out.println("!! Account Detail !!");
		System.out.println("!!~ Bank Name => " + user.getBankName());
		System.out.println("!!~ Account Name => " + user.getUserName());
		System.out.println("!!~ Account Email => " + user.getEmail());
		System.out.println("!!~ Mobile Number => " + user.getMobile());
		System.out.println("!!~ Account Number => " + user.getAccountNumber());
		System.out.println("!!~ Account Balance => " + user.getAccountBalance());
		System.out.println("!!~ Account Type => " + user.getType());
		System.out.println("!!~ IFSC Code => " + user.getIfscCode());
		System.out.println("!!~ Account Pin => " + user.getAccountPin());
		print("-----------*******-------------");
		this.mainMenu();
	}

	private void print(String s) {
		System.out.println(s);

	}

	@Override
	public void logout(User user) {
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
	public void FundTransfer(int amount, int pin, User fromUser, User toUser) {
		if (ValidateUser.verifyPin(pin, fromUser)) {
			if (amount <= fromUser.getAccountBalance()) {

				toUser.setAccountBalance(amount + toUser.getAccountBalance());
				fromUser.setAccountBalance(fromUser.getAccountBalance() - amount);
				print("----------[ Fund Transfer successfull ]----------");
				System.out.println("!! Available balance =: " + fromUser.getAccountBalance());
				this.createLog(fromUser, amount + " Transfered to " + toUser.getUserName());
				this.createLog(toUser, amount + " Recieved from " + fromUser.getUserName());
				this.mainMenu();

			} else {
				System.out.println(" Not Enough balance in account ");
				System.out.println("--------------------------------");
				this.mainMenu();

			}

		} else {
			System.out.println("Your pin is incorrect");
		}

	}

	@Override
	public void withdraw(User user) {

		System.out.println("Enter amount");
		int amount = scanner.nextInt();
		System.out.println("Enter Pin");

		int pin = scanner.nextInt();

		if (ValidateUser.verifyPin(pin, user)) {
			if (amount <= user.getAccountBalance()) {
				user.setAccountBalance(user.getAccountBalance() - amount);
				print("----------[ Withdraw successfull ]----------");
				System.out.println("!! Available balance =: " + user.getAccountBalance());
				
				this.createLog(user, amount + " withdraw");
				this.mainMenu();

			} else {
				System.out.println("Not enough account balance !!!");
			}

		} else {
			System.out.println("Pin is not valid");
			this.mainMenu();
		}

	}

	@Override
	public void changePin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createLog(User user, String msg) {
		String history;
		if (user.getHistory() == null) {
			history = "";

		} else {
			history = user.getHistory();

		}
		user.setHistory(msg + " on " + Utils.getTimeStamp() + "\n" + history);

	}

}
