/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

/**
 *
 * @author JESUS
 */
public class JugadorListo extends Event<String>{
    
    public JugadorListo(typeEvents type, String payload){
        super(type,payload);
    }
    
    public JugadorListo(){
        super();
    }
    
}
