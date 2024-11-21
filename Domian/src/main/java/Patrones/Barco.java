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
public class Barco extends INave{
    
    public Barco(  Casilla casillaCabeza, Orientacion direccion) {
        super(1, casillaCabeza, direccion);
    }

    @Override
    public String getTipo() {
        return "barco";
    }
     
}
