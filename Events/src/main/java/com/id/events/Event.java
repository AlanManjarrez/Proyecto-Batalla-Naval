/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import java.io.Serializable;

/**
 *
 * @author JESUS
 */
public abstract class Event<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private typeEvents type;
    private T payload;

    public Event(typeEvents type, T payload) {
        this.type = type;
        this.payload = payload;
    }
    
    public Event(){
        
    }

    public typeEvents getType() {
        return type;
    }

    public void setType(typeEvents type) {
        this.type = type;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
    
    
}
