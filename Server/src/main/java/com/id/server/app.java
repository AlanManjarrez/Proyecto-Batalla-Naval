/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.id.server;

import Negocio.JuegoManager;
import network.Server;

/**
 *
 * @author JESUS
 */
public class app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 5000;
        int maxClients = 3;
        
        // Inicializa el JuegoManager (esto podría ser una clase que gestiona los jugadores y las partidas)
        JuegoManager juegoManager = JuegoManager.getInstance();  // Obtener la instancia del singleton
        
        // Crea el servidor
        Server server = new Server(port, maxClients, juegoManager);
        
        // Inicia el servidor
        server.startServer();
    }
    
}
