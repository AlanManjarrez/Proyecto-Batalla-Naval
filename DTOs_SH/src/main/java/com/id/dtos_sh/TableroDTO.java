/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.dtos_sh;

import java.util.List;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class TableroDTO {

    private int tamaño;
    private CasillaDTO[][] casillas;
    private List<NaveDTO> naves;
    private List<DisparoDTO> disparos;

    public TableroDTO(int tamaño, CasillaDTO[][] casillas, List<NaveDTO> naves) {
        this.tamaño = tamaño;
        this.casillas = casillas;
        this.naves = naves;
        disparos = null;
    }

    public TableroDTO() {
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public CasillaDTO[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(CasillaDTO[][] casillas) {
        this.casillas = casillas;
    }

    public List<NaveDTO> getNaves() {
        return naves;
    }

    public void setNaves(List<NaveDTO> naves) {
        this.naves = naves;
    }

    public List<DisparoDTO> getDisparos() {
        return disparos;
    }

    public void setDisparos(List<DisparoDTO> disparos) {
        this.disparos = disparos;
    }
    
    public CasillaDTO getCasilla(int fila, int columna) {
        if (casillas != null && fila >= 0 && columna >= 0 && fila < tamaño && columna < tamaño) {
            return casillas[fila][columna];
        }
        return null; // Retorna null si las coordenadas están fuera de los límites
    }

}
