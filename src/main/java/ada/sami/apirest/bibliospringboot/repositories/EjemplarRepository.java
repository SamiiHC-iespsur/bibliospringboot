package ada.sami.apirest.bibliospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.sami.apirest.bibliospringboot.entities.Ejemplar;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

}
