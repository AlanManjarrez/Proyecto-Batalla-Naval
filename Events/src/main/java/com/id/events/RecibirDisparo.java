/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;
import com.id.domian.Disparo;
/**
 *
 * @author JESUS
 */
public class RecibirDisparo extends Event<Disparo>{
    
    public RecibirDisparo(typeEvents type, Disparo payload){
        super(type,payload);
    }
    
    public RecibirDisparo(){
        super(null,null);
    }
}
