--CREATE TABLE IF NOT EXISTS greeting (
--    id         BIGSERIAL PRIMARY KEY,
--    name       VARCHAR(255) NOT NULL,
--    message    VARCHAR(500) NOT NULL,
--    created_at TIMESTAMP    NOT NULL DEFAULT now()
--);

--INSERT INTO greeting (name, message) VALUES
--    ('Alice',   'Hello from the database!'),
--    ('Bob',     'JPA + PostgreSQL is working!'),
--    ('Charlie', 'Seed data loaded successfully.');

------------------------------------------------------------------------------------

-- IRIS DB --

-- clinic
CREATE TABLE IF NOT EXISTS clinic (
    id bigserial PRIMARY KEY,
    name varchar(100) NOT NULL,
    phone varchar(20),
     -- address
    street varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    zipcode varchar(20)
);

-- user
CREATE TABLE IF NOT EXISTS users (
    id bigserial PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(100),
    active boolean,
    userrole int
);

-- patient
CREATE TABLE IF NOT EXISTS patient (
    id bigserial PRIMARY KEY,
    pid int,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    phone varchar(20),
    gender varchar(1),
    birth_date date
);

-- doctor
CREATE TABLE IF NOT EXISTS doctor (
    id bigserial PRIMARY KEY,
    first_name varchar(255),
    last_name varchar(255),
    active boolean,
    specialty int
);

CREATE TABLE IF NOT EXISTS clinic_doctor (
    id bigserial PRIMARY KEY,
    clinic_id bigint REFERENCES clinic not null,
    doctor_id bigint REFERENCES doctor not null
);

-- appointment
CREATE TABLE IF NOT EXISTS appointment (
    id bigserial PRIMARY KEY,
    patient_id bigint REFERENCES patient,
    doctor_id bigint REFERENCES doctor,
    appointment_date timestamp, -- == TIMESTAMP WITHOUT TIME ZONE
    duration_minutes int,
    status int,
    reason int,
    --created_at TIMESTAMP GENERATED ALWAYS AS (CURRENT_TIMESTAMP) STORED -- generated column (PostgreSQL 12+)
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz
);

-- trigger function: updated_at column
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS trigger AS $$
BEGIN
    NEW.updated_at := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- trigger for updated_at: appointment
CREATE TRIGGER trg_set_updated_at
BEFORE UPDATE ON appointment
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

-- using BIGSERIAL (alias), same as:
-- CREATE SEQUENCE user_id_seq;
-- CREATE TABLE user (
--     id bigint NOT NULL DEFAULT nextval('user_id_seq')
-- );
-- ALTER SEQUENCE user_id_seq OWNED BY user.id;

-- indexes
CREATE INDEX patient_last_name_idx ON patient (last_name);
CREATE INDEX patient_birth_date_idx ON patient (birth_date);
CREATE INDEX appointment_date_idx ON appointment (appointment_date);
