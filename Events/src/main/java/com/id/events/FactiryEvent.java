/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import com.id.domian.Jugador;

/**
 *
 * @author JESUS
 */
public class FactiryEvent {
    
    public static Event<?> createEvent(typeEvents type,Object payload){
        switch (type) {
            case JugadorConectado:
                return new JugadorConectadoEvent(type,(Jugador)payload);
            
            default:
                throw new AssertionError();
        }
    }
}
