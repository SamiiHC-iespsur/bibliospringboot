package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.sami.apirest.bibliospringboot.entities.Edicion;
import ada.sami.apirest.bibliospringboot.repositories.EdicionRepository;

@Service
public class EdicionServiceImpl implements EdicionService {

    @Autowired
    EdicionRepository edicionRepository;

    @Override
    public Iterable<Edicion> findAll() {
        return edicionRepository.findAll();
    }

    @Override
    public Optional<Edicion> findById(String isbn) {
        return edicionRepository.findById(isbn);
    }

    @Override
    public Edicion save(Edicion edicion) {
        return edicionRepository.save(edicion);
    }

    @Override
    public void deleteById(String isbn) {
        edicionRepository.deleteById(isbn);
    }
}
