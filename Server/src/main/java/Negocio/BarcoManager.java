/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Patrones.INave;
import Patrones.NaveFactory;
import Patrones.Orientacion;
import com.id.domian.ConfiguracionNaves;
import com.id.domian.Jugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author JESUS
 */
public class BarcoManager {

    private final Map<Jugador, JugadorNaves> navesPorJugador = new ConcurrentHashMap<>();

    // Configurar un jugador
    public void configurarJugador(Jugador jugador, ConfiguracionNaves configuracion) {
        navesPorJugador.put(jugador, new JugadorNaves(configuracion));
    }

    // Obtener naves para un jugador
    public List<INave> obtenerNaves(Jugador jugador) {
        JugadorNaves jugadorNaves = navesPorJugador.get(jugador);
        if (jugadorNaves == null) {
            throw new IllegalStateException("No se ha configurado el jugador: " + jugador.getNombre());
        }
        return jugadorNaves.generarNaves();
    }
}
    

