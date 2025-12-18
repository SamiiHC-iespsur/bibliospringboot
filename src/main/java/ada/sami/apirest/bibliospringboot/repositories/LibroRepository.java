package ada.sami.apirest.bibliospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.sami.apirest.bibliospringboot.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
