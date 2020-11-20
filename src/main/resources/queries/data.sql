DROP TABLE IF EXISTS account;

CREATE TABLE account_error (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  branch_number VARCHAR(4) NOT NULL,
  account_number VARCHAR(7) NOT NULL,
  balance DOUBLE NOT NULL,
  status VARCHAR(1) NOT NULL,
  reason VARCHAR(250) NOT NULL
);

INSERT INTO account_error (branch_number, account_number, balance, status, reason)
VALUES ('2222', '12345-6', 220.00, 'A', 'Connection timeout');
