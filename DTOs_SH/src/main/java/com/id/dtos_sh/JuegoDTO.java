/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.dtos_sh;

import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class JuegoDTO {
    
    private List<Observer> observers = new ArrayList<>();
    private static JuegoDTO instance;
    private List<JugadorDTO> jugadores;
    private JugadorDTO jugadorTurno;
    private int timepoLimite;
    private TableroDTO jugador1TableroPrincipal;
    private TableroDTO jugador1TableroDisparo;
    private TableroDTO jugador2TableroPrincipal;
    private TableroDTO jugador2TableroDisparo;

    private JuegoDTO(List<JugadorDTO> jugadores, JugadorDTO jugadorTurno, int timepoLimite, TableroDTO jugador1TableroPrincipal, TableroDTO jugador1TableroDisparo, TableroDTO jugador2TableroPrincipal, TableroDTO jugador2TableroDisparo) {
        this.jugadores = jugadores;
        this.jugadorTurno = jugadorTurno;
        this.timepoLimite = timepoLimite;
        this.jugador1TableroPrincipal = jugador1TableroPrincipal;
        this.jugador1TableroDisparo = jugador1TableroDisparo;
        this.jugador2TableroPrincipal = jugador2TableroPrincipal;
        this.jugador2TableroDisparo = jugador2TableroDisparo;
    }
    
     private JuegoDTO() {
        this.jugadores = new ArrayList<>();
        this.jugador1TableroPrincipal = null;
        this.jugador2TableroPrincipal = null;
    }

    // Método estático para obtener la única instancia
    public static synchronized JuegoDTO getInstance() {
        if (instance == null) {
            instance = new JuegoDTO();
        }
        return instance;
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
        notifyObservers();
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
        notifyObservers(); 
    }

    public TableroDTO getJugador2TableroDisparo() {
        return jugador2TableroDisparo;
    }

    public void setJugador2TableroDisparo(TableroDTO jugador2TableroDisparo) {
        this.jugador2TableroDisparo = jugador2TableroDisparo;
    }
    
    public void addObserver(Observer observer) {
         if (!observers.contains(observer)) { // Asegúrate de no agregar duplicados
            observers.add(observer);
            System.out.println("Observador agregado: " + observer.getClass().getName());
            System.out.println("Total de observadores: " + observers.size());
        } else {
            System.out.println("El observador ya estaba registrado.");
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println("Notificando a " + observers.size() + " observadores.");
        System.out.println("Modelo actual en notifyObservers: " + this);
        for (Observer observer : observers) {
            System.out.println("Notificando a observador: " + observer.getClass().getName());
            observer.update(this); // Notifica a los observadores.
        }
    }
    
    public List<Observer> getObservers() {
        System.out.println("Observer enviado"+observers.size());
        return new ArrayList<>(observers);
    }
    
    
}
