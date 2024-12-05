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
public class DisparoRealizado extends Event<Disparo>{
    
    public DisparoRealizado(typeEvents event,Disparo payload){
        super(event,payload);
    }
    
    public DisparoRealizado(){
       super(); 
    }
    
}
