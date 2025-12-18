package ada.sami.apirest.bibliospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ada.sami.apirest.bibliospringboot.entities.Ejemplar;
import ada.sami.apirest.bibliospringboot.repositories.EjemplarRepository;

@Service
public class EjemplarServiceImpl implements EjemplarService {

    @Autowired
    EjemplarRepository ejemplarRepository;

    @Override
    public Iterable<Ejemplar> findAll() {
        return ejemplarRepository.findAll();
    }

    @Override
    public Optional<Ejemplar> findById(int id) {
        return ejemplarRepository.findById(id);
    }

    @Override
    public Ejemplar save(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    @Override
    public void deleteById(int id) {
        ejemplarRepository.deleteById(id);
    }
}
