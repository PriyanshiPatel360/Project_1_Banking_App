
import java.util.Scanner;

// Account Class
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully. Remaining balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("----- Account Details -----");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    // Getter
    public int getAccountNumber() {
        return accountNumber;
    }
}


// User Interface Class
public class BankingApp {
    private static Account[] accounts = new Account[100];  // array for accounts
    private static int accountCount = 0;
    private static Scanner sc = new Scanner(System.in);
    private static int nextAccountNumber = 1001;

    // Create new account
    public static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double balance = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        Account newAcc = new Account(nextAccountNumber++, name, balance, email, phone);
        accounts[accountCount++] = newAcc;

        System.out.println("Account created successfully with Account Number: " + newAcc.getAccountNumber());
    }

    // Find account by number
    private static Account findAccount(int accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit
    public static void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw
    public static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Show details
    public static void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // Update contact
    public static void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Main Menu
    public static void mainMenu() {
        while (true) {
            System.out.println("\n--- Welcome to the Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Thank you for using Banking App!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}

