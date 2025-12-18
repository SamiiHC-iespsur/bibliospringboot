package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import ada.sami.apirest.bibliospringboot.entities.Ejemplar;

public interface EjemplarService {

    public Iterable<Ejemplar> findAll();

    public Optional<Ejemplar> findById(int id);

    public Ejemplar save(Ejemplar ejemplar);

    public void deleteById(int id);
}
