package com.jump.plus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;

import com.jump.plus.constant.AccountMenu;
import com.jump.plus.constant.CreateNewAccountMenu;
import com.jump.plus.constant.CreateNewUserMenu;
import com.jump.plus.constant.MainMenu;
import com.jump.plus.constant.UserMenu;
import com.jump.plus.model.Account;
import com.jump.plus.model.Transaction;
import com.jump.plus.model.User;
import com.jump.plus.view.ConsoleScanner;
import com.jump.plus.view.DollarBankView;

public class ConsoleController {

	private Scanner scanner = ConsoleScanner.getScanner();
	private DollarBankView dbView = new DollarBankView();
	private UserController userController = new UserController();
	private AccountController accountController = new AccountController();
	private TransactionController transactionController = new TransactionController();

	public void startConsole() {
		dbView.printHeader();
		startMenuSelections();
	}

	private void startMenuSelections() {

		while (true) {

			dbView.displayTitle("Welcome To DollarBank");
			dbView.displaySelections(MainMenu.selections);
			String[] inputValue = null;
			try {
				int message = Integer.parseInt(scanner.next());
				if (message >= 0 && message <= MainMenu.selections.length) {
					if (message == MainMenu.EXIT) {
						System.out.println(
								ansi().eraseScreen().fg(Color.MAGENTA).a("Goodbye, See you next time").reset());
						System.exit(0);
					} else if (message == MainMenu.LOGIN) {
						dbView.displayTitle("Login");
						inputValue = inputLogin();
						if (inputValue != null && userController.login(inputValue[0], inputValue[1])) {
							inputValue = null;
							userMenuInterface();
						}
					} else if (message == MainMenu.LOGOUT) {
						userController.logout();
						System.out.println(ansi().eraseScreen().fg(Color.MAGENTA).a("User logout").reset());
					} else if (message == MainMenu.CREATE_NEW_USER) {
						dbView.displayTitle("Create New User");
						userController.createNewUser(inputUserInfos());
						userMenuInterface();
					}
				} else
					dbView.displayIllegalWarning();
			} catch (NumberFormatException e) {
				dbView.displayIllegalWarning();
			}
		}
	}

	/*
	 * Helper Method to collect username/password for login
	 */
	private String[] inputLogin() {

		String[] loginValue = new String[2];
		String message = null;

		System.out.println(ansi().eraseScreen().fg(Color.WHITE).a(MainMenu.BACK_SLECTION).reset());
		System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter Username: ").reset());
		while (true) {
			if (loginValue[0] == null && message != null) {
				loginValue[0] = message;
				System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter Password: ").reset());
			} else if (loginValue[1] == null) {
				loginValue[1] = message;
			}
			if (loginValue[0] != null && loginValue[1] != null)
				return loginValue;
			message = scanner.next();
			if (message.equalsIgnoreCase(MainMenu.BACK))
				return null;

		}
	}
	
	/*
	 * After user login or create new account, they will be in the user menu where they can do all the 
	 * management of their accounts and user data
	 */

	private void userMenuInterface() {
		User user = userController.getCurrentUser();
		if (user == null)
			return;
		while (true) {
			if (!userController.isLogin())
				break;
			dbView.displayUserDetail(user);
			dbView.displaySelections(UserMenu.selections);
			try {
				int message = Integer.parseInt(scanner.next());
				if (message > 0 && message <= UserMenu.selections.length) {
					switch (message) {
					case UserMenu.EXIT:
						System.out.println(
								ansi().eraseScreen().fg(Color.MAGENTA).a("Goodbye, See you next time").reset());
						System.exit(0);
						break ;
					case UserMenu.LOGOUT:
						userController.logout();
						System.out.println(ansi().eraseScreen().fg(Color.MAGENTA).a("User logout").reset());
						break ;
					case UserMenu.CREATE_NEW_ACCOUNT:
						dbView.displaySelections(CreateNewAccountMenu.accountTypes);
						String indexMessage = scanner.next();
						if (indexMessage.equals("1") || indexMessage.equals("2")) {
							int index = Integer.parseInt(indexMessage) - 1;
							accountController
									.createNewAccount(CreateNewAccountMenu.accountTypes[index], user.getId());
						}
						break ;
					case UserMenu.ACCOUNTS_LIST:
						accountSelctionInterface();
						break ;
					default:
						dbView.displayIllegalWarning();
					}
				}
			} catch (NumberFormatException e) {
				dbView.displayIllegalWarning();
			}
		}
	}
	
	/*
	 * Menu for displaying and selecting different accounts
	 */
	private void accountSelctionInterface() {
		User user = userController.getCurrentUser();
		if (user == null)
			return;
		while (true) {
			List<Account> accounts = accountController.getAccountsByUserId(user.getId());
			dbView.displayAccounstList(accounts);
			System.out.println(ansi().eraseScreen().fg(Color.WHITE).a(MainMenu.BACK_SLECTION).reset());
			String message = scanner.next();
			if (message.equalsIgnoreCase(MainMenu.BACK))
				return ;
			try {
			int acountNumber = Integer.parseInt(message);
			accountMenuInterface(accounts.get(acountNumber - 1));
			
			} catch (NumberFormatException e) {
				dbView.displayIllegalWarning();
			}
		}
	}
	
	/*
	 * Account details, make deposit, withdraw, and transfer
	 */
	private void accountMenuInterface(Account account) {
		User user = userController.getCurrentUser();
		if (user == null)
			return;
		while (true) {
			dbView.displayTitle("Account: " + account.getId() + " | " + account.getType() +  " | Balance: " + account.getBalance());
			dbView.displaySelections(AccountMenu.selections);
			System.out.println(ansi().eraseScreen().fg(Color.WHITE).a(MainMenu.BACK_SLECTION).reset());
			String message = scanner.next();
			if (message.equalsIgnoreCase(MainMenu.BACK))
				return ;
			try {
				int selectionIndex = Integer.parseInt(message);
				if (selectionIndex > 0 && selectionIndex <= AccountMenu.selections.length) {
					switch (selectionIndex) {
					case AccountMenu.EXIT:
						System.out.println(
								ansi().eraseScreen().fg(Color.MAGENTA).a("Goodbye, See you next time").reset());
						System.exit(0);
						break ;
					case AccountMenu.LOGOUT:
						userController.logout();
						System.out.println(ansi().eraseScreen().fg(Color.MAGENTA).a("User logout").reset());
						return ;
					case AccountMenu.DEPOSIT:
						try {
							System.out.println(
									ansi().eraseScreen().fg(Color.GREEN).a("Enter Amount to Deposit: ").reset());						
							double depositAmount = Double.parseDouble(scanner.next());
							accountController.deposit(account, doubleToTwoDecimal(depositAmount));
							transactionController.createNewTransaction(account.getId(), user.getId(), depositAmount, "Deposit");
						} catch (NumberFormatException e) {
							System.out.println(
								ansi().eraseScreen().fg(Color.RED).a("Enter amount as double").reset());
						}
						break ;
					case AccountMenu.FUNDS_TRANSFER:
						try {
							System.out.println(
									ansi().eraseScreen().fg(Color.GREEN).a("Enter Amount to Transfer: ").reset());
							double transferAmount = Double.parseDouble(scanner.next());
							System.out.println(
									ansi().eraseScreen().fg(Color.GREEN).a("Enter AccountId to Transfer: ").reset());
							int transferAccountId = Integer.parseInt(scanner.next());
							accountController.transerFunds(account, doubleToTwoDecimal(transferAmount), transferAccountId);
							transactionController.createNewTransaction(account.getId(), user.getId(), 
									transferAmount, "Transfer To Account: " + String.valueOf(transferAccountId));
						} catch (NumberFormatException e) {
							System.out.println(
								ansi().eraseScreen().fg(Color.RED).a("Enter amount as double").reset());
						}
						break ;
					case AccountMenu.TRANSACTIONS:
						List<Transaction> transaction = transactionController.getTransactionByAccountId(account.getId());
						List<Map<String, String>> transactionInfo = getTransactionInfo(transaction);
						dbView.displayTitle("Transaction");
						transactionInfo.forEach(dbView::diaplayTransactionTable);
						break ;
					case AccountMenu.WITHDRAW:
						try {
							System.out.println(
									ansi().eraseScreen().fg(Color.GREEN).a("Enter Amount to Withdraw: ").reset());
							double withdrawAmount = Double.parseDouble(scanner.next());
							accountController.withdraw(account, doubleToTwoDecimal(withdrawAmount));
							transactionController.createNewTransaction(account.getId(), user.getId(), withdrawAmount, "Withdraw");
						} catch (NumberFormatException e) {
							System.out.println(
								ansi().eraseScreen().fg(Color.RED).a("Enter amount as double").reset());
						}
						break ;
					default:
						dbView.displayIllegalWarning();
					}
				}
			} catch (NumberFormatException e) {
				dbView.displayIllegalWarning();
			}
		}
	}
	
	private List<Map<String, String>> getTransactionInfo(List<Transaction> tranactions) {
		List<Map<String, String>> infos = new ArrayList<Map<String, String>>();
		tranactions.forEach(t -> {
			Map<String, String> info = new HashMap<>();
			info.put("Type", t.getType());
			info.put("Amount", String.valueOf(t.getAmount()));
			info.put("Date", String.valueOf(t.getCreateDate()));
			infos.add(info);
		});
		return infos;
	}

	/*
	 * Collecting user information to create new User
	 */
	private Map<String, String> inputUserInfos() {
		Map<String, String> userInfo = new HashMap<>();
		for (String s : CreateNewUserMenu.selections) {
			System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter " + s + ": ").reset());
			String message = scanner.next();
			userInfo.put(s, message);
		}
		return userInfo;
	}
	
	/*
	 * change double to two decimal points
	 */
	private double doubleToTwoDecimal(double d) {
		int truncatedNumberInt = (int)(d * Math.pow(10, 2));
		return truncatedNumberInt / Math.pow(10, 2);
	}
}
