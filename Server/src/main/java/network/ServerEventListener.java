/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import java.io.PrintWriter;
import com.id.events.Event;
import java.io.ObjectOutputStream;
/**
 *
 * @author JESUS
 */
public interface ServerEventListener {
    void onEventReceived(String clientId,Event event);
 
}
