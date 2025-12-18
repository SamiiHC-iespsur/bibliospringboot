package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import ada.sami.apirest.bibliospringboot.entities.Edicion;

public interface EdicionService {

    public Iterable<Edicion> findAll();

    public Optional<Edicion> findById(String isbn);

    public Edicion save(Edicion edicion);

    public void deleteById(String isbn);
}
