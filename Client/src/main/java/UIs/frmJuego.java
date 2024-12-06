/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UIs;

import Controll.Controller;
import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.CoordenadaDTO;
import com.id.dtos_sh.DisparoDTO;
import com.id.dtos_sh.JuegoDTO;
import com.id.dtos_sh.JugadorDTO;
import com.id.dtos_sh.Observer;
import com.id.dtos_sh.TableroDTO;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JESUS
 */
public class frmJuego extends javax.swing.JFrame implements Observer{
    Controller conto;
    private static final Logger LOG = Logger.getLogger(frmJuego.class.getName());
    JuegoDTO juego;
    boolean esJugador1;
    private TableroRedender tableroPropio; // Panel para el tablero del jugador
    private TableroSelector tableroDisparo;
    
    /**
     * Creates new form frmJuego
     */
    public frmJuego(Controller conto,boolean jugador) {
        this.conto=conto;
        this.juego=JuegoDTO.getInstance();;
        this.esJugador1=jugador;
        System.out.println(esJugador1);
        
        this.juego.addObserver(this);
        LOG.log(Level.INFO, "Observador registrado para el juego.");
        
        initComponents();
        
        inicializarTableros();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnAtacar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        btnAtacar.setText("Atacar");
        btnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tableros");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(480, 480, 480))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(btnAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        

        Point casillaSeleccionada = tableroDisparo.getCasillaSeleccionada();

        if (casillaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una casilla antes de atacar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear la CasillaDTO a partir de la selección
        CoordenadaDTO coordenada = new CoordenadaDTO(casillaSeleccionada.x, casillaSeleccionada.y);
        System.out.println("Coordenadas enviadas al controlador: X (Columna)=" + coordenada.getX() + ", Y (Fila)=" + coordenada.getY());

        CasillaDTO casilla = new CasillaDTO(coordenada);
        DisparoDTO disparo = new DisparoDTO(casilla);

        // Realizar el ataque utilizando el Controller
        conto.realizarAtaque(disparo);

        // Reiniciar la selección en el tablero de disparos
        tableroDisparo.clearSeleccion();


        // Notificar al usuario que el disparo fue enviado
      //  JOptionPane.showMessageDialog(this, "Disparo enviado correctamente. Turno del siguiente jugador.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAtacarActionPerformed
    private void inicializarTableros() {
        // Tablero del jugador actual
        TableroDTO tableroPrincipal = esJugador1 ? juego.getJugador1TableroPrincipal() : juego.getJugador2TableroPrincipal();
        String colorJugador = esJugador1 ? juego.getJugadores().get(0).getColor() : juego.getJugadores().get(1).getColor();

        if (tableroPrincipal == null) {
            System.err.println("Tablero principal es nulo para " + (esJugador1 ? "Jugador 1" : "Jugador 2"));
            return;
        }
        
        tableroPropio = new TableroRedender(tableroPrincipal, colorJugador,esJugador1);

        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(tableroPropio, BorderLayout.CENTER);

        // Tablero para seleccionar disparos
        TableroDTO tableroEnemigo = esJugador1 ? juego.getJugador2TableroPrincipal() : juego.getJugador1TableroPrincipal();

        if (tableroEnemigo == null) {
            System.err.println("Tablero enemigo es nulo");
            return;
        }
        System.out.println(tableroPrincipal.getNaves().size());
        tableroEnemigo.setNaves(tableroPrincipal.getNaves());
        tableroDisparo = new TableroSelector(tableroEnemigo,colorJugador,esJugador1,this);

        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(tableroDisparo, BorderLayout.CENTER);

        // Redibujar ambos paneles
        jPanel2.revalidate();
        jPanel2.repaint();
        jPanel3.revalidate();
        jPanel3.repaint();
    }
    public void mensaje(boolean si){
        if (si) {
            JOptionPane.showMessageDialog(this, "Jugador Ha recibido daño a una nave");
        }else{
            JOptionPane.showMessageDialog(this, "Tiro Fallado");
        }
    }
    public void fin(){
        JOptionPane.showMessageDialog(this, "el Jugador Ha perdido");
        this.dispose();
        frmTerminarPartida f = new frmTerminarPartida();
        f.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtacar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object Modelo) {
           if (Modelo instanceof JuegoDTO) {
                this.juego = (JuegoDTO) Modelo;

                TableroDTO tableroPrincipal = esJugador1 ? juego.getJugador1TableroPrincipal() : juego.getJugador2TableroPrincipal();
                TableroDTO tableroEnemigo = esJugador1 ? juego.getJugador2TableroPrincipal() : juego.getJugador1TableroPrincipal();

                // Actualizar el tablero propio si existe
                if (tableroPrincipal != null) {
                    tableroPropio.setTableroDTO(tableroPrincipal); // Método para actualizar datos internos del panel
                    jPanel2.revalidate();
                    jPanel2.repaint();
                }

                // Actualizar el tablero enemigo si existe
                if (tableroEnemigo != null) {
                    tableroDisparo.setTableroDTO(tableroEnemigo); // Método para actualizar datos internos del panel
                    jPanel3.revalidate();
                    jPanel3.repaint();
                }

                // Habilitar o deshabilitar el botón según el turno
                boolean esMiTurno = esJugador1
                        ? juego.getJugadorTurno().equals(juego.getJugadores().get(0))
                                : juego.getJugadorTurno().equals(juego.getJugadores().get(1));

                btnAtacar.setEnabled(esMiTurno);
           }
    }
    
    public Point getCasillaSeleccionada() {
        return tableroDisparo.getCasillaSeleccionada();
    }
    
////    private void cambiarTurno() {
////        JugadorDTO jugadorTurnoActual = juego.getJugadorTurno();
////
////        if (jugadorTurnoActual.equals(juego.getJugadores().get(0))) {
////            juego.setJugadorTurno(juego.getJugadores().get(1));
////        } else {
////            juego.setJugadorTurno(juego.getJugadores().get(0));
////        }
////
////        // Actualizar la interfaz del botón según el nuevo turno
////        boolean esMiTurno = esJugador1
////                ? juego.getJugadorTurno().equals(juego.getJugadores().get(0))
////                : juego.getJugadorTurno().equals(juego.getJugadores().get(1));
////
////        btnAtacar.setEnabled(esMiTurno);
////    }
    
}
