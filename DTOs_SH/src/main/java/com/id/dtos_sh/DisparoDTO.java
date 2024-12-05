/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.dtos_sh;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class DisparoDTO {

    private CasillaDTO casilla;
    private Boolean impacto;
    private NaveDTO nave;

    public DisparoDTO(CasillaDTO casilla) {
        this.casilla = casilla;
        this.impacto = false;
        this.nave = null;
    }
    
    public DisparoDTO(){
        
    }

    public CasillaDTO getCasilla() {
        return casilla;
    }

    public void setCasilla(CasillaDTO casilla) {
        this.casilla = casilla;
    }

    public Boolean getImpacto() {
        return impacto;
    }

    public void setImpacto(Boolean impacto) {
        this.impacto = impacto;
    }

    public NaveDTO getNave() {
        return nave;
    }

    public void setNave(NaveDTO nave) {
        this.nave = nave;
    }

}
