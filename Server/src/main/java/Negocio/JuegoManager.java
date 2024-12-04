/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Patrones.INave;
import java.util.ArrayList;
import java.util.List;
import com.id.domian.Juego;
import com.id.domian.Jugador;
import Patrones.NaveFactory;
import Patrones.Orientacion;
import com.id.domian.ConfiguracionNaves;
import com.id.domian.Tablero;
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
    private final BarcoManager barcoManager;
    private final Juego juego;
    private static final Logger LOG = Logger.getLogger(JuegoManager.class.getName());
    private String codigoUnirse; 

     private JuegoManager() {
        this.juego = Juego.getInstance();
        this.codigoUnirse = "ABC123";
        this.barcoManager = new BarcoManager();
    }

    public static synchronized JuegoManager getInstance() {
        if (instance == null) {
            instance = new JuegoManager();
        }
        return instance;
    }

    // Agregar un jugador y configurar sus naves
    public boolean unirJugadorSinCodigo(Jugador jugador) {
        synchronized (juego) {
            if (juego.getJugadores().size() >= 2) {
                LOG.log(Level.INFO, "El juego ya está lleno. Jugador: " + jugador.getNombre());
                return false; 
            }

            juego.getJugadores().add(jugador);
            LOG.log(Level.INFO, "Jugador unido sin código: " + jugador.getNombre());

            // Configurar naves para este jugador
            barcoManager.configurarJugador(jugador, ConfiguracionNaves.defaultConfig());
            return true; 
        }
    }
    
    public void configurarTablero(Jugador jugador, Tablero tablero) {
        synchronized (juego) {
            if (juego.getJugadores().size() < 2) {
                LOG.log(Level.WARNING, "No hay suficientes jugadores registrados para configurar el tablero.");
                return;
            }

            if (juego.getJugadores().get(0).equals(jugador)) {
                juego.setJugador1TableroPrincipal(tablero);
                LOG.log(Level.INFO, "Tablero principal asignado al Jugador 1: " + jugador.getNombre());
            } else if (juego.getJugadores().get(1).equals(jugador)) {
                juego.setJugador2TableroPrincipal(tablero);
                LOG.log(Level.INFO, "Tablero principal asignado al Jugador 2: " + jugador.getNombre());
            } else {
                LOG.log(Level.WARNING, "El jugador no está registrado en el juego.");
            }
        }
    }
    
    public boolean ambosTablerosConfigurados() {
        synchronized (juego) {
            return juego.getJugador1TableroPrincipal() != null && juego.getJugador2TableroPrincipal() != null;
        }
    }
    

    // Obtener naves para un jugador específico
   public List<INave> obtenerNaves(Jugador jugador) {
        return barcoManager.obtenerNaves(jugador);
    }
    
   public Juego getEstadoDelJuego() {
        return juego; // Asegúrate de que `juego` tenga toda la información actualizada
    }
    
}
