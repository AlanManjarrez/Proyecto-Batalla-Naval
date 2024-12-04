/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import Negocio.JuegoManager;
import Patrones.INave;
import com.id.domian.ConfiguracionNaves;
import com.id.domian.Juego;
import com.id.domian.Jugador;
import com.id.domian.Tablero;
import com.id.events.Event;
import com.id.events.FactoryEvent;
import com.id.events.typeEvents;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;
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
                boolean jugadorUnido = juegoManager.unirJugadorSinCodigo(jugador);

                if (jugadorUnido) {
                    jugadores.put(clientId, jugador); // Asociar el clientId con el objeto Jugador
                    LOG.log(Level.INFO, "Jugador unido al juego: " + jugador.getNombre());

                    Event<?> eventoAceptado = FactoryEvent.createEvent(typeEvents.ConexionExitosa, jugador);
                    server.sendEventToClient(clientId, eventoAceptado);
                } else {
                    Event<?> eventoRechazo = FactoryEvent.createEvent(typeEvents.PartidaLlena, "El juego ya está lleno.");
                    server.sendEventToClient(clientId, eventoRechazo);
                }
                break;

            case solicitudNaves:
                LOG.log(Level.INFO, "Solicitud de naves recibida del cliente: " + clientId);
                Jugador jugadorAsociado = jugadores.get(clientId); // Obtener el jugador asociado
                if (jugadorAsociado != null) {
                    List<INave> naves = juegoManager.obtenerNaves(jugadorAsociado); // Usar el objeto Jugador directamente
                    if (!naves.isEmpty()) {
                        Event<?> eventoEnviado = FactoryEvent.createEvent(typeEvents.solicitudNaves, naves);
                        server.sendEventToClient(clientId, eventoEnviado);
                        LOG.log(Level.INFO, "Lista de naves enviada al cliente: " + clientId);
                    } else {
                        LOG.log(Level.WARNING, "No se pudieron obtener naves para el cliente: " + clientId);
                    }
                } else {
                    LOG.log(Level.WARNING, "Cliente no tiene un jugador asociado: " + clientId);
                }
                break;
            case ActualizarTablero:
                LOG.log(Level.INFO,"Actualizacion de tablero recibida de :"+clientId);
                Tablero tableroRecibido= (Tablero) event.getPayload();
                Jugador jugadorAsociados = jugadores.get(clientId);
                
                if (tableroRecibido != null && jugadorAsociados != null) {
                    LOG.log(Level.INFO, "Recibido tablero del cliente: " + jugadorAsociados.getNombre());

                    // Configurar el tablero en el modelo del servidor
                    juegoManager.configurarTablero(jugadorAsociados, tableroRecibido);

                    // Verificar si ambos tableros están configurados
                    if (juegoManager.ambosTablerosConfigurados()) {
                        LOG.log(Level.INFO, "Ambos tableros están configurados. Notificando a los jugadores.");
                        
                        Juego estadoDelJuego = juegoManager.getEstadoDelJuego();
                        
                        // Enviar notificación a todos los jugadores
                        jugadores.forEach((clienteId, jugadorAsociadoss) -> {
                            Event<?> eventoInicio = FactoryEvent.createEvent(typeEvents.IniciarPartida,estadoDelJuego);
                            server.sendEventToClient(clienteId, eventoInicio);
                        });
                    } else {
                        // Enviar confirmación de tablero recibido solo al cliente actual
                        Event<?> confirmacion = FactoryEvent.createEvent(typeEvents.JugadorListo, "Tablero recibido correctamente. Esperando al otro jugador.");
                        server.sendEventToClient(clientId, confirmacion);
                    }
                } else {
                    LOG.log(Level.WARNING, "El tablero recibido está vacío o el jugador no está asociado.");
                }
                break;
                
                
            default:
                LOG.log(Level.WARNING, "Tipo de evento desconocido: " + event.getType());
        }
    }        
}

    
    
    
    
    
   
