DROP DATABASE IF EXISTS ocxuser;
CREATE DATABASE slate DEFAULT CHARACTER SET utf8;

USE ocxuser;

CREATE TABLE tenant (
  user_id bigint NOT NULL PRIMARY KEY,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  position varchar(100) NOT NULL,
  phone_no varchar(100) NOT NULL,
  other_info varchar(100) NOT NULL,
  UNIQUE (phone_no)
);
