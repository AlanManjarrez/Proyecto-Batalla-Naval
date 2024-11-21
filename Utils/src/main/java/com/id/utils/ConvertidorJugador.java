/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.dtos_sh.JugadorDTO;
import com.id.domian.Jugador;

/**
 *
 * @author JESUS
 */
public class ConvertidorJugador {
    
    public static JugadorDTO toDTO(Jugador jugador){
        if (jugador == null) {
            return null;
        }
        
        return new JugadorDTO(jugador.getNombre(), jugador.getColor());
    }
    
    public static Jugador toEntity(JugadorDTO jugador){
        if (jugador == null) {
            return null;
        }
        
        return new Jugador(jugador.getNombre(), jugador.getColor());
    }
    
}
