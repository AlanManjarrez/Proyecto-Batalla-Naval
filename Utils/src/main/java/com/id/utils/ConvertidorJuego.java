/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.domian.Casilla;
import com.id.domian.Juego;
import com.id.domian.Tablero;
import com.id.dtos_sh.JuegoDTO;
import java.lang.System.Logger.Level;
import java.util.logging.Logger;

/**
 *
 * @author JESUS
 */
public class ConvertidorJuego {
   
    
    public static JuegoDTO toDTO(Juego juego) {
        if (juego == null) {
            return null;
        }
        
        System.out.println("Estado del tablero en el servidor antes de la conversión:");
        Tablero tableroServidor = juego.getJugador1TableroPrincipal(); // Cambia a Jugador2 si aplica.
        /*
        Casilla[][] casillasServidor = tableroServidor.getCasilla();
        for (int i = 0; i < casillasServidor.length; i++) {
            for (int j = 0; j < casillasServidor[i].length; j++) {
                System.out.println("Casilla [" + i + "][" + j + "]: Estado=" + casillasServidor[i][j].isEstado());
            }
        }*/
        
        JuegoDTO juegoDTO = JuegoDTO.getInstance(); // Usar el singleton       
        
        juegoDTO.setJugadores(ConvertidorJugador.toDTOList(juego.getJugadores()));
        juegoDTO.setJugadorTurno(ConvertidorJugador.toDTO(juego.getTurnoJugador()));
        juegoDTO.setJugador1TableroPrincipal(ConvertidoTablero.toDTO(juego.getJugador1TableroPrincipal()));
        juegoDTO.setJugador2TableroPrincipal(ConvertidoTablero.toDTO(juego.getJugador2TableroPrincipal()));
        
        
        
        
        return juegoDTO;
    }

    // Convertir de JuegoDTO a Juego usando el Singleton
    public static Juego toEntity(JuegoDTO juegoDTO) {
        if (juegoDTO == null) {
            return null;
        }

        // Obtener la instancia única del Singleton
        Juego juego = Juego.getInstance();

        // Actualizar los datos del Singleton con los valores del DTO
        juego.setJugadores(ConvertidorJugador.toEntityList(juegoDTO.getJugadores()));
        juego.setTurnoJugador(ConvertidorJugador.toEntity(juegoDTO.getJugadorTurno()));
        juego.setJugador1TableroPrincipal(ConvertidoTablero.toEntity(juegoDTO.getJugador1TableroPrincipal()));
        juego.setJugador2TableroPrincipal(ConvertidoTablero.toEntity(juegoDTO.getJugador2TableroPrincipal()));

        return juego;
    }
}
