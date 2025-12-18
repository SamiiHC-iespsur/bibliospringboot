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

import ada.sami.apirest.bibliospringboot.entities.Libro;
import ada.sami.apirest.bibliospringboot.services.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Libro> libros = StreamSupport.stream(libroService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (libros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Libro> libro = libroService.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Libro libro) {
        if (libro == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo de la solicitud no puede estar vacío.");
        }
        Libro saved = libroService.save(libro);
        return ResponseEntity.created(URI.create("/libros/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Libro libroNuevo, @PathVariable int id) {
        Optional<Libro> libroActual = libroService.findById(id);

        if (libroActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (libroNuevo == null || libroNuevo.getTitulo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo no contiene ningún parámetro válido");
        }

        libroActual.get().setTitulo(libroNuevo.getTitulo());

        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.save(libroActual.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
