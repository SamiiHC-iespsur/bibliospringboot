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

import ada.sami.apirest.bibliospringboot.entities.Ejemplar;
import ada.sami.apirest.bibliospringboot.services.EjemplarService;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {

    @Autowired
    private EjemplarService ejemplarService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Ejemplar> ejemplares = StreamSupport.stream(ejemplarService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (ejemplares.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ejemplares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Ejemplar> ejemplar = ejemplarService.findById(id);
        return ejemplar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Ejemplar ejemplar) {
        if (ejemplar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo de la solicitud no puede estar vacío.");
        }
        Ejemplar saved = ejemplarService.save(ejemplar);
        return ResponseEntity.created(URI.create("/ejemplares/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Ejemplar ejemplarNuevo, @PathVariable int id) {
        Optional<Ejemplar> ejemplarActual = ejemplarService.findById(id);

        if (ejemplarActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (ejemplarNuevo == null || ejemplarNuevo.getEstado() == null && ejemplarNuevo.getEdicion() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo no contiene ningún parámetro válido");
        }

        if (ejemplarNuevo.getEstado() != null) {
            ejemplarActual.get().setEstado(ejemplarNuevo.getEstado());
        }
        if (ejemplarNuevo.getEdicion() != null) {
            ejemplarActual.get().setEdicion(ejemplarNuevo.getEdicion());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ejemplarService.save(ejemplarActual.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Ejemplar> ejemplar = ejemplarService.findById(id);
        if (ejemplar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ejemplarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
