/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Patrones.EstadoNave;
import Patrones.INave;
import java.util.ArrayList;
import java.util.List;
import com.id.domian.Juego;
import com.id.domian.Jugador;
import Patrones.NaveFactory;
import Patrones.Orientacion;
import com.id.domian.Casilla;
import com.id.domian.ConfiguracionNaves;
import com.id.domian.Coordenada;
import com.id.domian.Disparo;
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
            
            barcoManager.configurarJugador(jugador, ConfiguracionNaves.defaultConfig());
            return true; 
        }
    }
    
    public void configurarTablero(Jugador jugador, Tablero tablero) {
        synchronized (juego) {

            if (!juego.getJugadores().contains(jugador)) {
                LOG.log(Level.WARNING, "El jugador no está registrado en el juego: " + jugador.getNombre());
                return;
            }
            
            if (!juego.getJugadores().contains(jugador)) {
                LOG.log(Level.WARNING, "El jugador no está registrado en el juego: " + jugador.getNombre());
                return;
            }

        if (tablero.getCasilla() == null || tablero.getCasilla().length == 0) {
            LOG.log(Level.SEVERE, "El tablero proporcionado no tiene casillas inicializadas.");
            return;
        }

            if (juego.getJugadores().indexOf(jugador) == 0) {
                juego.setJugador1TableroPrincipal(tablero);
                LOG.log(Level.INFO, "Tablero principal asignado al Jugador 1: " + jugador.getNombre());
            } else if (juego.getJugadores().indexOf(jugador) == 1) {
                juego.setJugador2TableroPrincipal(tablero);
                LOG.log(Level.INFO, "Tablero principal asignado al Jugador 2: " + jugador.getNombre());
            } else {
                LOG.log(Level.WARNING, "Error al asignar el tablero. Jugador no reconocido: " + jugador.getNombre());
            }
            
            if (juego.getJugador1TableroPrincipal() != null && juego.getJugador2TableroPrincipal() != null) {
                LOG.log(Level.INFO, "Ambos tableros están configurados. Asignando turno inicial...");

                int turnoInicial = new java.util.Random().nextInt(2);
                juego.setTurnoJugador(juego.getJugadores().get(turnoInicial));
                LOG.log(Level.INFO, "Turno inicial asignado al jugador: " + juego.getTurnoJugador().getNombre());
            }
            
            
        }
    }
    
    public boolean ambosTablerosConfigurados() {
        synchronized (juego) {
            boolean configurados = juego.getJugador1TableroPrincipal() != null && juego.getJugador2TableroPrincipal() != null;
            LOG.log(Level.INFO, "Ambos tableros configurados: " + configurados);
            return configurados;
        }
    }
    
    
    public boolean procesarDisparo(Jugador jugador, Disparo disparo) {
        synchronized (juego) {
            Tablero tableroObjetivo;
            List<INave> navesObjetivo;


            if (juego.getJugadores().get(0).equals(jugador)) {
                tableroObjetivo = juego.getJugador2TableroPrincipal();
                navesObjetivo = tableroObjetivo.getNaves();
            } else if (juego.getJugadores().get(1).equals(jugador)) {
                tableroObjetivo = juego.getJugador1TableroPrincipal();
                navesObjetivo = tableroObjetivo.getNaves();
            } else {
                LOG.log(Level.WARNING, "El jugador no está registrado.");
                return false;
            }

            // Procesar el disparo en el tablero
            Casilla casillaObjetivo = tableroObjetivo.getCasilla()[disparo.getCasilla().getCordenada().getX()]
                                                        [disparo.getCasilla().getCordenada().getY()];
            System.out.println("estado"+casillaObjetivo.isEstado());

            if (casillaObjetivo.isEstado()) {
                // Ya fue disparada esta casilla
                LOG.log(Level.WARNING, "Disparo redundante. Casilla ya atacada.");
            }
            casillaObjetivo.setEstado(true);
            
            
            
            /*
            LOG.log(Level.INFO, "Estado de las casillas del Tablero de Jugador 1 desde el modelo del juego:");
            Tablero tableroJugador1 = juego.getJugador1TableroPrincipal();
            for (int i = 0; i < tableroJugador1.getTamaño(); i++) {
                for (int j = 0; j < tableroJugador1.getTamaño(); j++) {
                    LOG.log(Level.INFO, "Casilla [" + i + "][" + j + "]: Estado=" + tableroJugador1.getCasilla()[i][j].isEstado());
                }
            }

            LOG.log(Level.INFO, "Estado de las casillas del Tablero de Jugador 2 desde el modelo del juego:");
            Tablero tableroJugador2 = juego.getJugador2TableroPrincipal();
            for (int i = 0; i < tableroJugador2.getTamaño(); i++) {
                for (int j = 0; j < tableroJugador2.getTamaño(); j++) {
                    LOG.log(Level.INFO, "Casilla [" + i + "][" + j + "]: Estado=" + tableroJugador2.getCasilla()[i][j].isEstado());
                }
            }*/
            
            
            
            // Verificar si impacta en una nave
            boolean impacto = false;
            for (INave nave : navesObjetivo) {
                if (verificarImpacto(nave, disparo.getCasilla())) {
                    actualizarEstadoNave(nave, tableroObjetivo);
                    impacto = true;
                    LOG.log(Level.INFO, "Impacto confirmado en la nave: " + nave.getTipo());
                    break;
                }
            }

            // Cambiar turno solo si no hubo impacto
            if (!impacto) {
                cambiarTurno();
            }

            return true;
        }
    }
    
    
    private boolean verificarImpacto(INave nave, Casilla disparo) {
        // Obtener todas las casillas ocupadas por la nave
        List<Casilla> casillasNave = calcularCasillasDeNave(nave);

        for (Casilla casilla : casillasNave) {
            if (casilla.getCordenada().equals(disparo.getCordenada())) {
                return true;
            }
        }
        return false;
    }
    
    private void actualizarEstadoNave(INave nave, Tablero tablero) {
        List<Casilla> casillasNave = calcularCasillasDeNave(nave);

        boolean naveHundida = true;
        for (Casilla casilla : casillasNave) {
            if (!tablero.getCasilla()[casilla.getCordenada().getX()][casilla.getCordenada().getY()].isEstado()) {
                naveHundida = false;
                break;
            }
        }

        nave.setEstado(naveHundida ? EstadoNave.HUNDIDO : EstadoNave.AVERIADO);
    }
    
    private List<Casilla> calcularCasillasDeNave(INave nave) {
        List<Casilla> casillas = new ArrayList<>();
        Casilla casillaCabeza = nave.getCasillaCabeza();

        for (int i = 0; i < nave.getLongitud(); i++) {
            if (nave.getDireccion() == Orientacion.HORIZONTAL) {
                casillas.add(new Casilla(new Coordenada(casillaCabeza.getCordenada().getX() + i, casillaCabeza.getCordenada().getY())));
            } else {
                casillas.add(new Casilla(new Coordenada(casillaCabeza.getCordenada().getX(), casillaCabeza.getCordenada().getY() + i)));
            }
        }
        return casillas;
    }
    
    private void cambiarTurno() {
        if (juego.getTurnoJugador().equals(juego.getJugadores().get(0))) {
            juego.setTurnoJugador(juego.getJugadores().get(1));
        } else {
            juego.setTurnoJugador(juego.getJugadores().get(0));
        }
    }
    
    // Obtener naves para un jugador específico
   public List<INave> obtenerNaves(Jugador jugador) {
        return barcoManager.obtenerNaves(jugador);
    }
    
   public Juego getEstadoDelJuego() {
        return juego; 
    }
    
}
