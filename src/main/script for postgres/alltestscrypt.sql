DROP DATABASE IF EXISTS "testmarket";


CREATE DATABASE "testmarket" ENCODING "UTF8";


create user testmarket with password 'testmarket';

 \c testmarket;

CREATE TABLE IF NOT EXISTS "coins" (
    "identity" INTEGER PRIMARY KEY,
    "coin" VARCHAR(5),
    "full_name" VARCHAR(20) NOT NULL);


CREATE TYPE roles AS ENUM (
  'user',
  'sec', 
  'admin');

CREATE TABLE IF NOT EXISTS "users" (
    "identity"  SERIAL PRIMARY KEY,
    "user_name" VARCHAR(30) NOT NULL unique,
    "name" VARCHAR(30) NOT NULL,
    "surname" VARCHAR(30) NOT NULL,
    "hash_of_password" VARCHAR(64) NOT NULL,
    "role" roles NOT NULL
);


CREATE TABLE IF NOT EXISTS "cryptocurrency_pairs" (
    "identity" INTEGER PRIMARY KEY,
    "first_currency" INTEGER NOT NULL,
    "second_currency" INTEGER NOT NULL,
    "active" BOOL NOT NULL,
    FOREIGN KEY ("first_currency")
    REFERENCES "coins" ("identity")
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

    FOREIGN KEY ("second_currency")
    REFERENCES "coins" ("identity")
    ON UPDATE CASCADE
    ON DELETE RESTRICT

);








CREATE TABLE IF NOT EXISTS "wallets" (
    "user_id" INTEGER NOT NULL,
    "btc" DECIMAL(12,6) NOT NULL,
    "eth"  DECIMAL(12,6) NOT NULL,
    "usdt"  DECIMAL(12,6) NOT NULL,


      FOREIGN KEY ("user_id")
    REFERENCES "users" ("identity")
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


CREATE TYPE typesOfOrder AS ENUM (
  'Ask',
  'Bid' );

CREATE TYPE statesOfOrder AS ENUM (
  'active',
  'executed',
  'canceled');

CREATE TYPE currencyPairs AS ENUM (
  'BTC-USDT',
  'ETH-USDT',
  'BTC-ETH');


CREATE TABLE IF NOT EXISTS "orders" (
  "identity" SERIAL PRIMARY KEY,
  "user_id" INTEGER NOT NULL,
  "pair"  currencyPairs  NOT NULL,
  "amount"  decimal(12, 6)  NOT NULL,
  "price"  decimal(12, 6) NOT NULL,
  "type" typesOfOrder  NOT NULL,
  "state"  statesOfOrder  NOT NULL,

FOREIGN KEY ("user_id")
REFERENCES "users" ("identity")
ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TYPE transactionType AS ENUM (
  'deposit',
  'withdraw');


CREATE TYPE transactionStatus AS ENUM (
  'pending',
  'approved',
   'rejected');

CREATE TABLE IF NOT EXISTS "transactions" (
  "identity" SERIAL PRIMARY KEY,
  "user_id" INTEGER NOT NULL,
  "coin_id" INTEGER NOT NULL,
  "amount"  decimal(12, 6) NOT NULL,
  "type"  transactionType NOT NULL,
  "date" TIMESTAMP,
  "status"  transactionStatus NOT NULL,

FOREIGN KEY ("user_id")
REFERENCES "users" ("identity")
ON UPDATE CASCADE ON DELETE RESTRICT,

    FOREIGN KEY ("coin_id")
REFERENCES "coins" ("identity")
ON UPDATE CASCADE ON DELETE RESTRICT

);




INSERT INTO public.coins(
	identity, coin, full_name)
	VALUES (1, 'BTC', 'Bitcoin'),(2,'ETH', 'Ethereum'),(3,'USDT', 'Tether');





INSERT INTO public.users (user_name, name, surname, hash_of_password, role)
VALUES
('Alice', 'Alice','Williams','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','admin'),
('Bob', 'Bob','Johnson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','sec'),
('Eve', 'Eve','Jones','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),
('Peggy', 'Peggy','Brown','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),
('Craig', 'Craig','Miller','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),
('Mallory', 'Mallory','Anderson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),
('Walter', 'Walter','Jackson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user');




INSERT INTO public.wallets(user_id, "btc", "eth", "usdt")
VALUES
(1,10,10,10),
(2,  20,15,18),
(3,  10,10,10),
(4,  5,10,10),
(5,  10,7,10),
(6,  10,10,10),
(7,  10,10,10);



INSERT INTO public.cryptocurrency_pairs ("identity", "first_currency", "second_currency", "active")
VALUES
(1, 1,3,TRUE),
(2, 2,3,TRUE),
(3, 1,2,TRUE);


GRANT ALL ON TABLE coins TO testmarket;
GRANT ALL ON TABLE users TO testmarket;
GRANT ALL ON TABLE cryptocurrency_pairs TO testmarket;
GRANT ALL ON TABLE wallets TO testmarket;
GRANT ALL ON TABLE orders TO testmarket;
GRANT ALL ON TABLE transactions TO testmarket;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO testmarket;