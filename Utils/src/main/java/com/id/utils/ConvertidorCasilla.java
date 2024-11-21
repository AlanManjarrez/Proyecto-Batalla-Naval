/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.domian.Casilla;
import com.id.domian.Coordenada;
import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.CoordenadaDTO;

/**
 *
 * @author JESUS
 */
public class ConvertidorCasilla {
    
    public static CasillaDTO toDTO(Casilla casilla){
        if (casilla == null) {
            return null;
        }
        return new CasillaDTO(toDTOC(casilla.getCordenada()));
    }
    
    public static Casilla toEntity(CasillaDTO casilla){
        if (casilla == null) {
            return null;
        }
        return new Casilla(toEntityC(casilla.getCoordenada()));
    }
    
    public static CoordenadaDTO toDTOC(Coordenada coordenada){
        if (coordenada == null) {
            return null;
        }
        return new CoordenadaDTO(coordenada.getX(), coordenada.getY());
    }
    
    public static Coordenada toEntityC(CoordenadaDTO coordenada){
        if (coordenada == null) {
            return null;
        }
        return new Coordenada(coordenada.getX(), coordenada.getY());
    }
}
