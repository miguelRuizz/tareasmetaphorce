package mx.metaphorce.demo.tarea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepositorio extends CrudRepository<TareaEntidad, Long> {

    Iterable<TareaEntidad> findAllByCompletada(boolean completada);

    Optional<TareaEntidad> findByTitulo(String titulo);

}
