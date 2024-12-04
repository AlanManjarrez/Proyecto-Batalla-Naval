/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Patrones.INave;
import Patrones.NaveFactory;
import Patrones.Orientacion;
import com.id.domian.ConfiguracionNaves;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JESUS
 */
public class JugadorNaves {
     private final ConfiguracionNaves configuracion;
    private int contadorPortaAviones = 0;
    private int contadorCruceros = 0;
    private int contadorSubmarinos = 0;
    private int contadorBarcos = 0;

    public JugadorNaves(ConfiguracionNaves configuracion) {
        this.configuracion = configuracion;
    }

    public List<INave> generarNaves() {
        List<INave> naves = new ArrayList<>();

        try {
            // Generar Porta Aviones
            for (int i = 0; i < configuracion.getMaxPortaAviones(); i++) {
                if (contadorPortaAviones >= configuracion.getMaxPortaAviones()) {
                    throw new IllegalStateException("No se pueden crear más Porta Aviones");
                }
                contadorPortaAviones++;
                naves.add(NaveFactory.crearNave("porta aviones", null, Orientacion.HORIZONTAL));
            }

            // Generar Cruceros
            for (int i = 0; i < configuracion.getMaxCruceros(); i++) {
                if (contadorCruceros >= configuracion.getMaxCruceros()) {
                    throw new IllegalStateException("No se pueden crear más Cruceros");
                }
                contadorCruceros++;
                naves.add(NaveFactory.crearNave("crucero", null, Orientacion.HORIZONTAL));
            }

            // Generar Submarinos
            for (int i = 0; i < configuracion.getMaxSubmarinos(); i++) {
                if (contadorSubmarinos >= configuracion.getMaxSubmarinos()) {
                    throw new IllegalStateException("No se pueden crear más Submarinos");
                }
                contadorSubmarinos++;
                naves.add(NaveFactory.crearNave("submarino", null, Orientacion.HORIZONTAL));
            }

            // Generar Barcos
            for (int i = 0; i < configuracion.getMaxBarcos(); i++) {
                if (contadorBarcos >= configuracion.getMaxBarcos()) {
                    throw new IllegalStateException("No se pueden crear más Barcos");
                }
                contadorBarcos++;
                naves.add(NaveFactory.crearNave("barco", null, Orientacion.HORIZONTAL));
            }
        } catch (IllegalStateException e) {
            System.err.println("Error al generar naves: " + e.getMessage());
        }

        return naves;
    }
}
