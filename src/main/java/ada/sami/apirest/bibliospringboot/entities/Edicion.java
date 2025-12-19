package ada.sami.apirest.bibliospringboot.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Edicion")
public class Edicion {

    @Id
    @Column(length = 20)
    private String isbn;

    @Column(name = "anno_publicacion")
    private Integer annoPublicacion;

    @Column(length = 100)
    private String editorial;

    @Column(length = 50)
    private String idioma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    @JsonBackReference("libro-ediciones")
    private Libro libro;

    @OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("edicion-ejemplares")
    private List<Ejemplar> ejemplares = new ArrayList<>();

    public Edicion() {
    }

    public Edicion(String isbn, Libro libro) {
        this.isbn = isbn;
        this.libro = libro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnnoPublicacion() {
        return annoPublicacion;
    }

    public void setAnnoPublicacion(Integer annoPublicacion) {
        this.annoPublicacion = annoPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
}
