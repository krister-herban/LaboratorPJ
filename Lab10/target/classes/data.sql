CREATE TABLE IF NOT EXISTS carti(
    isbn VARCHAR(10) PRIMARY KEY,
    titlu VARCHAR(50),
    autor VARCHAR(50));

INSERT INTO carti (isbn, titlu, autor) VALUES
    ('ISBN1', 'Scurta isotrie a omenirii', 'Yuval Noah Harari'),
    ('ISBN2', 'Homo deus-Scurta istorie a viitorului', 'Yuval Noah Harari'),
    ('ISBN3', 'De veghe in lanul de secara', 'J.D.SALINGER');