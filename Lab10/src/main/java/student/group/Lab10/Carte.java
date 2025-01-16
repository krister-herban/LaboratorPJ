package student.group.Lab10;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@Getter
@Entity
@Table(name="carti")
public class Carte {

    @Id
    @Column(name="isbn", unique = true)
    private String isbn;
    @Column(name="titlu")
    private String titlu;
    @Column(name="autor")
    private String autor;

    public Carte(String isbn, String titlu, String autor) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
    }
    public Carte() {}

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitlu() {
        return titlu;
    }
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Isbn: " + isbn + ", titlu: " + titlu + ", autor: " + autor;
    }
}
