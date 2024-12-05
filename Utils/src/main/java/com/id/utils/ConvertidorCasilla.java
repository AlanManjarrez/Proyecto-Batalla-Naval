/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.domian.Casilla;
import com.id.domian.Coordenada;
import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.CoordenadaDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JESUS
 */
public class ConvertidorCasilla {


    public static CasillaDTO toDTO(Casilla casilla) {
        if (casilla == null) {
            return null;
        }


        CasillaDTO casillaDTO = new CasillaDTO(toDTOC(casilla.getCordenada()));
        casillaDTO.setEstado(casilla.isEstado());

        return casillaDTO;
    }

    public static Casilla toEntity(CasillaDTO casillaDTO) {
        if (casillaDTO == null) {
            return null;
        }
        Casilla casilla = new Casilla(toEntityC(casillaDTO.getCoordenada()));
        casilla.setEstado(casillaDTO.isEstado()); 
        return casilla;
    }

    public static CoordenadaDTO toDTOC(Coordenada coordenada) {
        if (coordenada == null) {
            return null;
        }
        return new CoordenadaDTO(coordenada.getX(), coordenada.getY());
    }

    public static Coordenada toEntityC(CoordenadaDTO coordenadaDTO) {
        if (coordenadaDTO == null) {
            return null;
        }
        return new Coordenada(coordenadaDTO.getX(), coordenadaDTO.getY());
    }
}
