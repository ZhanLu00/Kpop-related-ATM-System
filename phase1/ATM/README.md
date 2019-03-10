# ATM

This program allows users can create to create or access their bank accounts(debt or asset).
From there, users are able to view their account balance(s), transfer money between their accounts, transfer money to another user's account, withdraw money, pay bills, deposit money by entering a cheque or cash into the machine.

## Motivation

Our motivation for the project was the 27% of our CSC207 mark.

## Running the program

ATMApplication

## Configuration

There are several configuration files for this program called "AtmInfo.txt", "BankAccounts.txt" and "BankUsers.txt". They should be present in the same directory as the folder ATM.

#### Configuring AtmInfo.txt

This file configures the ATM's information.

The first line will be the date the ATM was initialized written in the following format:

```
<year> <month> <date>
```

Each variable is separated by spaces. Months must be written in the following format:

```
"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec
```

Years and dates are initialized with no leading zeros.

The second line will be the number of each bill the ATM holds in the following format:

```
<number of $5 bills>, <number of $10 bills>, <number of $20 bills>, <number of $50 bills>
```

They should all be positive integers.

Any lines after this will be account creation requests, written in the same format as BankUsers.txt

#### Configuring BankAccounts.txt

This file contains information of all the bank accounts.

Each line is a new bank account. All accounts are initialized in the following format:

```
<account type>, <balance>, <date of creation>
```

Account type can be one of the following: 'chequing', 'savings', 'lineofcredit', or 'creditcard'.

Balances must be doubles greater than or equal to 0.

The date of creation is written in the same format as the first line of 'ATMInfo.txt'.

#### Configuring BankUsers.txt

This file contains the users of this bank.

Each line is a new user. All users are initialized in the following format:

```
<user type>, <username>, <password>, <list of account ids>
```

User type can either be 'manager' or 'client'.

Usernames and passwords are allowed to include any special characters, numbers or characters except the comma.

The list of account ids should be a list of integers, separated by commas, referring to each of the account ids that they own.