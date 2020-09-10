CREATE TABLE customer (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  UNIQUE(name)
);
INSERT INTO customer (name, email) VALUES
    ('Marten Deinum', 'marten.deinum@conspect.nl'),
    ('Juseong Jung', 'jungjuseong@gmail.com'),
    ('John Lee', 'john.lee@printbe.com'),
    ('Jane Park', 'jane.park@clbee.com');