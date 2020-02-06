DROP DATABASE IF EXISTS "market";

CREATE DATABASE "market" ENCODING "UTF8";

create user market5 with password 'market';


 \c market;

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


INSERT INTO public.orders ("user_id", "pair", "amount","price","type","state")
VALUES
(1, 'BTC-USDT',100,15000,'Ask','executed'),
(2, 'BTC-USDT',50,14000,'Ask','executed'),
(3, 'BTC-USDT',50,13000,'Ask','active'),
(4, 'BTC-USDT',50,12000,'Ask','active'),
(5, 'BTC-USDT',50,11000,'Ask','active'),
(5, 'BTC-USDT',50,16000,'Ask','active'),
(1, 'BTC-USDT',50,10500,'Ask','active'),
(1, 'BTC-USDT',100,15000,'Bid','executed'),
(2, 'BTC-USDT',50,14000,'Bid','executed'),
(3, 'BTC-USDT',50,10000,'Bid','active'),
(4, 'BTC-USDT',50,9585,'Bid','active'),
(5, 'BTC-USDT',50,8000,'Bid','active'),
(1, 'BTC-USDT',50,8538,'Bid','active'),

(1, 'ETH-USDT',100,15000,'Ask','executed'),
(2, 'ETH-USDT',50,14000,'Ask','executed'),
(3, 'ETH-USDT',50,350,'Ask','active'),
(4, 'ETH-USDT',50,320,'Ask','active'),
(5, 'ETH-USDT',50,310,'Ask','active'),
(1, 'ETH-USDT',50,300,'Ask','active'),
(1, 'ETH-USDT',100,290,'Bid','executed'),
(2, 'ETH-USDT',50,280,'Bid','executed'),
(3, 'ETH-USDT',50,285,'Bid','active'),
(4, 'ETH-USDT',50,270,'Bid','active'),
(5, 'ETH-USDT',50,250,'Bid','active'),
(1, 'ETH-USDT',50,270,'Bid','active'),

(1, 'BTC-ETH',100,15000,'Ask','executed'),
(2, 'BTC-ETH',50,14000,'Ask','executed'),
(3, 'BTC-ETH',50,50,'Ask','active'),
(4, 'BTC-ETH',50,49,'Ask','active'),
(5, 'BTC-ETH',50,47,'Ask','active'),
(1, 'BTC-ETH',50,43,'Ask','active'),
(1, 'BTC-ETH',100,290,'Bid','executed'),
(2, 'BTC-ETH',50,280,'Bid','executed'),
(3, 'BTC-ETH',50,40,'Bid','active'),
(4, 'BTC-ETH',50,41,'Bid','active'),
(5, 'BTC-ETH',50,35,'Bid','active'),
(1, 'BTC-ETH',50,17,'Bid','active'),

(1, 'BTC-USDT',1,1,'Ask','executed');


GRANT ALL ON TABLE coins TO market7;
GRANT ALL ON TABLE users TO market7;
GRANT ALL ON TABLE cryptocurrency_pairs TO market7;
GRANT ALL ON TABLE wallets TO market7;
GRANT ALL ON TABLE orders TO market7;
GRANT ALL ON TABLE transactions TO market7;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO market7;


CREATE SEQUENCE IF NOT EXISTS order_id_sequence INCREMENT BY 10;
SELECT setval('order_id_sequence', max(identity)+1) FROM orders;


CREATE SEQUENCE IF NOT EXISTS user_id_sequence INCREMENT BY 10;
SELECT setval('user_id_sequence', max(identity)+1) FROM users;


CREATE SEQUENCE IF NOT EXISTS transaction_id_sequence INCREMENT BY 10;
SELECT setval('transaction_id_sequence', max(identity)+1) FROM transactions;
