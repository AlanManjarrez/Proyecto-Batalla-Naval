/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import java.io.Serializable;
import com.id.domian.Jugador;
/**
 *
 * @author JESUS
 */
public class ConexionExitosaEvent extends Event<Jugador> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public ConexionExitosaEvent(typeEvents type, Jugador payload) {
        super(type, payload);
    }
    
    public ConexionExitosaEvent(){
        super(null, null);
    }
}
