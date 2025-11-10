package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {
    public void mostrarBienvenida(){
        System.out.println("Bienvenida desde Mensaje Prod Service!");
    }
    public void mostrarDespedida(){
        System.out.println("Despedida desde Mensaje Prod Service!");
    }
}
