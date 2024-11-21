/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

import Patrones.INave;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Disparo {

    private Casilla casilla;
    private Boolean impacto;
    private INave naveImpactada;

    public Disparo(Casilla casilla) {
        this.casilla = casilla;
        this.impacto = false;
        this.naveImpactada = null;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Boolean getImpacto() {
        return impacto;
    }

    public void setImpacto(Boolean impacto) {
        this.impacto = impacto;
    }

    public INave getNaveImpactada() {
        return naveImpactada;
    }

    public void setNaveImpactada(INave naveImpactada) {
        this.naveImpactada = naveImpactada;
    }

}
