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
public class Portaviones extends INave implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public Portaviones(Casilla casillaCabeza, Orientacion direccion) {
        super(4, casillaCabeza, direccion);
    }
    
    public Portaviones(){
        
    }

    @Override
    public String getTipo() {
        return "porta aviones";
    }

}
