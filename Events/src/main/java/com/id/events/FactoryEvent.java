/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import Patrones.INave;
import com.id.domian.Disparo;
import com.id.domian.Juego;
import com.id.domian.Jugador;
import com.id.domian.Tablero;
import java.util.List;

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
            case solicitudNaves:
                if (payload == null || payload instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<INave> listaNaves = (List<INave>) payload;
                    return new SolicitudNaves(type, listaNaves);
                }else{
                    throw new IllegalArgumentException("Lista desconocidad "+ type);
                }
            case JugadorListo:
                return new JugadorListo(type, (String) payload);
            case ActualizarTablero:
                return new TableroActualizado(type, (Tablero) payload);
            case IniciarPartida:
                return new IniciarPartida(type, (Juego)payload);
            case DisparoRealizado:
                return new DisparoRealizado(type, (Disparo)payload);
            case ActualizarJuego:
                return new ActualizaJuego(type, (Juego)payload);
            case RecibirDisparo:
                return new RecibirDisparo(type, (Disparo)payload);
            default:
                throw new AssertionError();
        }
    }
}
