/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.id.domian;

/**
 *
 * @author JESUS
 */
public class ConfiguracionNaves {
    private final int maxPortaAviones;
    private final int maxCruceros;
    private final int maxSubmarinos;
    private final int maxBarcos;

    public ConfiguracionNaves(int maxPortaAviones, int maxCruceros, int maxSubmarinos, int maxBarcos) {
        this.maxPortaAviones = maxPortaAviones;
        this.maxCruceros = maxCruceros;
        this.maxSubmarinos = maxSubmarinos;
        this.maxBarcos = maxBarcos;
    }

    public int getMaxPortaAviones() {
        return maxPortaAviones;
    }

    public int getMaxCruceros() {
        return maxCruceros;
    }

    public int getMaxSubmarinos() {
        return maxSubmarinos;
    }

    public int getMaxBarcos() {
        return maxBarcos;
    }

    // Configuración por defecto
    public static ConfiguracionNaves defaultConfig() {
        return new ConfiguracionNaves(2, 2, 4, 3);
    }
}
