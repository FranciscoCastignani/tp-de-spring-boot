package com.utn.tareas.repository;

import com.utn.tareas.model.Priority;
import com.utn.tareas.model.Tarea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {
    private final List<Tarea> tareas;
    private final AtomicLong idGenerator;

    public TareaRepository() {
        this.tareas = new ArrayList<>();
        this.idGenerator = new AtomicLong(1);
        //Inicialización con 5 tareas de ejemplo diferentes
        tareas.add(Tarea.builder()
                .id(idGenerator.incrementAndGet()).description("Estudiar Spring Boot fundamentos")
                .completed(false).priority(Priority.ALTA).build());
        tareas.add(Tarea.builder()
                .id(idGenerator.incrementAndGet()).description("Completar ejercicios de inyección de dependencias")
                .completed(true).priority(Priority.MEDIA).build());
        tareas.add(Tarea.builder()
                .id(idGenerator.incrementAndGet()).description("Revisar documentación de Spring Framework")
                .completed(false).priority(Priority.BAJA).build());
        tareas.add(Tarea.builder()
                .id(idGenerator.incrementAndGet()).description("Implementar pruebas unitarias del servicio")
                .completed(true).priority(Priority.BAJA).build());
        tareas.add(Tarea.builder()
                .id(idGenerator.incrementAndGet()).description("Configurar profiles para diferentes entornos")
                .completed(false).priority(Priority.ALTA).build());
    }

    // ○ Guardar una tarea (genera ID automático)
    public Tarea guardarTarea(Tarea tarea){
        tarea.setId(idGenerator.incrementAndGet());
        tareas.add(tarea);
        return tarea;
    }

    // ○ Obtener todas las tareas
    public List<Tarea>  getTareas() {
        return Collections.unmodifiableList(tareas);
    }

    // ○ Buscar por ID (retorna Optional<Tarea>)
    public Optional<Tarea> buscarPorId(Long id){
        return tareas.stream().filter(tarea -> tarea.getId().equals(id)).findFirst();
    }

    // ○ Eliminar por ID
   public boolean eliminarPorId(Long id){
        return tareas.stream().toList().removeIf(tarea -> tarea.getId().equals(id));
   }

   public int contarTareas(){
        return tareas.size();
   }
}
