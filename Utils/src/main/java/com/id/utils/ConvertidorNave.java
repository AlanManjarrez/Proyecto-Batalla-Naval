/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import Patrones.EstadoNave;
import Patrones.INave;
import Patrones.Orientacion;
import com.id.dtos_sh.EstadoNaveDTO;
import com.id.dtos_sh.NaveDTO;
import com.id.dtos_sh.OrientacionDTO;

/**
 *
 * @author JESUS
 */
public class ConvertidorNave {
    
    public static NaveDTO toDTO(INave nave) {
        if (nave == null) {
            return null;
        }
        return new NaveDTO(
            nave.getLongitud(),
            EstadoNaveDTO.valueOf(nave.getEstado().name()),
            ConvertidorCasilla.toDTO(nave.getCasillaCabeza()),
            OrientacionDTO.valueOf(nave.getDireccion().name()),
            nave.getTipo()
        );
    }
    
    public static INave toEntity(NaveDTO nave){
        if (nave == null) {
            return null;
        }
        
        INave naved=null;
        naved.setLongitud(nave.getLongitud());
        naved.setCasillaCabeza(ConvertidorCasilla.toEntity(nave.getCasilla()));
        naved.setEstado(EstadoNave.valueOf(nave.getEstado().name()));
        naved.setDireccion(Orientacion.valueOf(nave.getDireccion().name()));
        
        return naved;
    }
}
