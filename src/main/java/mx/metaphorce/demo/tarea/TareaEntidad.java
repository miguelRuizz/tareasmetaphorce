package mx.metaphorce.demo.tarea;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TareaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private boolean completada;

}
