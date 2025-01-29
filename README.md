# Bank System

## Overview
This is a simple banking system written in Java that simulates various banking operations. It allows users to create savings and business accounts, deposit, withdraw, transfer funds between accounts, and more. The system tracks account balances and allows for additional operations like applying interest to savings accounts and reviewing transaction histories for business accounts.

## Features

- **Create Savings Accounts**: Users can create savings accounts with an initial balance.
- **Create Business Accounts**: Users can create business accounts with an initial balance.
- **Deposit**: Deposit money into any account.
- **Withdraw**: Withdraw money from any account, with checks for sufficient funds.
- **Transfer**: Transfer money between accounts, with checks for sufficient funds.
- **Interest Application**: Apply interest to savings accounts.
- **Transaction History**: View transfer history for business accounts.
- **Limitations**: Transfers in savings accounts are limited to 500 EUR.

## Project Structure

1. **Enums**
    - `Currency`: Represents different currency types (e.g., EUR).

2. **Exception Handling**
    - `InsufficientFundsException`: Thrown when an account has insufficient funds for an operation.
    - `TransferLimitExceededException`: Thrown when a transfer amount exceeds the set transfer limit for savings accounts.

3. **Interfaces**
    - `AccountOperations`: An interface that defines the operations allowed for an account, such as deposit, withdraw, and transfer.

4. **Model Classes**
    - `Account`: Abstract class representing a general bank account with methods for deposit, withdrawal, and transfer.
    - `BusinessAccount`: Extends `Account`, adds transaction history functionality.
    - `SavingsAccount`: Extends `Account`, adds interest functionality and a transfer limit.

5. **Main Class**
    - `Main`: The entry point for the application that displays a menu and allows the user to interact with the system.

## Operations

### 1. Create a Savings Account
- Input the owner's name and initial balance.
- Account is created and stored in the system.

### 2. Create a Business Account
- Input the owner's name and initial balance.
- Account is created and stored in the system.

### 3. Display Account Details
- Displays the details of all accounts (savings and business).

### 4. Make a Deposit
- Select an account and specify the amount to deposit.
- Amount is added to the account balance.

### 5. Make a Withdrawal
- Select an account and specify the amount to withdraw.
- Amount is subtracted from the account balance.

### 6. Make a Transfer
- Select the source and destination accounts.
- Specify the amount to transfer.
- Money is transferred, and balances updated.

### 7. Apply Interest to Savings Account
- Select a savings account and apply the current interest rate to the balance.

### 8. Query Business Account Transfer History
- Displays all transfers made from a business account.

### 9. Exit the System
- Exits the application.

## Requirements

- Java 8 or later.
- Basic knowledge of Java object-oriented principles.

## How to Run

1. Clone the repository to your local machine.
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3. Compile and run the `Main` class.
4. Interact with the system through the command-line interface.

## Sample Output

```
###############
+ Bank System +
###############

Options:
1. Create a new savings account
2. Create a new business account
3. Display account details
4. Make a deposit
5. Make a withdrawal
6. Make a transfer
7. Apply interest to savings account
8. Query business account transfer history
9. Exit
Enter your option: 1
Enter the owner name: John Doe
Enter the balance: 1000.00

Account ID: 1234, Owner: John Doe, Balance: 1000.0€

...
```

## Notes

- This system operates using Euros (`EUR`).
- The transfer limit for savings accounts is set to 500 EUR.
- The business accounts log transfer history.