/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Patrones;

import com.id.domian.Casilla;
import java.io.Serializable;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public abstract class INave implements Serializable{
    private static final long serialVersionUID = 1L;
    private int longitud;
    private EstadoNave estado;
    private Casilla casillaCabeza;
    private Orientacion direccion;
    
    public INave(){
        
    }
    
    public INave(int longitud, Casilla casillaCabeza, Orientacion direccion) {
        this.longitud = longitud;
        this.estado = estado.SANO;
        this.casillaCabeza = casillaCabeza;
        this.direccion = direccion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public EstadoNave getEstado() {
        return estado;
    }

    public void setEstado(EstadoNave estado) {
        this.estado = estado;
    }

    public Casilla getCasillaCabeza() {
        return casillaCabeza;
    }

    public void setCasillaCabeza(Casilla casillaCabeza) {
        this.casillaCabeza = casillaCabeza;
    }

    public Orientacion getDireccion() {
        return direccion;
    }

    public void setDireccion(Orientacion direccion) {
        this.direccion = direccion;
    }

    public abstract String getTipo();
    
}
