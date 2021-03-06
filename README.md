# DollarsBankCoreJavaApp
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/MainMenu.png" width=980>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/LoginToAccount.png" width=980>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/Actions.png" width=980>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/Transactions.png" width=980>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/CreateExit.png" width=980>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/DollarBankApp.png" width=500>
<img src="https://github.com/JCTGY/DollarsBankCoreJavaApp/raw/main/images/FileStructure.png" width=380>

* [Objective](#objective)
* [Prerequisites](#prerequisites)
* [How to use](#how-to-use)
* [Bonus](#bonus)

## Objective
Objectives
- Use layered architecture (abstracted files under SOLID principles) to make an MVC banking application.
- Create a user login system (no DB necessary, use in program memory).
- Once logged in, have a user menu display in console.
- Allow user to make:
  * a. Deposit
  * b. Withdrawl
  * c. Funds Transfer
  * d. 5 recent transaction history
  * e. Display customer information
  * f. Sign out
- Apply business logic to handle illegal operations in:
  * a. login system
  * b. Transactions (withdrawls /depsosits, etc.)
- See sample screenshots (next page).
- After 1 week attempt - sample code will be released
## Prerequisites
Java 11 \
[Jansi 2.1.0 ](https://github.com/fusesource/jansi) \
[MySQL connector Java 8.0.22](https://dev.mysql.com/downloads/connector/j/8.0.html)

## How to use

```
cd ~
git clone https://github.com/JCTGY/DollarsBankCoreJavaApp.git
import project to Eclipse
run the program
```
Connect MySQL to the connector using the [config.properties](resources/config.properties) \
Run [SQL starter](resources/DollardsBankStart.sql) to create the initial tables

## Bonus
- Use JDBC and DAO to connect user info to a MySQL database
- Manage mutiple accounts in one user
