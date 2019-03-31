# src.ATM

This program allows users can create to create or access their bank accounts(debt or asset).
From there, users are able to view their account balance(s), transfer money between their accounts, transfer money to another user's account, withdraw money, pay bills, deposit money by entering a cheque or cash into the machine.

## Motivation

Our motivation for the project was the 27% of our CSC207 mark.

## Running the program

Open and run ATMApplication. The src.ATM will prompt the user to enter an username and a password (example of working usernames and 
passwords can be found in BankUsers.txt). 

Bank manager is able to create an client, restock the atm machine, undo transaction, view account creation requests, 
set time, and show and clear alerts.

Clients are able to view a summary of all account balances and their net total. They can also view the most recent 
transaction and the date of creation of any account they own. A client is able to transfer money between their accounts 
or out to other user's, withdraw or deposit money with the src.ATM machine, pay a bill, and request a creation of an account 
from the bank manager.

Both bank manager and clients are able to access the features above by entering the number corresponding to the actions
and following the prompt.

## Configuration

There are several configuration files for this program called "AtmInfo.txt", "BankAccounts.txt" and "BankUsers.txt". They should be present in the same directory as the folder src.ATM.

#### Configuring AtmInfo.txt

This file configures the src.ATM's information.

The first line will be the date the src.ATM was initialized written in the following format:

```
<year> <month> <date>
```

Each variable is separated by spaces. Months must be written in the following format:

```
"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"
```

Years and dates are initialized with no leading zeros.

The second line will be the number of each bill the src.ATM holds in the following format:

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