/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import Negocio.JuegoManager;
import com.id.events.Event;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author JESUS
 */
public class Server {
    private final int port;
    private final int maxClients;
    private static final Logger LOG = Logger.getLogger(Server.class.getName());
    private final ExecutorService executor; 
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private final EventDispatcher eventDispatcher;

    public Server(final int PORT, final int MAX_CLIENTS, JuegoManager juegoManager) {
        this.port = PORT;
        this.maxClients = MAX_CLIENTS;
        this.executor = Executors.newFixedThreadPool(maxClients);
        this.eventDispatcher = new EventDispatcher(this,juegoManager);
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOG.log(Level.INFO, "Server iniciado. Listening for clients on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, eventDispatcher);
                clients.put(clientHandler.getClientId(), clientHandler);

                executor.submit(clientHandler);
                LOG.log(Level.INFO, "Nuevo cliente conectado: " + clientHandler.getClientId());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al iniciar el servidor", e);
        } finally {
            LOG.log(Level.INFO, "Server stopped");
            executor.shutdown();
        }
    }

    public void sendEventToClient(String clientId, Event<?> event) {
        ClientHandler clientHandler = clients.get(clientId);
        if (clientHandler != null) {
            LOG.log(Level.INFO, "Enviando evento {0} al cliente {1}", new Object[]{event.getType(), clientId});
            clientHandler.sendEvent(event);
        } else {
            LOG.log(Level.WARNING, "No se encontró el cliente con ID: " + clientId);
        }
    }

    public Map<String, ClientHandler> getClients() {
        return clients;
    }
    
}
