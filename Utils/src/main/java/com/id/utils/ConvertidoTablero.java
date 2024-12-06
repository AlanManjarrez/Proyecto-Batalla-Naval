/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.domian.Casilla;
import com.id.domian.Tablero;
import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.TableroDTO;

/**
 *
 * @author JESUS
 */
public class ConvertidoTablero {
    public static TableroDTO toDTO(Tablero tablero) {
        if (tablero == null) {
            return null;
        }

        TableroDTO tableroDTO = new TableroDTO();
        tableroDTO.setTamaño(tablero.getTamaño());        
        tableroDTO.setCasillas(convertirCasillasATableroDTO(tablero.getCasilla()));
        tableroDTO.setNaves(ConvertidorNave.toDTOList(tablero.getNaves()));
        tableroDTO.setDisparos(ConvertidorDisparos.toDTOList(tablero.getDisparos()));

        return tableroDTO;
    }

    // Convertir TableroDTO a Tablero
    public static Tablero toEntity(TableroDTO tableroDTO) {
        if (tableroDTO == null) {
            return null;
        }

        Tablero tablero = new Tablero();
        tablero.setTamaño(tableroDTO.getTamaño());
        tablero.setCasilla(convertirCasillasATableroEntity(tableroDTO.getCasillas()));
        tablero.setNaves(ConvertidorNave.toEntityList(tableroDTO.getNaves()));
        tablero.setDisparos(ConvertidorDisparos.toEntityList(tableroDTO.getDisparos()));

        return tablero;
    }

    // Métodos auxiliares para convertir las casillas del tablero
    private static CasillaDTO[][] convertirCasillasATableroDTO(Casilla[][] casillas) {
        if (casillas == null) {
           // System.out.println("Advertencia: Matriz de casillas nula.");
            return null;
        }

        int size = casillas.length;
        CasillaDTO[][] casillasDTO = new CasillaDTO[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (casillas[i][j] == null) {
                   // System.out.println("Advertencia: Casilla nula en posición [" + i + "][" + j + "]");
                    casillasDTO[i][j] = null;
                } else {
                    //System.out.println("Convirtiendo casilla [" + i + "][" + j + "] con estado: " + casillas[i][j].isEstado());
                    casillasDTO[i][j] = ConvertidorCasilla.toDTO(casillas[i][j]);
                }
            }
        }

        return casillasDTO;
    }

    private static Casilla[][] convertirCasillasATableroEntity(CasillaDTO[][] casillasDTO) {
        if (casillasDTO == null) {
            return null;
        }

        int size = casillasDTO.length;
        Casilla[][] casillas = new Casilla[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                casillas[i][j] = ConvertidorCasilla.toEntity(casillasDTO[i][j]);
            }
        }
        return casillas;
    }
}
