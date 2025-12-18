package ada.sami.apirest.bibliospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ada.sami.apirest.bibliospringboot.entities.Edicion;

public interface EdicionRepository extends JpaRepository<Edicion, String> {

}
