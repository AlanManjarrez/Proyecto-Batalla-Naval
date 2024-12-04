/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controll;

import Network.Comunicacion;
import Patrones.INave;
import com.id.domian.Jugador;
import com.id.domian.Tablero;
import com.id.dtos_sh.EstadoNaveDTO;
import com.id.dtos_sh.JuegoDTO;
import com.id.dtos_sh.JugadorDTO;
import com.id.dtos_sh.NaveDTO;
import com.id.dtos_sh.OrientacionDTO;
import com.id.dtos_sh.TableroDTO;
import com.id.events.Event;
import com.id.events.FactoryEvent;
import com.id.events.typeEvents;
import com.id.utils.ConvertidorJugador;
import com.id.utils.ConvertidorNave;
import com.id.utils.ConvertidoTablero;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private static Controller instance;
    private JuegoDTO modelo;
    private Comunicacion comunicacion;
    private JugadorConectadoListener listener;

    private Controller() {
        comunicacion = new Comunicacion();
        modelo = new JuegoDTO();
        if (!comunicacion.connect()) {
            LOG.log(Level.SEVERE, "No se pudo conectar al servidor.");
           
        } else {
            LOG.log(Level.INFO, "Conexión establecida con el servidor.");
        }
    }

    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
            
        }
        return instance;
    }

    public void setJugadorConectadoListener(JugadorConectadoListener listener) {
        this.listener = listener;
    }


    public void manejarEvento(Event<?> evento) {
        LOG.log(Level.INFO, "Evento recibido en el controlador: {0}", evento.getType());

        Object payload = convertPayloadToDTO(evento.getPayload());

        if (evento.getType().equals(typeEvents.ConexionExitosa)) {
            if (payload instanceof JugadorDTO) {
                JugadorDTO jugadorDTO = (JugadorDTO) payload;

                if (modelo.getJugadores().size() < 2) {
                    modelo.getJugadores().add(jugadorDTO);
                    LOG.log(Level.INFO, "Jugador agregado. Total jugadores: {0}", modelo.getJugadores().size());
                }

                if (listener != null) {
                    listener.actualizarVista(true);
                }
            } else {
                LOG.log(Level.WARNING, "Payload no es una instancia de JugadorDTO: {0}", payload);
            }
        } else if (evento.getType().equals(typeEvents.PartidaLlena)) {
            if (listener != null) {
                listener.actualizarVista(false);
            }
        } else if (evento.getType().equals(typeEvents.solicitudNaves)) {
            if (payload instanceof List<?>) {
                List<?> navesDomain = (List<?>) payload;

                // Usa el convertidor para transformar la lista de INave a NaveDTO
                List<NaveDTO> navesDTO = (List<NaveDTO>) convertPayloadToDTO(navesDomain);

                LOG.log(Level.INFO, "Lista de naves convertida a DTO: {0}", navesDTO);

                if (listener != null) {
                    listener.actualizarNaves(navesDTO);
                }
            } else {
                LOG.log(Level.WARNING, "Payload no es una lista de objetos: {0}", payload);
            }
        } else if (evento.getType().equals(typeEvents.JugadorListo)) {
            JOptionPane.showMessageDialog(null, "Esperando al otro jugador");
        }

    }

   

   //  Metodos para solicitar
    
    public void jugadorConectado(String nombreJugador, String color) {
        JugadorDTO jugadorDTO = new JugadorDTO(nombreJugador, color);

        Object payload = convertPayloadToDomain(jugadorDTO);

        Event<?> evento = FactoryEvent.createEvent(typeEvents.JugadorConectado, payload);

        // Enviar el evento a través de la capa de comunicación
        comunicacion.sendEvent(evento);
    }
    
    public void solicitarNaves(){
       try {
            // Crear un evento vacío con el tipo "solicitudNaves"
            Event<?> evento = FactoryEvent.createEvent(typeEvents.solicitudNaves, null);

            // Enviar el evento al servidor a través de la comunicación
            comunicacion.sendEvent(evento);

            LOG.log(Level.INFO, "Evento solicitudNaves enviado al servidor.");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al solicitar naves.", e);
        }
        
    }
    
    public void jugadorListo(TableroDTO tablero){
        if (tablero == null) {
            LOG.log(Level.SEVERE, "El tablero es nulo. No se puede enviar.");
            return;
        }
        try {
            Object payload = convertPayloadToDomain(tablero);
            Event<?> event= FactoryEvent.createEvent(typeEvents.ActualizarTablero, payload);
            comunicacion.sendEvent(event);
            LOG.log(Level.INFO, "Se ha enviado el tablero correctamente");
        } catch (Exception e) {
            LOG.log(Level.WARNING,"No se ha podido enviar el tablero",e);
        }
    }
    
    
    
    public void actualizarTablero(TableroDTO tablero) {
        if (modelo.getJugadores().size() == 1) {
            // Si es el jugador 1
            modelo.setJugador1TableroPrincipal(tablero);
            LOG.log(Level.INFO, "Tablero y naves actualizados para el Jugador 1.");
        } else if (modelo.getJugadores().size() == 2) {
            // Si es el jugador 2
            modelo.setJugador2TableroPrincipal(tablero);
            LOG.log(Level.INFO, "Tablero y naves actualizados para el Jugador 2.");
        } else {
            LOG.log(Level.WARNING, "No se pudo asociar el tablero. Número de jugadores no válido.");
        }
    }
    
    
    
     private Object convertPayloadToDomain(Object payload) {
        if (payload instanceof JugadorDTO) {
            return ConvertidorJugador.toEntity((JugadorDTO) payload);
        } else if (payload instanceof TableroDTO) {
            return ConvertidoTablero.toEntity((TableroDTO)payload);
        } else if (payload instanceof NaveDTO) {
            return ConvertidorNave.toEntity((NaveDTO) payload);
        } else if (payload instanceof List<?>) {
            // Si es una lista de NaveDTO, conviértela a lista de INave
            List<?> lista = (List<?>) payload;
            if (!lista.isEmpty() && lista.get(0) instanceof NaveDTO) {
                return ConvertidorNave.toEntityList((List<NaveDTO>) lista);
            }
        }
        return payload; // Si no requiere conversión
    }
    
    private Object convertPayloadToDTO(Object payload) {
        if (payload instanceof Jugador) {
            return ConvertidorJugador.toDTO((Jugador) payload);
        } else if (payload instanceof INave) {
            return ConvertidorNave.toDTO((INave) payload);
        } else if (payload instanceof List<?>) {
            // Si es una lista de INave, conviértela a lista de NaveDTO
            List<?> lista = (List<?>) payload;
            if (!lista.isEmpty() && lista.get(0) instanceof INave) {
                return ConvertidorNave.toDTOList((List<INave>) lista);
            }
        }else if (payload instanceof Tablero) {
            return ConvertidoTablero.toDTO((Tablero)payload);
        }
        return payload; // Si no requiere conversión
    }
}
