package com.utn.tareas.service;

import com.utn.tareas.model.Priority;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository;

    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    // ○ Agregar una nueva tarea (recibe descripción y prioridad)
    public Tarea agregarTarea(String description, Priority priority){
        return tareaRepository.guardarTarea(Tarea.builder().description(description)
                .priority(priority).completed(false).build());
    }

    //○ Listar todas las tareas
    public List<Tarea> listarTodas(){
        return tareaRepository.getTareas();
    }

    //○ Listar solo las tareas pendientes (no completadas)
    public List<Tarea> listarPendientes(){
        return tareaRepository.getTareas().stream().filter(tarea -> !tarea.isCompleted()).collect(Collectors.toList());
    }

    //○ Listar solo las tareas completadas
    public List<Tarea> listarCompletadas(){
        return tareaRepository.getTareas().stream().filter(tarea -> tarea.isCompleted()).collect(Collectors.toList());
    }

    //○ Marcar una tarea como completada (recibe ID)
    public boolean marcarComoCompletada(Long id){
        return tareaRepository.buscarPorId(id).
                map(tarea -> {
                    tarea.setCompleted(true);
                    return true;})
                .orElse(false);
    }

    //○ Obtener estadísticas (retorna String formateado con: total, completadas, pendientes)
    public String obtenerEstadisticas(){
        int total = tareaRepository.getTareas().size();
        int completadas =  listarCompletadas().size();
        int pendientes = listarPendientes().size();

        return String.format(
                "\n=== Estadísticas de Tareas ===\n" +
                        "Total: %d\n" +
                        "Completadas: %d\n" +
                        "Pendientes: %d\n" +
                        "==============================",
                total, completadas, pendientes
        );
    }

    //Crea un método que imprima las propiedades de configuración
    public void mostrarConfiguracion(){
        System.out.println("\n======= Configuracion de la Aplicacion ======");
        System.out.println("Nombre de la app: " + nombreApp );
        System.out.println("Max de tareas: " + maxTareas );
        System.out.println("Mostrar estadisticas: " + mostrarEstadisticas);
        System.out.println("==============================================\n");
    }

    public boolean debeMostrarEstadisticas() {
        return mostrarEstadisticas;
    }
}
