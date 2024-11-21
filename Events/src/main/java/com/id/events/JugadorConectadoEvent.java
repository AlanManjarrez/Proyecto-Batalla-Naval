/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;
import com.id.domian.Jugador;
import java.io.Serializable;
/**
 *
 * @author JESUS
 */
public class JugadorConectadoEvent extends Event<Jugador> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public JugadorConectadoEvent(typeEvents type, Jugador payload) {
        super(type, payload);
    }
    
    public JugadorConectadoEvent(){
        super(null, null);
    }
}
