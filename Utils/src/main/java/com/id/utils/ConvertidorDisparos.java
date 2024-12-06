/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.utils;

import com.id.domian.Disparo;
import com.id.dtos_sh.DisparoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JESUS
 */
public class ConvertidorDisparos {
    // Convertir Disparo a DisparoDTO
    public static DisparoDTO toDTO(Disparo disparo) {
        if (disparo == null) {
            return null;
        }

        DisparoDTO Disparo=new DisparoDTO();
        Disparo.setCasilla(ConvertidorCasilla.toDTO(disparo.getCasilla()));
        Disparo.setImpacto(disparo.getImpacto());
        Disparo.setNave(ConvertidorNave.toDTO(disparo.getNaveImpactada()));
        return Disparo;
    }

    // Convertir DisparoDTO a Disparo
    public static Disparo toEntity(DisparoDTO disparoDTO) {
        if (disparoDTO == null) {
            return null;
        }

        Disparo disparo = new Disparo(ConvertidorCasilla.toEntity(disparoDTO.getCasilla()));
        disparo.setImpacto(disparoDTO.getImpacto());
        disparo.setNaveImpactada(ConvertidorNave.toEntity(disparoDTO.getNave()));
        return disparo;
    }

    // Convertir lista de Disparo a lista de DisparoDTO
    public static List<DisparoDTO> toDTOList(List<Disparo> disparos) {
        if (disparos == null || disparos.isEmpty()) {
            return new ArrayList<>();
        }

        List<DisparoDTO> disparosDTO = new ArrayList<>();
        for (Disparo disparo : disparos) {
            disparosDTO.add(toDTO(disparo));
        }
        return disparosDTO;
    }

    // Convertir lista de DisparoDTO a lista de Disparo
    public static List<Disparo> toEntityList(List<DisparoDTO> disparosDTO) {
        if (disparosDTO == null || disparosDTO.isEmpty()) {
            return new ArrayList<>();
        }

        List<Disparo> disparos = new ArrayList<>();
        for (DisparoDTO disparoDTO : disparosDTO) {
            disparos.add(toEntity(disparoDTO));
        }
        return disparos;
    }
}
