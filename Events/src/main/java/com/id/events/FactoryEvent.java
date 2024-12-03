/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import com.id.domian.Juego;
import com.id.domian.Jugador;
import com.id.domian.pruba;

/**
 *
 * @author JESUS
 */
public class FactoryEvent {
    
    public static Event<?> createEvent(typeEvents type,Object payload){
        switch (type) {
            case JugadorConectado:
                return new JugadorConectadoEvent(type,(Jugador)payload);
            case PartidaLlena:
                return new PartidaLlenaEvent(type, (String)payload);
            case ConexionExitosa:
                return new ConexionExitosaEvent(type, (Jugador)payload);
            default:
                throw new AssertionError();
        }
    }
}
