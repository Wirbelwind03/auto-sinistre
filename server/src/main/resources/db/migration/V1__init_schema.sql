
CREATE TABLE roles (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   email VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   role_id BIGINT NOT NULL,
   name VARCHAR(255) NOT NULL,
   first_name VARCHAR(255) NOT NULL,

   CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE vehicles (
    id BIGSERIAL PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    mileage BIGINT NOT NULL,
    license_plate VARCHAR(9) NOT NULL UNIQUE,
    fuel_type VARCHAR(64) NOT NULL,
    vin VARCHAR(17) NOT NULL,

    CONSTRAINT fk_vehicle_brand FOREIGN KEY (brand_id) REFERENCES brands(id),
    CONSTRAINT fk_vehicle_user FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE sinistres (
    id BIGSERIAL PRIMARY KEY ,
    vehicle_id BIGINT NOT NULL REFERENCES vehicles(id),
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    declaration_date TIMESTAMP NOT NULL,
    location VARCHAR(255) NOT NULL,
    description VARCHAR(1024) NOT NULL
);

