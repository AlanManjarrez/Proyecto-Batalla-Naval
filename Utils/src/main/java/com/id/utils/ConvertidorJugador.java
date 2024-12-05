/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.dtos_sh.JugadorDTO;
import com.id.domian.Jugador;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<JugadorDTO> toDTOList(List<Jugador> jugadores) {
        if (jugadores == null) {
            return new ArrayList<>();
        }
        List<JugadorDTO> lista = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            lista.add(toDTO(jugador));
        }
        return lista;
    }

    public static List<Jugador> toEntityList(List<JugadorDTO> jugadoresDTO) {
        if (jugadoresDTO == null) {
            return new ArrayList<>();
        }
        List<Jugador> lista = new ArrayList<>();
        for (JugadorDTO jugadorDTO : jugadoresDTO) {
            lista.add(toEntity(jugadorDTO));
        }
        return lista;
    }
}
