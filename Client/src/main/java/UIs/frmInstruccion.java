/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UIs;

import Controll.Controller;
import Controll.JugadorConectadoListener;
import com.id.dtos_sh.JuegoDTO;
import com.id.dtos_sh.NaveDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class frmInstruccion extends javax.swing.JFrame implements JugadorConectadoListener {

    Controller cont = Controller.getInstance();

    /**
     * Creates new form frmInstruccion
     */
    public frmInstruccion() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        cont.setJugadorConectadoListener(this);

        // Personalizar el panel existente para mostrar una imagen de fondo
        jPanel1 = new JPanel() {
            private Image fondo = new ImageIcon(getClass().getResource("/mapa.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen escalada al tamaño del panel
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Configurar layout y agregar el panel personalizado como contenido principal
        jPanel1.setLayout(null); // Desactivar layout automático
        setContentPane(jPanel1); // Reemplazar contenido principal por el panel personalizado

        // Configurar componentes manualmente
        jLabel1.setBounds(264, 10, 400, 40);
        txtNombre.setBounds(60, 100, 200, 30);
        jLabel2.setBounds(60, 80, 200, 20);
        labelRegla.setBounds(380, 70, 380, 430);
        jLabel5.setBounds(60, 160, 200, 20);
        cbColor.setBounds(60, 180, 200, 30);
        jBarcoImagen.setBounds(60, 220, 200, 100);
        btnUnirse.setBounds(60, 420, 200, 40);
        btnCodigo.setBounds(60, 470, 200, 40);

        labelRegla.setText("<html>"
                + "<h2>Reglas del Juego:</h2><br>"
                + "<b>1. Objetivo:</b> Hundir todas las naves enemigas.<br><br>"
                + "<b>2. Preparación:</b> Tablero 10x10, las naves no se superponen.<br><br>"
                + "<b>3. Tipos de Naves:</b><br>"
                + "Portaviones: 5 casillas, Acorazado: 4, Destructor: 3, Lancha: 2.<br><br>"
                + "<b>4. Turnos:</b> Dispara a las coordenadas enemigas.<br>"
                + "Impacto da turno extra, fallo pasa el turno.<br><br>"
                + "<b>5. Hundimientos:</b> Se hunde cuando todas las casillas son impactadas.<br><br>"
                + "<b>6. Tiempo:</b> 30 segundos por turno.<br><br>"
                + "<b>7. Disparos Inválidos:</b> No repitas casillas ni dispares fuera del tablero.<br><br>"
                + "<b>8. Final:</b> Gana quien hunde todas las naves enemigas.<br>"
                + "</html>");

        estilizarTextField(txtNombre);
        estilizarLabel(jLabel2);
        estilizarLabel(labelRegla);
        estilizarLabel(jLabel5);
        estilizarComboBox(cbColor);
        estilizarBoton(btnUnirse);
        estilizarBoton(btnCodigo);

        // Agregar componentes al panel
        jPanel1.add(jLabel1);
        jPanel1.add(txtNombre);
        jPanel1.add(jLabel2);
        jPanel1.add(labelRegla);
        jPanel1.add(jLabel5);
        jPanel1.add(cbColor);
        jPanel1.add(jBarcoImagen);
        jPanel1.add(btnUnirse);
        jPanel1.add(btnCodigo);

        // Agregar acción al combo box
        cbColor.addActionListener(e -> {
            String colorSeleccionado = (String) cbColor.getSelectedItem();

            if (colorSeleccionado.equalsIgnoreCase("Rojo")) {
                Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/barcoR2.png"))
                        .getImage().getScaledInstance(jBarcoImagen.getWidth(), jBarcoImagen.getHeight(), Image.SCALE_SMOOTH));
                jBarcoImagen.setIcon(icono);
            } else if (colorSeleccionado.equalsIgnoreCase("Azul")) {
                Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/barcoA2.png"))
                        .getImage().getScaledInstance(jBarcoImagen.getWidth(), jBarcoImagen.getHeight(), Image.SCALE_SMOOTH));
                jBarcoImagen.setIcon(icono);
            } else if (colorSeleccionado.equalsIgnoreCase("Verde")) {
                Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/barcoV2.png"))
                        .getImage().getScaledInstance(jBarcoImagen.getWidth(), jBarcoImagen.getHeight(), Image.SCALE_SMOOTH));
                jBarcoImagen.setIcon(icono);
            }
        });
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(139, 69, 19)); // Color marrón oscuro
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFont(new Font("Lucida Fax", Font.PLAIN, 16)); // Fuente personalizada
        boton.setBorderPainted(false); // Sin bordes
        boton.setFocusPainted(false); // Sin foco
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(160, 82, 45)); // Marrón más claro al pasar el cursor
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(139, 69, 19)); // Marrón oscuro original al salir el cursor
            }
        });
    }

    private void estilizarTextField(JTextField textField) {
        textField.setBackground(new Color(245, 245, 245)); // Fondo gris claro
        textField.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
        textField.setFont(new Font("Lucida Fax", Font.PLAIN, 14)); // Fuente personalizada
        textField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2)); // Borde marrón
        textField.setCaretColor(new Color(139, 69, 19)); // Cursor marrón
    }

    private void estilizarLabel(JLabel label) {
        label.setFont(new Font("Lucida Fax", Font.BOLD, 14)); // Fuente personalizada
        label.setForeground(new Color(139, 69, 19)); // Color marrón oscuro
        label.setHorizontalAlignment(SwingConstants.CENTER); // Alinear al centro
    }

    private void estilizarComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(245, 245, 245)); // Fondo gris claro
        comboBox.setForeground(new Color(50, 50, 50)); // Texto gris oscuro
        comboBox.setFont(new Font("Lucida Fax", Font.PLAIN, 14)); // Fuente personalizada
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2)); // Borde marrón
        comboBox.setFocusable(false); // Quitar el borde de enfoque al seleccionar
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelRegla = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBarcoImagen = new javax.swing.JLabel();
        cbColor = new javax.swing.JComboBox<>();
        btnUnirse = new javax.swing.JButton();
        btnCodigo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 2, 36)); // NOI18N
        jLabel1.setText("Instrucciones");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Ingrese su nombre");

        labelRegla.setText("Falta las reglas aqui");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Seleccione un color");

        cbColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un color", "Rojo", "Azul", "Verde" }));

        btnUnirse.setText("Jugar una partida");
        btnUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirseActionPerformed(evt);
            }
        });

        btnCodigo.setText("Unirse con codigo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelRegla)
                .addGap(123, 123, 123))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jBarcoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUnirse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(labelRegla)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(cbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jBarcoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void btnUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirseActionPerformed
        if (!txtNombre.getText().isBlank() && cbColor.getSelectedItem() != null && !cbColor.getSelectedItem().toString().equals("Seleccione un color")) {
            String nombreJugador = txtNombre.getText();
            String colorSeleccionado = cbColor.getSelectedItem().toString();

            // Crea el evento en el controlador para enviar al servidor
            cont.jugadorConectado(nombreJugador, colorSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un nombre y selecciona un color.");
        }
    }//GEN-LAST:event_btnUnirseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCodigo;
    private javax.swing.JButton btnUnirse;
    private javax.swing.JComboBox<String> cbColor;
    private javax.swing.JLabel jBarcoImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelRegla;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarVista(boolean avanzar) {
        if (avanzar) {
            System.out.println("entre");
            JOptionPane.showMessageDialog(this, "Conectado al servidor. Avanzando a la siguiente pantalla...");
            
            new frmTablero(cont,cbColor.getSelectedItem().toString()).setVisible(true);
            this.dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo conectar, la partida está llena o hubo un error.");
        }
    }

    @Override
    public void actualizarNaves(List<NaveDTO> naves) {
        
    }

    @Override
    public void iniciarPartida(JuegoDTO modelo, boolean jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
