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
        int port = 50505;
        int maxClients = 3;
        
        JuegoManager juegoManager = JuegoManager.getInstance(); 
        
        Server server = new Server(port, maxClients, juegoManager);
        
        server.startServer();
    }
    
}
