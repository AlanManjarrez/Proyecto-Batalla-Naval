/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import com.id.events.Event;
import java.io.EOFException;
import java.util.logging.Level;

/**
 *
 * @author JESUS
 */
public class ClientHandler implements Runnable{
    private static final Logger LOG = Logger.getLogger(ClientHandler.class.getName());
    private final Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final ServerEventListener eventListener;
    private final String clientId;

    public ClientHandler(Socket clientSocket, ServerEventListener eventListener) throws IOException {
        this.clientSocket = clientSocket;
        this.eventListener = eventListener;
        this.clientId = clientSocket.getRemoteSocketAddress().toString();
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush(); // Es importante llamar a flush inmediatamente después de crear el ObjectOutputStream
            in = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                try {
                    Event<?> event = (Event<?>) in.readObject(); 
                    eventListener.onEventReceived(clientId, event);
                } catch (EOFException e) {
                    LOG.log(Level.WARNING, "Cliente " + clientId + " se desconectó abruptamente");
                    break; // Salir del bucle si se detecta EOF
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Error al comunicar con el cliente " + clientId, e);
        } finally {
            closeConnections();
            LOG.log(Level.INFO, "Cliente desconectado: " + clientId);
        }
    }

    public void sendEvent(Event<?> event) {
        try {
            out.writeObject(event);
            out.flush();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al enviar evento al cliente " + clientId, e);
        }
    }

    private void closeConnections() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al cerrar conexión del cliente " + clientId, e);
        }
    }
}
