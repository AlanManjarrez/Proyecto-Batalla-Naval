/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import java.util.ArrayList;
import java.util.List;
import com.id.domian.Juego;
import com.id.domian.Jugador;
import com.id.events.Event;
import com.id.events.FactoryEvent;
import com.id.events.typeEvents;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JESUS
 */
public class JuegoManager {
    private static JuegoManager instance;
    private final Juego juego;
    private static final Logger LOG = Logger.getLogger(JuegoManager.class.getName());
    private String codigoUnirse; 
    
    private JuegoManager() {
        this.juego = Juego.getInstance();
        this.codigoUnirse = "ABC123";
    }

    public static synchronized JuegoManager getInstance() {
        if (instance == null) {
            instance = new JuegoManager();
        }
        return instance;
    }
     
    public boolean unirJugadorSinCodigo(Jugador jugador) {
        synchronized (juego) {
            if (juego.getJugadores().size() >= 2) {
                LOG.log(Level.INFO, "El juego ya está lleno. Jugador: " + jugador.getNombre());
                return false; 
            }

            juego.getJugadores().add(jugador);
            LOG.log(Level.INFO, "Jugador unido sin código: " + jugador.getNombre());
            return true; 
        }
    }
    
    
}
