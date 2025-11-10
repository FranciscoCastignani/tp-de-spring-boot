package com.utn.tareas.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private Long id;
    private String description;
    private boolean completed;
    private Priority priority;
}
