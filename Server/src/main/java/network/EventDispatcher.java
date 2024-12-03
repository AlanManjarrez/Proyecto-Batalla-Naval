/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import Negocio.JuegoManager;
import com.id.domian.Jugador;
import com.id.events.Event;
import com.id.events.FactoryEvent;
import com.id.events.typeEvents;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JESUS
 */
public class EventDispatcher implements ServerEventListener {
    
    private static final Logger LOG = Logger.getLogger(EventDispatcher.class.getName());
    private final Server server;
    
    private final JuegoManager juegoManager; 
    private final Map<String, Jugador> jugadores = new ConcurrentHashMap<>(); 

    public EventDispatcher(Server server,JuegoManager juegoManager) {
        this.server = server;
        this.juegoManager=juegoManager;
        
    }

    @Override
    public void onEventReceived(String clientId, Event event) {
        
        if (event == null) {
            LOG.log(Level.WARNING, "Evento recibido es null para el cliente " + clientId);
            return;
        }
        
       LOG.log(Level.INFO, "Procesando evento de tipo: " + event.getType());
        
        switch (event.getType()) {
            case JugadorConectado:
                Jugador jugador = (Jugador) event.getPayload();
                boolean jugadorUnido = juegoManager.unirJugadorSinCodigo(jugador); // Intentar unir al jugador al juego

                if (jugadorUnido) {
                    jugadores.put(clientId, jugador); // Asociar clientId con el Jugador solo si se une al juego
                    LOG.log(Level.INFO, "Jugador unido al juego: " + jugador.getNombre());
                    Event<?> eventoAceptado = FactoryEvent.createEvent(typeEvents.ConexionExitosa, jugador);
                    server.sendEventToClient(clientId, eventoAceptado);
                } else {
                    LOG.log(Level.INFO, "Jugador no pudo unirse al juego: " + jugador.getNombre());
                    Event<?> eventoRechazo = FactoryEvent.createEvent(typeEvents.PartidaLlena, "El juego ya está lleno.");
                    server.sendEventToClient(clientId, eventoRechazo); // Notificar al cliente
                }
                break;
            
            default:
                LOG.log(Level.WARNING, "Tipo de evento desconocido: " + event.getType());
                //out.println("Tipo de evento desconocido: " + event.getType());
                
        }
        
    }

    
    
    
    
    
    

}
