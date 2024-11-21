/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import Patrones.INave;
import java.util.List;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Tablero {

    private int tamaño;
    private Casilla[][] casilla;
    private List<INave> naves;
    private List<Disparo> disparos;

    public Tablero(int tamaño, Casilla[][] casilla, List<INave> naves) {
        this.tamaño = tamaño;
        this.casilla = casilla;
        this.naves = naves;
        this.disparos = null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public Casilla[][] getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla[][] casilla) {
        this.casilla = casilla;
    }

    public List<INave> getNaves() {
        return naves;
    }

    public void setNaves(List<INave> naves) {
        this.naves = naves;
    }

    public List<Disparo> getDisparos() {
        return disparos;
    }

    public void setDisparos(List<Disparo> disparos) {
        this.disparos = disparos;
    }

}
