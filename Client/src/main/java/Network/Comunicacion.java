/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network;

import Controll.Controller;
import com.id.domian.Jugador;
import com.id.utils.ConvertidorJugador;
import com.id.dtos_sh.JugadorDTO;
import com.id.dtos_sh.TableroDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.id.events.Event;
import com.id.events.FactoryEvent;

/**
 * 
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Comunicacion {

    private static final Logger LOG = Logger.getLogger(Comunicacion.class.getName());
    private Socket socket;
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 50505;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private volatile boolean connected;

    public boolean connect() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            connected = true;
            LOG.log(Level.INFO, "Conectado al servidor en {0}:{1}", new Object[]{SERVER_ADDRESS, SERVER_PORT});

            new Thread(this::listenForEvents).start();

            return true;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al conectar con el servidor.", e);
            return false;
        }
    }

    public void sendEvent(Event<?> event) {
        try {
            if (connected) {
                Object payload = event.getPayload();

                Object convertedPayload = convertPayloadToDomain(payload);

                Event<?> domainEvent = FactoryEvent.createEvent(event.getType(), convertedPayload);
                System.out.println(convertedPayload);
                out.writeObject(domainEvent);
                out.flush();
                LOG.log(Level.INFO, "Evento enviado: {0}", event.getType());
            } else {
                LOG.log(Level.WARNING, "No se puede enviar el evento. Cliente no conectado.");
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al enviar evento al servidor.", e);
        }
    }

    private void listenForEvents() {
        try {
            while (connected) {
                Event<?> event = (Event<?>) in.readObject(); // Leer evento del servidor
                LOG.log(Level.INFO, "Evento recibido: {0}", event.getType());

                // Convertir el evento si es necesario antes de enviarlo al Controller
                Object payload = event.getPayload();
                
                // Crear un nuevo evento con el payload convertido
                event = FactoryEvent.createEvent(event.getType(), event.getPayload());
                
                Controller.getInstance().manejarEvento(event);
            }
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Error al escuchar eventos del servidor.", e);
            disconnect();
        }
    }

    public void disconnect() {
        connected = false;
        try {
            if (socket != null) {
                socket.close();
            }
            LOG.log(Level.INFO, "Conexión cerrada.");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error al cerrar la conexión.", e);
        }
    }

    private Object convertPayloadToDomain(Object payload) {
        if (payload instanceof JugadorDTO) {
            return ConvertidorJugador.toEntity((JugadorDTO) payload);
        } else if (payload instanceof TableroDTO) {
            return null;
        }
        return payload;
    }

    private Object convertPayload(Object payload) {
        if (payload instanceof Jugador) {
            return ConvertidorJugador.toDTO((Jugador) payload);
        }

        return payload;
    }
}
