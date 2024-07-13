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
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareas());
        } catch (TareaExcepcion e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/completadas")
    public ResponseEntity<?> obtenerTareasCompletadas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareasCompletadas(true));
        } catch (TareaExcepcion e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/nocompletadas")
    public ResponseEntity<?> obtenerTareasNoCompletadas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareasCompletadas(false));
        } catch (TareaExcepcion e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarTarea(@RequestBody TareaEntidad tarea) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tareaServicio.guardarTarea(tarea));
        } catch (TareaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id) {
        try {
            TareaEntidad tarea = tareaServicio.eliminarTarea(id);
            return ResponseEntity.status(HttpStatus.OK).body(tarea);
        } catch (TareaExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
