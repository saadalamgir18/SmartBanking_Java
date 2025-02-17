package com.transfer.saad.service;

import com.transfer.saad.bean.User;

public interface UserService {
	public void login();
	public void createAccount();
	public void logout();
	public void myAccountDetails();
	public void accountActivity();
	public void FundTransfer();
	public void withdraw();
	public void changePin();
	public void createLog(User user, String msg);

}
