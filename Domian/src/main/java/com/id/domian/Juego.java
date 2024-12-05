/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Juego implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static Juego instancia;
    private List<Jugador> jugadores;
    private Jugador turnoJugador;
    public static final int tiempoLimite = 30;
    private Tablero jugador1TableroPrincipal;
    private Tablero jugador1TableroDisparo;
    private Tablero jugador2TableroPrincipal;
    private Tablero jugador2TableroDisparo;

    private Juego() {
        this.jugadores = new ArrayList<>();
        this.turnoJugador = null;
        this.jugador1TableroPrincipal = null;
        this.jugador1TableroDisparo = null;
        this.jugador2TableroPrincipal = null;
        this.jugador2TableroDisparo = null;
    }

    public static synchronized Juego getInstance() {
        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getTurnoJugador() {
        return turnoJugador;
    }

    public void setTurnoJugador(Jugador turnoJugador) {
        this.turnoJugador = turnoJugador;
    }

    public Tablero getJugador1TableroPrincipal() {
        return jugador1TableroPrincipal;
    }

    public void setJugador1TableroPrincipal(Tablero jugador1TableroPrincipal) {
        this.jugador1TableroPrincipal = jugador1TableroPrincipal;
    }

    public Tablero getJugador1TableroDisparo() {
        return jugador1TableroDisparo;
    }

    public void setJugador1TableroDisparo(Tablero jugador1TableroDisparo) {
        this.jugador1TableroDisparo = jugador1TableroDisparo;
    }

    public Tablero getJugador2TableroPrincipal() {
        return jugador2TableroPrincipal;
    }

    public void setJugador2TableroPrincipal(Tablero jugador2TableroPrincipal) {
        this.jugador2TableroPrincipal = jugador2TableroPrincipal;
    }

    public Tablero getJugador2TableroDisparo() {
        return jugador2TableroDisparo;
    }

    public void setJugador2TableroDisparo(Tablero jugador2TableroDisparo) {
        this.jugador2TableroDisparo = jugador2TableroDisparo;
    }

    public boolean espacio() {
        return jugadores.size() == 2;
    }
}
