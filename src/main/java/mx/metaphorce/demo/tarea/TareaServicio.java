package mx.metaphorce.demo.tarea;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TareaServicio {

    private final TareaRepositorio tareaRepositorio;

    public List<TareaEntidad> obtenerTareas() throws TareaExcepcion {
        List<TareaEntidad> tareas = (List<TareaEntidad>) tareaRepositorio.findAll();
        if (tareas.isEmpty()) {
            throw new TareaExcepcion("No hay tareas registradas");
        }
        return tareas;
    }

    public List<TareaEntidad> obtenerTareasCompletadas(boolean completada) throws TareaExcepcion {
        List<TareaEntidad> tareas = (List<TareaEntidad>) tareaRepositorio.findAllByCompletada(completada);
        if (tareas.isEmpty()) {
            throw new TareaExcepcion("No hay tareas registradas");
        }
        return tareas;
    }

    public TareaEntidad guardarTarea(TareaEntidad tarea) throws TareaExcepcion {
        TareaEntidad nueva = tareaRepositorio.findByTitulo(tarea.getTitulo()).orElse(null);

        if(isUpdate(tarea)) {
            if(nueva == null) {
                tarea = tareaRepositorio.save(tarea);
            }
        } else {
            if(nueva != null) {
                throw new TareaExcepcion("La tarea ya existe");
            } else {
                tarea = tareaRepositorio.save(tarea);
            }
        }

        return tareaRepositorio.save(tarea);
    }

    public TareaEntidad eliminarTarea(Long id) throws TareaExcepcion {
        TareaEntidad tarea = tareaRepositorio.findById(id).orElse(null);
        if(tarea == null) {
            throw new TareaExcepcion("La tarea no existe");
        }
        tareaRepositorio.deleteById(id);
        return tarea;
    }

    private boolean isUpdate(TareaEntidad tarea) {
        return tarea.getId() != null;
    }

}
