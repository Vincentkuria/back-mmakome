CREATE TABLE IF NOT EXISTS blogs(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    image TEXT,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'DRAFT',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS messages(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS testimonials(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    testimony TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS case_studies(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    image TEXT,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS user_permissions (
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    permission_id INTEGER NOT NULL REFERENCES permissions(id) ON DELETE CASCADE,

    PRIMARY KEY (user_id, permission_id)
);

-- inserting permissions to the permissions table.
INSERT INTO permissions (name,description) VALUES('ADMIN','permission to Accessing to all the tables');
INSERT INTO permissions (name,description) VALUES('BLOG','permission to Accessing to blogs table');
INSERT INTO permissions (name,description) VALUES('CASE-STUDY','permission to Accessing to case_studies table');
INSERT INTO permissions (name,description) VALUES('USER','permission to Accessing to users table');
INSERT INTO permissions (name,description) VALUES('MESSAGE','permission to Accessing to messages table');
INSERT INTO permissions (name,description) VALUES('TESTIMONIAL','permission to Accessing to testimonials table');

-- inserting default user, to the users table with default password= password
INSERT INTO users (name, email, password) VALUES('Maina Makome','info@mmakome.com','$2a$12$Xn1yzNyoSW3Uj.n/KeDw.etk6xcyJ2BNSB.fLFfqNpMdUlnn7pqIy');

-- give default user admin permission.
INSERT INTO user_permissions (user_id, permission_id) VALUES(1,1);