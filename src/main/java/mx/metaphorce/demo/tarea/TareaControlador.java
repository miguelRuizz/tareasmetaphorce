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
    public ResponseEntity<?> obtenerTareasCompletadas() {
        return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareasCompletadas(true));
    }

    @GetMapping("/nocompletadas")
    public ResponseEntity<?> obtenerTareasNoCompletadas() {
        return ResponseEntity.status(HttpStatus.OK).body(tareaServicio.obtenerTareasCompletadas(false));
    }

    @PostMapping
    public ResponseEntity<?> guardarTarea(@RequestBody TareaEntidad tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaServicio.guardarTarea(tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id) {
        tareaServicio.eliminarTarea(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
