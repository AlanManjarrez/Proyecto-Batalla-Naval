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
public class Barco extends INave implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public Barco(  Casilla casillaCabeza, Orientacion direccion) {
        super(1, casillaCabeza, direccion);
    }
    public Barco(){
        
    }

    @Override
    public String getTipo() {
        return "barco";
    }
     
}
