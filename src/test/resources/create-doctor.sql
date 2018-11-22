DELETE from doctors;

INSERT INTO doctors (id, name) VALUES
(1, 'alex'),
(2, 'mike');

alter sequence hibernate_sequence restart with 10;