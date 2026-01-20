CREATE TABLE IF NOT EXISTS loan (
  codeProduct INTEGER,
  quantity INTEGER,
  unitPrice INTEGER
);

ALTER TABLE loan
ADD COLUMN IF NOT EXISTS status VARCHAR(50);

INSERT INTO loan (codeProduct, quantity, unitPrice, status)
VALUES (10, 50, 128, NULL);
