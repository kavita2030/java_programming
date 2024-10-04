import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    
    // HashMap to store user account data (accountNumber, PIN, balance)
    private static Map<String, Account> accounts = new HashMap<>();
    
    public static void main(String[] args) {
        // Creating a few sample accounts
        accounts.put("12345", new Account("12345", "1234", 1000));
        accounts.put("67890", new Account("67890", "5678", 5000));
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        // User authentication
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            
            if (account.getPin().equals(pin)) {
                // Authenticated
                boolean exit = false;
                
                while (!exit) {
                    // Displaying the menu
                    System.out.println("\nATM Menu:");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    
                    switch (choice) {
                        case 1:
                            // Check balance
                            System.out.println("Your balance is: $" + account.getBalance());
                            break;
                        case 2:
                            // Deposit money
                            System.out.print("Enter amount to deposit: ");
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            System.out.println("Successfully deposited $" + depositAmount);
                            break;
                        case 3:
                            // Withdraw money
                            System.out.print("Enter amount to withdraw: ");
                            double withdrawAmount = scanner.nextDouble();
                            if (account.withdraw(withdrawAmount)) {
                                System.out.println("Successfully withdrew $" + withdrawAmount);
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                            break;
                        case 4:
                            // Exit
                            exit = true;
                            System.out.println("Thank you for using the ATM!");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid PIN.");
            }
        } else {
            System.out.println("Account not found.");
        }
        
        scanner.close();
    }
}

// Account class to represent a bank account
class Account {
    @SuppressWarnings("unused")
    private String accountNumber;
    private String pin;
    private double balance;

    // Constructor
    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    // Getter for PIN
    public String getPin() {
        return pin;
    }

    // Getter for Balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}
