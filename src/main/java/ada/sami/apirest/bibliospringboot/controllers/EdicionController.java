package ada.sami.apirest.bibliospringboot.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ada.sami.apirest.bibliospringboot.entities.Edicion;
import ada.sami.apirest.bibliospringboot.services.EdicionService;

@RestController
@RequestMapping("/ediciones")
public class EdicionController {

    @Autowired
    private EdicionService edicionService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Edicion> ediciones = StreamSupport.stream(edicionService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (ediciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ediciones);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> findById(@PathVariable String isbn) {
        Optional<Edicion> edicion = edicionService.findById(isbn);
        return edicion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Edicion edicion) {
        if (edicion == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo de la solicitud no puede estar vacío.");
        }
        Edicion saved = edicionService.save(edicion);
        return ResponseEntity.created(URI.create("/ediciones/" + saved.getIsbn())).body(saved);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> update(@RequestBody Edicion edicionNueva, @PathVariable String isbn) {
        Optional<Edicion> edicionActual = edicionService.findById(isbn);

        if (edicionActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (edicionNueva == null
                || (edicionNueva.getAnnoPublicacion() == null && edicionNueva.getEditorial() == null
                && edicionNueva.getIdioma() == null && edicionNueva.getLibro() == null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo no contiene ningún parámetro válido");
        }

        if (edicionNueva.getAnnoPublicacion() != null) {
            edicionActual.get().setAnnoPublicacion(edicionNueva.getAnnoPublicacion());
        }
        if (edicionNueva.getEditorial() != null) {
            edicionActual.get().setEditorial(edicionNueva.getEditorial());
        }
        if (edicionNueva.getIdioma() != null) {
            edicionActual.get().setIdioma(edicionNueva.getIdioma());
        }
        if (edicionNueva.getLibro() != null) {
            edicionActual.get().setLibro(edicionNueva.getLibro());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(edicionService.save(edicionActual.get()));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@PathVariable String isbn) {
        Optional<Edicion> edicion = edicionService.findById(isbn);
        if (edicion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        edicionService.deleteById(isbn);
        return ResponseEntity.noContent().build();
    }
}
