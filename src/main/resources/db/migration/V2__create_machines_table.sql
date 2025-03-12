CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE machines (
   id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   status VARCHAR(20) NOT NULL
);