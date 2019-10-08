INSERT INTO currency (id, name, iso_name)
VALUES
  (1, 'US Dollar','USD'),
  (2, 'Euro', 'EUR'),
  (3, 'Great Britain Pound', 'GBP');

INSERT INTO payout_status (id, name)
VALUES
       (1, 'Processing'),
       (2, 'Failed'),
       (3, 'Successful'),
	   (4, 'Canceled');

INSERT INTO account (title, balance, blocked_amount, currency_id)
VALUES
  ('Test Account 1', 1000.5, 0, 3),
  ('Test Account 3', 2000.5, 0, 2),
  ('Test Account 2', 3000.5, 0, 1);
  