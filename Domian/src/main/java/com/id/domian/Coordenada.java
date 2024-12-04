/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import java.io.Serializable;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Coordenada implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
