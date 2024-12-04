/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;
import Patrones.INave;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author JESUS
 */
public class SolicitudNaves extends Event<List<INave>> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public SolicitudNaves(typeEvents type, List<INave> payload){
        super(type,payload);
    }
    
    public SolicitudNaves(){
        super(null,null);
    }
    
}
