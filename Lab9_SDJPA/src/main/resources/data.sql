create table if not exists masini (numar_inmatriculare VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50),
    anul_fabricatiei INT,
    culoarea VARCHAR(30),
    nr_km INT);

INSERT INTO masini (numar_inmatriculare, marca, anul_fabricatiei, culoarea, nr_km) VALUES
                                                                                    ('TM123XYZ', 'Dacia', 2020, 'Alb', 30000),
                                                                                    ('B456XYZ', 'BMW', 2019, 'Negru', 45000),
                                                                                    ('AR789XYZ', 'Audi', 2021, 'Rosu', 20000),
                                                                                    ('CS21XYZ', 'Toyota', 2018, 'Albastru', 60000),
                                                                                    ('BH654XYZ', 'Ford', 2022, 'Verde', 15000);
