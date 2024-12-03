/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controll;

import Network.Comunicacion;
import UIs.frmInstruccion;
import UIs.frmTablero;
import com.id.dtos_sh.JugadorDTO;
import com.id.dtos_sh.TableroDTO;
import com.id.events.Event;
import com.id.events.FactoryEvent;
import com.id.events.typeEvents;
import com.id.utils.ConvertidorJugador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private static Controller instance;
    private Comunicacion comunicacion;
    private JugadorConectadoListener listener;

    private Controller() {
        comunicacion = new Comunicacion();
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

    public void jugadorConectado(String nombreJugador, String color) {
        JugadorDTO jugadorDTO = new JugadorDTO(nombreJugador, color);

        Object payload = convertPayloadToDomain(jugadorDTO);

        Event<?> evento = FactoryEvent.createEvent(typeEvents.JugadorConectado, payload);

        // Enviar el evento a través de la capa de comunicación
        comunicacion.sendEvent(evento);
    }

    public void manejarEvento(Event<?> evento) {
        LOG.log(Level.INFO, "Evento recibido en el controlador: {0}", evento.getType());

        if (evento.getType().equals(typeEvents.JugadorConectado)) {
            if (listener != null) {
                listener.actualizarVista(true);
            }
        } else if (evento.getType().equals(typeEvents.PartidaLlena)) {
            if (listener != null) {
                listener.actualizarVista(false);
            }
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
}
