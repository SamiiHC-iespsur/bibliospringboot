package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.sami.apirest.bibliospringboot.entities.Libro;
import ada.sami.apirest.bibliospringboot.repositories.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    LibroRepository libroRepository;

    @Override
    public Iterable<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(int id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void deleteById(int id) {
        libroRepository.deleteById(id);
    }
}
