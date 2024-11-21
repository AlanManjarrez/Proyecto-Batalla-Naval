/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.dtos_sh;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class NaveDTO {

    private int longitud;
    private EstadoNaveDTO estado;
    private CasillaDTO casilla;
    private OrientacionDTO direccion;
    private String tipo;

    public NaveDTO(int longitud, EstadoNaveDTO estado, CasillaDTO casilla, OrientacionDTO direccion, String tipo) {
        this.longitud = longitud;
        this.estado = estado;
        this.casilla = casilla;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public EstadoNaveDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoNaveDTO estado) {
        this.estado = estado;
    }

    public CasillaDTO getCasilla() {
        return casilla;
    }

    public void setCasilla(CasillaDTO casilla) {
        this.casilla = casilla;
    }

    public OrientacionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(OrientacionDTO direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
