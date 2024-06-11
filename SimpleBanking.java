package JAVAPROJECTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Bank account class
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double accountBalance;

    // Constructor for BankAccount object
    public BankAccount(String accountNumber, String accountHolderName, double accountBalance) {
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    // Method to deposit
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Deposited successfully: " + amount + "\n New Account Balance is: " + accountBalance);
        } else {
            System.out.println("INVALID INPUT!!!");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= accountBalance && amount > 0) {
            accountBalance -= amount;
            System.out.println("Amount: " + amount + " Was successfully withdrawn" + "Your current balance is:" + accountBalance);
        }
    }

    // Method to check balance
    public void balancecheck() {
        System.out.println("You account balance is:" + accountBalance);
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public String toString() {
        return "Bank Account [Account Number=" + accountNumber + ", Name=" + accountHolderName + ", Balance=" + accountBalance + "]";
    }
}

// Bank class
class Bank {
    private List<BankAccount> accounts;

    // Constructor for accounts list
    public Bank() {
        accounts = new ArrayList<>();
    }

    // Method to add new account
    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account added successfully." + account);
    }

    // Method to remove account
    public void removeAccount(String accountNumber) {
        BankAccount accountToRemove = null;
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accountToRemove = account;
                break;
            }
        }
        if (accountToRemove != null) {
            accounts.remove(accountToRemove);
            System.out.println("Account removed: " + accountToRemove);
        } else {
            System.out.println("NO ACCOUNT FOUND WITH GIVEN CREDENTIALS!!!🤒");
        }
    }

    // Method to find account
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account; // Account found
            }
        }
        System.out.println("NO ACCOUNT FOUND WITH GIVEN CREDENTIALS!!!🤒");
        return null;
    }

    // Method to display all accounts
    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No account to display.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }
}

//BankSystem class for providing text based user interface wiith main method.
public class BankSystem {
    public static void main(String[] args) {
        try (Scanner myScanner = new Scanner(System.in)) {
            Bank bank = new Bank();

            while (true) {
                System.out.println("========= Banking System ========");
                System.out.println("CHOOSE OPTIONS");
                System.out.println("1. Add account");
                System.out.println("2. Find account");
                System.out.println("3. Display all accounts");
                System.out.println("4. Financial services");
                System.out.println("5. Exit");
                System.out.print("Enter option: ");
                int option = myScanner.nextInt();
                myScanner.nextLine(); // Consume newline

                // Switching to options
                switch (option) {
                    case 1:
                        System.out.println("Enter account number: ");
                        String accountNumber = myScanner.nextLine();
                        System.out.println("Enter account holder name: ");
                        String accountHolderName = myScanner.nextLine();
                        System.out.println("Enter initial balance: ");
                        double initialBalance = myScanner.nextDouble();
                        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialBalance);
                        bank.addAccount(newAccount);
                        break;

                    case 2:
                        System.out.print("Enter account number to find: ");
                        accountNumber = myScanner.nextLine();
                        BankAccount account = bank.findAccount(accountNumber);
                        if (account != null) {
                            System.out.println(account);
                        }
                        break;

                    case 3:
                        bank.displayAccounts();
                        break;

                    case 4:
                        System.out.println("Choose services below: ");
                        System.out.println("1. Check balance");
                        System.out.println("2. Deposit money");
                        System.out.println("3. Withdraw money");
                        System.out.println("4. Exit");
                        System.out.print("Enter option: ");
                        int service = myScanner.nextInt();
                        myScanner.nextLine();

                        switch (service) {
                            //checking balance
                            case 1:
                                System.out.print("Enter account number: ");
                                accountNumber = myScanner.nextLine();
                                BankAccount acc = bank.findAccount(accountNumber);
                                if (acc != null) {
                                    System.out.println("Account balance: " + acc.getAccountBalance());
                                }
                                break;
                            ///deposit
                            case 2:
                                System.out.print("Enter account number: ");
                                accountNumber = myScanner.nextLine();
                                BankAccount acc2 = bank.findAccount(accountNumber);
                                if (acc2 != null) {
                                    System.out.print("Enter amount to deposit: ");
                                    double amount = myScanner.nextDouble();
                                    acc2.deposit(amount);
                                }
                                break;
                            //withdraw
                            case 3:
                                System.out.print("Enter account number: ");
                                accountNumber = myScanner.nextLine();
                                BankAccount acc3 = bank.findAccount(accountNumber);
                                if (acc3 != null) {
                                    System.out.print("Enter amount to withdraw: ");
                                    double amount = myScanner.nextDouble();
                                    acc3.withdraw(amount);
                                }
                                break;
                            case 4:
                                System.out.println("Exiting....");
                                System.exit(0);
                            default:
                                System.out.println("Invalid option!");
                                break;
                        }
                        break;

                    case 5:
                        System.out.println("Thank you for choosing our services!!!");
                        System.out.println("========= Proudly made by Isaka Mtweve ========");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }
        }
    }
}
