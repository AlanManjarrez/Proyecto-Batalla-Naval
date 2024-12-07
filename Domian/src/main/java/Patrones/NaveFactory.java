/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Patrones;

import com.id.domian.Casilla;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class NaveFactory {

     public static INave crearNave(String tipo, Casilla casillaCabeza, Orientacion direccion) {
        if (tipo.equalsIgnoreCase("barco")) {   
            return new Barco(casillaCabeza, direccion);
        } else if (tipo.equalsIgnoreCase("submarino")) {
            return new Submarino(casillaCabeza, direccion);
        } else if (tipo.equalsIgnoreCase("crucero")) {
            return new Crucero(casillaCabeza, direccion);
        } else if (tipo.equalsIgnoreCase("porta aviones")) {
            return new Portaviones(casillaCabeza, direccion);
        } else {
            throw new IllegalArgumentException("No se conoce ese tipo de nave: " + tipo);
        }
    }
}
