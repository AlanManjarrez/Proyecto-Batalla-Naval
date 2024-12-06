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
    
    
   public Disparo procesarDisparo(Jugador jugador, Disparo disparo) {
    synchronized (juego) {
            Tablero tableroObjetivo;
            List<INave> navesObjetivo;

            // Determinar el tablero objetivo en función del jugador
            if (juego.getJugadores().get(0).equals(jugador)) {
                tableroObjetivo = juego.getJugador2TableroPrincipal();
                navesObjetivo = tableroObjetivo.getNaves();
            } else if (juego.getJugadores().get(1).equals(jugador)) {
                tableroObjetivo = juego.getJugador1TableroPrincipal();
                navesObjetivo = tableroObjetivo.getNaves();
            } else {
                LOG.log(Level.WARNING, "El jugador no está registrado.");
                disparo.setImpacto(false);
                disparo.setNaveImpactada(null);
                return disparo;
            }

           // Procesar el disparo
            System.out.println("Procesando disparo en coordenadas: X=" + disparo.getCasilla().getCordenada().getX() + 
                               " Y=" + disparo.getCasilla().getCordenada().getY());
            Casilla casillaObjetivo = tableroObjetivo.getCasilla()[disparo.getCasilla().getCordenada().getX()]
                                                        [disparo.getCasilla().getCordenada().getY()];

            if (casillaObjetivo.isEstado()) {
                System.out.println("La casilla ya fue atacada anteriormente.");
                disparo.setImpacto(false);
                disparo.setNaveImpactada(null);
                return disparo;
            }

            // Marcar la casilla como atacada
            casillaObjetivo.setEstado(true);
            System.out.println("La casilla se marcó como atacada.");

            // Verificar si impacta una nave
            boolean impacto = false;
            INave naveImpactada = null;
            for (INave nave : navesObjetivo) {
                if (verificarImpacto(nave, disparo.getCasilla())) {
                    impacto = true;
                    naveImpactada = nave;
                    System.out.println("¡Impacto en la nave! Tipo de nave: " + nave.getTipo());
                    actualizarEstadoNave(nave, tableroObjetivo);
                    System.out.println("Estado de la nave tras el impacto: " + 
                                       (nave.getEstado() == EstadoNave.HUNDIDO ? "Hundida" : "Averiada"));
                    break;
                }
            }

            // Cambiar el turno solo si no hubo impacto
            if (!impacto) {
                System.out.println("El disparo no impactó ninguna nave. Cambiando el turno al siguiente jugador.");
                cambiarTurno();
            } else {
                System.out.println("El turno no cambia porque el disparo impactó una nave.");
            }

            // Mensaje detallado
            if (impacto) {
                String estadoNave = naveImpactada.getEstado() == EstadoNave.HUNDIDO ? "hundida" : "averiada";
                System.out.println("¡Impacto! La nave "+naveImpactada.getTipo()+"Esta "+ naveImpactada.getEstado());
            } else {
                System.out.println("Disparo fallido");
            }

            // Actualizar el objeto disparo con el resultado
            disparo.setImpacto(impacto);
            disparo.setNaveImpactada(naveImpactada);
            
            return disparo;
        }
    }
    
    
    private boolean verificarImpacto(INave nave, Casilla disparo) {
        List<Casilla> casillasNave = calcularCasillasDeNave(nave);

        System.out.println("Verificando impacto en la nave: Tipo=" + nave.getTipo());
        System.out.println("Coordenadas del disparo: X=" + disparo.getCordenada().getX() + 
                           ", Y=" + disparo.getCordenada().getY());

        for (Casilla casilla : casillasNave) {
            System.out.println("Comparando con casilla de la nave: X=" + casilla.getCordenada().getX() +
                               ", Y=" + casilla.getCordenada().getY());
            if (casilla.getCordenada().getX() == disparo.getCordenada().getX() &&
                casilla.getCordenada().getY() == disparo.getCordenada().getY()) {
                System.out.println("Impacto confirmado.");
                return true;
            }
        }

        System.out.println("No hubo impacto.");
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
        System.out.println("Calculando casillas de la nave: Tipo=" + nave.getTipo() +
                           ", Dirección=" + nave.getDireccion() +
                           ", Longitud=" + nave.getLongitud());

        for (int i = 0; i < nave.getLongitud(); i++) {
            if (nave.getDireccion() == Orientacion.HORIZONTAL) {
                Casilla casilla = new Casilla(new Coordenada(
                    casillaCabeza.getCordenada().getX() + i, 
                    casillaCabeza.getCordenada().getY()));
                casillas.add(casilla);
                System.out.println("Casilla agregada: X=" + casilla.getCordenada().getX() +
                                   ", Y=" + casilla.getCordenada().getY());
            } else {
                Casilla casilla = new Casilla(new Coordenada(
                    casillaCabeza.getCordenada().getX(), 
                    casillaCabeza.getCordenada().getY() + i));
                casillas.add(casilla);
                System.out.println("Casilla agregada: X=" + casilla.getCordenada().getX() +
                                   ", Y=" + casilla.getCordenada().getY());
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
    
    public boolean jugadorPerdio(Jugador jugador) {
        Tablero tableroObjetivo;

        // Determinar el tablero correspondiente al jugador
        if (juego.getJugadores().get(0).equals(jugador)) {
            tableroObjetivo = juego.getJugador1TableroPrincipal();
        } else if (juego.getJugadores().get(1).equals(jugador)) {
            tableroObjetivo = juego.getJugador2TableroPrincipal();
        } else {
            System.out.println("El jugador no está registrado en la partida.");
            return false; // No se puede determinar si perdió si no está en la partida
        }

        // Verificar si todas las naves están hundidas
        for (INave nave : tableroObjetivo.getNaves()) {
            if (nave.getEstado() != EstadoNave.HUNDIDO) {
                System.out.println("El jugador aún tiene naves en pie: " + nave.getTipo() + " (Estado: " + nave.getEstado() + ")");
                return false; // Si alguna nave no está hundida, el jugador no ha perdido
            }
        }

        System.out.println("El jugador ya no tiene naves. Ha perdido.");
        return true; // Todas las naves están hundidas
    }
    
    
    // Obtener naves para un jugador específico
   public List<INave> obtenerNaves(Jugador jugador) {
        return barcoManager.obtenerNaves(jugador);
    }
    
   public Juego getEstadoDelJuego() {
        return juego; 
    }
    
}
