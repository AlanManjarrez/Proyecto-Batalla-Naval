/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import Patrones.Barco;
import Patrones.Crucero;
import Patrones.EstadoNave;
import Patrones.INave;
import Patrones.Orientacion;
import Patrones.Portaviones;
import Patrones.Submarino;
import com.id.dtos_sh.EstadoNaveDTO;
import com.id.dtos_sh.NaveDTO;
import com.id.dtos_sh.OrientacionDTO;
import java.util.ArrayList;
import java.util.List;

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
        
        INave naved;
         switch (nave.getTipo().toLowerCase()) {
            case "porta aviones":
                naved = new Portaviones();
                break;
            case "crucero":
                naved = new Crucero();
                break;
            case "submarino":
                naved = new Submarino();
                break;
            case "barco":
                naved = new Barco();
                break;
            default:
                throw new IllegalArgumentException("Tipo de nave desconocido: " + nave.getTipo());
        }
        
        
        naved.setLongitud(nave.getLongitud());
        naved.setCasillaCabeza(ConvertidorCasilla.toEntity(nave.getCasilla()));
        naved.setEstado(EstadoNave.valueOf(nave.getEstado().name()));
        naved.setDireccion(Orientacion.valueOf(nave.getDireccion().name()));
        
        return naved;
    }
    
    public static List<NaveDTO> toDTOList(List<INave> naves) {
        if (naves == null || naves.isEmpty()) {
            return new ArrayList<>();
        }

        List<NaveDTO> navesDTO = new ArrayList<>();
        for (INave nave : naves) {
            NaveDTO dto = toDTO(nave); // Usa el método toDTO de NaveConverter
            navesDTO.add(dto);
        }

        return navesDTO;
    }

    // Convertir una lista de NaveDTO a INave
    public static List<INave> toEntityList(List<NaveDTO> navesDTO) {
        if (navesDTO == null || navesDTO.isEmpty()) {
            return new ArrayList<>();
        }

        List<INave> naves = new ArrayList<>();
        for (NaveDTO dto : navesDTO) {
            INave nave = toEntity(dto); // Usa el método toEntity de NaveConverter
            naves.add(nave);
        }

        return naves;
    }
    
    
}
