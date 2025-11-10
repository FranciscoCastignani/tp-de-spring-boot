package com.utn.tareas;

import com.utn.tareas.model.Priority;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mensajeService.mostrarBienvenida();
        tareaService.mostrarConfiguracion();
        System.out.println("Tareas iniciales:");
        System.out.println(tareaService.listarTodas());
        System.out.println("Agregando Tarea Nueva:");
        Tarea nuevaTarea = tareaService.agregarTarea("Crear aplicación completa con Spring Boot", Priority.ALTA);
        System.out.println("Tarea agregada: "+ nuevaTarea.getDescription());
        System.out.println("Tareas Pendientes:");
        System.out.println(tareaService.listarPendientes());
        Long idCompletar = 1L;
        boolean completada = tareaService.marcarComoCompletada(idCompletar);
        if (completada == true){
            System.out.println("Tarea #"+idCompletar+" marcada como completada.");
        } else {
            System.out.println("Tarea #"+idCompletar+" no encontrada.");
        }
        if (tareaService.debeMostrarEstadisticas()){
            System.out.println(tareaService.obtenerEstadisticas());
        }
        System.out.println(tareaService.listarCompletadas());
        mensajeService.mostrarDespedida();
    }
}
