create table if not exists evenimente(
    id INT AUTO_INCREMENT PRIMARY KEY,
    denumire VARCHAR(50) NOT NULL,
    locatie VARCHAR(50) NOT NULL,
    data DATE NOT NULL,
    timp TIME NOT NULL,
    pret_bile FLOAT NOT NULL CHECK (pret_bile > 0)
    );

INSERT INTO evenimente (denumire, locatie, data, timp, pret_bile) VALUES
    ('Concert Rock', 'Sala Polivalenta', '2025-01-20', '20:00:00', 150.0),
    ('Piesa de Teatru', 'Teatrul National', '2025-01-21', '18:30:00', 100.0),
    ('Expozitie de Pictura', 'Galeria de Arta', '2025-01-22', '10:00:00', 50.0),
    ('Expozitie de Sculptura', 'Muzeul de Arta', '2025-01-23', '12:00:00', 75.0);