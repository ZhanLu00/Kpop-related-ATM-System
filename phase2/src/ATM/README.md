# ATM

This program allows users can create to create or access their bank accounts (debt or asset).
From there, users are able to view their account balance(s), transfer money between their accounts, transfer money to another user's account, withdraw money, pay bills, deposit money by entering a cheque or cash into the machine.

## Motivation

Our motivation for the project was the 27% of our CSC207 mark.

## Running the program

Open and run ATMApplication. A welcome page will pop up with three buttons, 'New User', 'Returning User' or 'Exit'. You can go to new user to create a new client account (by choosing a username, password and initial account). Otherwise you can choose returning user.

If you're a returning user, the ATM will prompt the user to enter an username and a password (example of working usernames and passwords can be found in 'userfiles.txt').

The bank manager is able to create an client, restock the ATM, undo transactions, view account/user creation requests, join two accounts, and show or clear alerts/messages.

Clients are able to view a summary of all account balances and their net total. They can also view the most recent transaction and the of date of creation of any account they own. A client is able to transfer money between their accounts or out to other user's, withdraw or deposit money, pay bills, and request a creation of an account from the bank manager.

Bank inspectors are able to do everything that clients can with a few added benefits. They can also send messages to the bank manager and view outgoing/incoming transactions of each client.

All users are able to access the above features by entering the correct username and password. As default, we have the bank manager with username and password "admin" while the inspector has username "ins" with password "00000". This information can also be found in 'userfiles.txt' as aforementioned.

In order to exit the program, you must press the 'Exit' button, found on the welcome page when you first run the program.

If you're interested, there is a little easter egg above the 'New User' button on the welcome page.

## Configuration

There are several configuration files for this program called "AtmInfo.txt", "accounts.txt", "userfiles.txt", "accountRequests.txt" and "clientRequests.txt". They should be present in the resources folder.

#### Configuring AtmInfo.txt

This file configures the ATM's information.

The first line will be the dateCreated the src.ATM was initialized written in the following format:

```
<year> <month> <dateCreated>
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

#### Configuring accounts.txt

This file contains information of all the bank accounts.

Each line is a new bank account. All accounts are initialized in the following format:

```
<account type>, <balance>, <date of creation>
```

Foreign currency account balances are stored as their foreign currency balance.

Account type can be one of the following: 'CHEQUING_ACCOUNT', 'SAVINGS_ACCOUNT', 'CREDIT_CARDS_ACCOUNT', 'LOTTERY_ACCOUNT', 'LINE_OF_CREDIT_ACCOUNT', or 'FOREIGN_CURRENCY_ACCOUNT'

Balances must be doubles greater than or equal to 0.

The date of creation is written in the same format as the first line of 'ATMInfo.txt'.

#### Configuring userfiles.txt

This file contains the users of this bank.

Each line is a new user. All users are initialized in the following format:

```
<user type>, <username>, <password>, <list of account ids>
```

User type can either be 'manager', 'inspector' or 'client'.

Usernames and passwords are allowed to include any special characters, numbers or characters except the comma.

The list of account ids should be a list of integers, separated by commas, referring to each of the account ids that they own.

#### Configuring accountRequests.txt

This file contains the account requests by users.

Each line is a new account request. They should be in the following format:

```
<username>, <account type>, <status of request>
```

Username must be the username of an existing user.

Account type must be one of the account types listed above in accounts.txt

The request status can either be 'accepted', 'pending' or 'declined'.

#### Configuring clientRequests.txt

This file contains new user requests by clients.

Each line is a new user request. They should be in the following format:

```
<username>, <account type>, <status of request>
```

They follow the same rules as 'accountRequests.txt'.