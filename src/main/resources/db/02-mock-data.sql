-- Mock data for IRIS DB
-- Run after 01-init.sql

------------------------------------------------------------------------------------
-- clinics
------------------------------------------------------------------------------------
INSERT INTO clinic (id, name, phone, street, city, zipcode) VALUES
    (1, 'IRIS Eye Center Downtown',   '555-100-2000', '120 Main Street',      'Springfield', '62701'),
    (2, 'IRIS Vision Clinic Westside', '555-100-3000', '45 Oak Avenue',        'Springfield', '62704'),
    (3, 'ClearSight Ophthalmology',    '555-200-4000', '789 Elm Boulevard',    'Shelbyville', '62565');

SELECT setval('clinic_id_seq', (SELECT max(id) FROM clinic));

------------------------------------------------------------------------------------
-- users  (roles: 1=ADMIN, 2=SECRETARY, 3=DOCTOR)
------------------------------------------------------------------------------------
INSERT INTO users (id, username, password, active, userrole) VALUES
    (1, 'admin',       'admin',    true,  1),
    (2, 'jsmith',      'pass123',  true,  2),
    (3, 'mwilliams',   'pass123',  true,  2),
    (4, 'drjones',     'pass123',  true,  3),
    (5, 'drmartinez',  'pass123',  true,  3),
    (6, 'drpatel',     'pass123',  true,  3),
    (7, 'drkim',       'pass123',  false, 3);

SELECT setval('users_id_seq', (SELECT max(id) FROM users));

------------------------------------------------------------------------------------
-- doctors  (specialty: 1=OPHTALMOLOGIST)
------------------------------------------------------------------------------------
INSERT INTO doctor (id, first_name, last_name, active, specialty) VALUES
    (1, 'Sarah',    'Jones',     true,  1),
    (2, 'Carlos',   'Martinez',  true,  1),
    (3, 'Priya',    'Patel',     true,  1),
    (4, 'James',    'Kim',       false, 1);

SELECT setval('doctor_id_seq', (SELECT max(id) FROM doctor));

------------------------------------------------------------------------------------
-- clinic_doctor (which doctors work at which clinics)
------------------------------------------------------------------------------------
INSERT INTO clinic_doctor (id, clinic_id, doctor_id) VALUES
    (1, 1, 1),   -- Dr. Jones    @ Downtown
    (2, 1, 2),   -- Dr. Martinez @ Downtown
    (3, 2, 2),   -- Dr. Martinez @ Westside (works at two clinics)
    (4, 2, 3),   -- Dr. Patel    @ Westside
    (5, 3, 3),   -- Dr. Patel    @ ClearSight
    (6, 3, 4);   -- Dr. Kim      @ ClearSight (inactive)

SELECT setval('clinic_doctor_id_seq', (SELECT max(id) FROM clinic_doctor));

------------------------------------------------------------------------------------
-- patients  (gender: 'M', 'F', 'U')
------------------------------------------------------------------------------------
INSERT INTO patient (id, pid, first_name, last_name, email, phone, gender, birth_date) VALUES
    (1,  1001, 'John',      'Doe',       'john.doe@email.com',       '555-010-0001', 'M', '1985-03-15'),
    (2,  1002, 'Jane',      'Smith',     'jane.smith@email.com',     '555-010-0002', 'F', '1990-07-22'),
    (3,  1003, 'Robert',    'Johnson',   'r.johnson@email.com',      '555-010-0003', 'M', '1978-11-30'),
    (4,  1004, 'Emily',     'Davis',     'emily.davis@email.com',    '555-010-0004', 'F', '2001-01-10'),
    (5,  1005, 'Michael',   'Brown',     'michael.b@email.com',      '555-010-0005', 'M', '1965-06-05'),
    (6,  1006, 'Olivia',    'Wilson',    'olivia.w@email.com',       '555-010-0006', 'F', '1995-09-18'),
    (7,  1007, 'Daniel',    'Taylor',    'dan.taylor@email.com',     '555-010-0007', 'M', '1988-12-25'),
    (8,  1008, 'Sophia',    'Anderson',  'sophia.a@email.com',       '555-010-0008', 'F', '2003-04-02'),
    (9,  1009, 'Alex',      'Garcia',    'alex.garcia@email.com',    '555-010-0009', 'U', '1992-08-14'),
    (10, 1010, 'Margaret',  'Lee',       'margaret.lee@email.com',   '555-010-0010', 'F', '1955-02-28');

SELECT setval('patient_id_seq', (SELECT max(id) FROM patient));

------------------------------------------------------------------------------------
-- appointments  (status: 1=NotStarted, 2=OnGoing, 3=Canceled  |  reason: 1=Consultation)
------------------------------------------------------------------------------------
INSERT INTO appointment (id, patient_id, doctor_id, appointment_date, duration_minutes, status, reason) VALUES
    -- upcoming appointments (NotStarted)
    (1,  1, 1, '2025-07-10 09:00', 30, 1, 1),
    (2,  2, 1, '2025-07-10 09:30', 30, 1, 1),
    (3,  3, 2, '2025-07-10 10:00', 45, 1, 1),
    (4,  4, 2, '2025-07-10 11:00', 30, 1, 1),
    (5,  5, 3, '2025-07-11 08:30', 30, 1, 1),
    (6,  6, 3, '2025-07-11 09:00', 45, 1, 1),
    -- ongoing
    (7,  7, 1, '2025-07-09 14:00', 30, 2, 1),
    (8,  8, 2, '2025-07-09 14:30', 30, 2, 1),
    -- canceled
    (9,  9, 3, '2025-07-08 10:00', 30, 3, 1),
    (10, 10, 1, '2025-07-08 11:00', 45, 3, 1),
    -- past completed (marked as ongoing since there's no "completed" status)
    (11, 1, 2, '2025-06-15 09:00', 30, 2, 1),
    (12, 3, 3, '2025-06-20 10:30', 45, 2, 1);

SELECT setval('appointment_id_seq', (SELECT max(id) FROM appointment));
