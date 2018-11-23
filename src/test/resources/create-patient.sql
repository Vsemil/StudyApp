delete from study;
DELETE from patient;

INSERT INTO patient (id, name, sex, day_of_birth) VALUES
(1, 'alex', true, '20-11-1999'),
(2, 'ola', false, '15-01-2015'),
(3, 'dima', true, '01-01-2007'),
(4, 'xxx', null , null);

INSERT INTO study (id, description, estimated_end_time, planned_start_time, status, patient_id) VALUES
  (1, '111', null, '12-11-2018 11:00', 0, 3);

alter sequence hibernate_sequence restart with 10;