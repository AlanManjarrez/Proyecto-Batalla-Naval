/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String color;

    public Jugador() {
        this.id = UUID.randomUUID().toString();
    }

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
