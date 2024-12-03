/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.dtos_sh;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class JuegoDTO {

    private List<JugadorDTO> jugadores;
    private JugadorDTO jugadorTurno;
    private int timepoLimite;
    private TableroDTO jugador1TableroPrincipal;
    private TableroDTO jugador1TableroDisparo;
    private TableroDTO jugador2TableroPrincipal;
    private TableroDTO jugador2TableroDisparo;

    public JuegoDTO(List<JugadorDTO> jugadores, JugadorDTO jugadorTurno, int timepoLimite, TableroDTO jugador1TableroPrincipal, TableroDTO jugador1TableroDisparo, TableroDTO jugador2TableroPrincipal, TableroDTO jugador2TableroDisparo) {
        this.jugadores = jugadores;
        this.jugadorTurno = jugadorTurno;
        this.timepoLimite = timepoLimite;
        this.jugador1TableroPrincipal = jugador1TableroPrincipal;
        this.jugador1TableroDisparo = jugador1TableroDisparo;
        this.jugador2TableroPrincipal = jugador2TableroPrincipal;
        this.jugador2TableroDisparo = jugador2TableroDisparo;
    }
    
    public JuegoDTO(){
        this.jugadores = new ArrayList<>(); 
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public JugadorDTO getJugadorTurno() {
        return jugadorTurno;
    }

    public void setJugadorTurno(JugadorDTO jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public int getTimepoLimite() {
        return timepoLimite;
    }

    public void setTimepoLimite(int timepoLimite) {
        this.timepoLimite = timepoLimite;
    }

    public TableroDTO getJugador1TableroPrincipal() {
        return jugador1TableroPrincipal;
    }

    public void setJugador1TableroPrincipal(TableroDTO jugador1TableroPrincipal) {
        this.jugador1TableroPrincipal = jugador1TableroPrincipal;
    }

    public TableroDTO getJugador1TableroDisparo() {
        return jugador1TableroDisparo;
    }

    public void setJugador1TableroDisparo(TableroDTO jugador1TableroDisparo) {
        this.jugador1TableroDisparo = jugador1TableroDisparo;
    }

    public TableroDTO getJugador2TableroPrincipal() {
        return jugador2TableroPrincipal;
    }

    public void setJugador2TableroPrincipal(TableroDTO jugador2TableroPrincipal) {
        this.jugador2TableroPrincipal = jugador2TableroPrincipal;
    }

    public TableroDTO getJugador2TableroDisparo() {
        return jugador2TableroDisparo;
    }

    public void setJugador2TableroDisparo(TableroDTO jugador2TableroDisparo) {
        this.jugador2TableroDisparo = jugador2TableroDisparo;
    }

}
