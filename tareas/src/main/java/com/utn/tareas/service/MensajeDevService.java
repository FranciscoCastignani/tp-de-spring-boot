package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {
    public void mostrarBienvenida(){
        System.out.println("Bienvenida desde Mensaje Dev Service!");
    }
    public void mostrarDespedida(){
        System.out.println("Despedida desde Mensaje Dev Service!");
    }
}
