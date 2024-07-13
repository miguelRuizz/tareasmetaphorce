package mx.metaphorce.demo.tarea;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tareas")
public class TareaControlador {

    private final TareaServicio tareaServicio;

    @GetMapping
    public ResponseEntity<?> obtenerTareas() {
        return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareas());
    }

    @GetMapping("/completadas")
    public List<TareaEntidad> obtenerTareasCompletadas() {
        return tareaServicio.obtenerTareasCompletadas(true);
    }

    @GetMapping("/nocompletadas")
    public List<TareaEntidad> obtenerTareasNoCompletadas() {
        return tareaServicio.obtenerTareasCompletadas(false);
    }

    @PostMapping
    public TareaEntidad guardarTarea(@RequestBody TareaEntidad tarea) {
        return tareaServicio.guardarTarea(tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaServicio.eliminarTarea(id);
    }

}
