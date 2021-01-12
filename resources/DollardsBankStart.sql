
use dollarbank;

DROP TABLE If EXISTS user;

create table user(
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
   (1, "tofanm", "1234", "Jeff", "Tom", "jeff@gmail.com", NOW(), NOW());
   
INSERT INTO user 
	(user_id, username, password, first_name, last_name, email, create_date, modify_date)
VALUES
   (null, "toal", "1234", "Jeff", "Tom", "jeff@gmail.com", NOW(), NOW());