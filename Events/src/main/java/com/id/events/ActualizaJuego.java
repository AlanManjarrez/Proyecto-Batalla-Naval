/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.events;

import com.id.domian.Juego;

/**
 *
 * @author JESUS
 */
    public class ActualizaJuego extends Event<Juego>{

        public ActualizaJuego(typeEvents type,Juego payload){
            super(type,payload);
        }

        public ActualizaJuego(){
            super(null,null);
        }

    }
