DROP TABLE IF EXISTS customer_details;

CREATE TABLE customer_details (
  user_name VARCHAR(30) PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  loyalty_points INT DEFAULT 0
);

INSERT INTO customer_details (user_name, first_name, last_name, loyalty_points) VALUES
  ('sa.nihal', 'Saindhav', 'Nihal', 100),
  ('gates01', 'Bill', 'Gates', 1000),
  ('james.bond', 'James', 'Bond', 9000);