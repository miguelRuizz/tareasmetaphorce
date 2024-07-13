package mx.metaphorce.demo.tarea;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TareaServicio {

    private final TareaRepositorio tareaRepositorio;

    public List<TareaEntidad> obtenerTareas() {
        List<TareaEntidad> tareas = (List<TareaEntidad>) tareaRepositorio.findAll();
        if (tareas.isEmpty()) {
            throw new RuntimeException("No hay tareas registradas");
        }
        return tareas;
    }

    public List<TareaEntidad> obtenerTareasCompletadas(boolean completada) {
        List<TareaEntidad> tareas = (List<TareaEntidad>) tareaRepositorio.findAllByCompletada(completada);
        if (tareas.isEmpty()) {
            throw new RuntimeException("No hay tareas registradas");
        }
        return tareas;
    }

    public TareaEntidad guardarTarea(TareaEntidad tarea) {
        TareaEntidad nueva = tareaRepositorio.findByTitulo(tarea.getTitulo()).orElse(null);

        if(isUpdate(tarea)) {
            if(nueva == null) {
                tarea = tareaRepositorio.save(tarea);
            }
        } else {
            if(nueva != null) {
                throw new RuntimeException("La tarea ya existe");
            } else {
                tarea = tareaRepositorio.save(tarea);
            }
        }

        return tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(Long id) {
        TareaEntidad tarea = tareaRepositorio.findById(id).orElse(null);
        if(tarea == null) {
            throw new RuntimeException("La tarea no existe");
        }
        tareaRepositorio.deleteById(id);
    }

    private boolean isUpdate(TareaEntidad tarea) {
        return tarea.getId() != null;
    }

}
