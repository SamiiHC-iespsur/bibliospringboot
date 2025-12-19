package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import ada.sami.apirest.bibliospringboot.entities.Libro;

public interface LibroService {

    public Iterable<Libro> findAll();

    public Optional<Libro> findById(int id);

    public Libro save(Libro libro);

    public void deleteById(int id);
}
