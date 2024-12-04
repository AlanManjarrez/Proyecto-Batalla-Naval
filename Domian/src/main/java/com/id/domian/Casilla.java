/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import java.io.Serializable;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Casilla implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Coordenada cordenada;

    public Casilla() {
    }

    public Casilla(Coordenada cordenada) {
        this.cordenada = cordenada;
    }

    public Coordenada getCordenada() {
        return cordenada;
    }

    public void setCordenada(Coordenada cordenada) {
        this.cordenada = cordenada;
    }

}
