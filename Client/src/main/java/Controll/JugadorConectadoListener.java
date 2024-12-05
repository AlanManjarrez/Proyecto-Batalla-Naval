/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controll;

import com.id.dtos_sh.JuegoDTO;
import com.id.dtos_sh.NaveDTO;
import java.util.List;

/**
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public interface JugadorConectadoListener {

    void actualizarVista(boolean avanzar);
    void actualizarNaves(List<NaveDTO> naves);
    void iniciarPartida(JuegoDTO modelo,boolean jugador);
}
