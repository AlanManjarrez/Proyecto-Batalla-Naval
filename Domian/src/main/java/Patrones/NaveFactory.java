/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Patrones;

import com.id.domian.Casilla;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class NaveFactory {

    public static final int MAX_PORTA_AVIONES = 2;
    public static final int MAX_CRUCEROS = 2;
    public static final int MAX_SUBMARINOS = 4;
    public static final int MAX_BARCOS = 3;

    public static int contadorPortaAviones = 0;
    public static int contadorCruceros = 0;
    public static int contadorSubmarinos = 0;
    public static int contadorBarcos = 0;

    public static INave crearNave(String tipo, Casilla casillaCabeza, Orientacion direccion) {

        if (tipo.equalsIgnoreCase("barco")) {
            if (contadorBarcos < MAX_BARCOS) {
                contadorBarcos++;
                return new Barco(casillaCabeza, direccion);
            } else {
                throw new IllegalStateException("No se pueden crear más Barcos");
            }
        } else if (tipo.equalsIgnoreCase("submarino")) {
            if (contadorSubmarinos < MAX_SUBMARINOS) {
                contadorSubmarinos++;
                return new Submarino(casillaCabeza, direccion);
            } else {
                throw new IllegalStateException("No se puede crear mas Submarinos");
            }
        } else if (tipo.equalsIgnoreCase("crucero")) {
            if (contadorCruceros < MAX_CRUCEROS) {
                contadorCruceros++;
                return new Crucero(casillaCabeza, direccion);
            } else {
                throw new IllegalStateException("No se puede crear mas Cruceros");
            }
        } else if (tipo.equalsIgnoreCase("porta aviones")) {
            if (contadorPortaAviones < MAX_PORTA_AVIONES) {
                contadorPortaAviones++;
                return new Portaviones(casillaCabeza, direccion);
            } else {
                throw new IllegalStateException("No se puede crear mas porta aviones");
            }
        } else {
            throw new IllegalArgumentException("No se conoce ese tipo de nave" + tipo);
        }

    }
}
