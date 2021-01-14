
use dollarbank;

DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
DROP TABLE If EXISTS user;

CREATE TABLE user(
   user_id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(30) NOT NULL UNIQUE,
   password VARCHAR(30) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL UNIQUE,
   create_date DATETIME NOT NULL,
   modify_date DATETIME NOT NULL,
   PRIMARY KEY ( user_id )
);

INSERT INTO user 
	(user_id, username, password, first_name, last_name, email, create_date, modify_date)
VALUES
   (1, "toda", "1234", "Jeff", "Tom", "toda@gmail.com", NOW(), NOW());
   
INSERT INTO user 
	(user_id, username, password, first_name, last_name, email, create_date, modify_date)
VALUES
   (null, "toal", "1234", "Jeff", "Tom", "toal@gmail.com", NOW(), NOW());
   
CREATE TABLE account (
    account_id INT NOT NULL AUTO_INCREMENT,
    balance DOUBLE NOT NULL,
    type VARCHAR(30) NOT NULL,
    user_id Int,
	create_date DATETIME NOT NULL,
    modify_date DATETIME NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );
INSERT INTO account 
	(account_id, balance, type, user_id, create_date, modify_date)
VALUES
   (null, 55632.32, "Saving Account", 1, NOW(), NOW());

CREATE TABLE transaction (
    transaction_id INT NOT NULL AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    type VARCHAR(30) NOT NULL,
    user_id INT NOT NULL,
    account_id INT NOT NULL,
	create_date DATETIME NOT NULL,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );
INSERT INTO transaction 
	(transaction_id, amount, type, user_id, account_id, create_date)
VALUES
   (null, 55632.32, "Deposit", 1, 1, NOW());