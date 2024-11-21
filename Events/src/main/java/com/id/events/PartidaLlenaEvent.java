/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import com.id.domian.Juego;
import java.io.Serializable;

/**
 *
 * @author JESUS
 */
public class PartidaLlenaEvent extends Event<String> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public PartidaLlenaEvent(typeEvents type, String payload) {
        super(type, payload);
    }
    
    public PartidaLlenaEvent(){
        super(null, null);
    }
    
}
