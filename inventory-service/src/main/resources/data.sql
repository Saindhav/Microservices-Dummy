DROP TABLE IF EXISTS inventory_details;

CREATE TABLE inventory_details (
  inventory_name VARCHAR(30) PRIMARY KEY,
  inventory_count INT DEFAULT 0,
  loyalty_points INT DEFAULT 0
);

INSERT INTO inventory_details (inventory_name, inventory_count, loyalty_points) VALUES
  ('Razor Pen', 100, 10),
  ('Natraj Pencil', 110, 1),
  ('KeyChain', 120, 12),
  ('Wallet', 5, 20),
  ('Belt', 50, 15),
  ('Diary', 8, 10),
  ('Pen Holder', 100, 20),
  ('Wrist Watch', 100, 25),
  ('Tie', 100, 20),
  ('Cap', 100, 100);