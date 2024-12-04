/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import com.id.domian.Tablero;

/**
 *
 * @author JESUS
 */
public class TableroActualizado extends Event<Tablero>{
    
    public TableroActualizado(typeEvents type, Tablero payload){
        super(type, payload);
    }
    
    public TableroActualizado(){
        super(null,null);
    }
    
}
