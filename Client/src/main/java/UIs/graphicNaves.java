/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIs;

import Auxiliares.ImageIconTransferable;
import Auxiliares.NaveTransferable;
import com.id.dtos_sh.NaveDTO;
import com.id.dtos_sh.OrientacionDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class graphicNaves extends JPanel {
    
    public String colorSeleccionado;
    public List<NaveDTO> naves;
    
    private Map<String, JPanel> panelesPorTipo = new HashMap<>();

    public graphicNaves(List<NaveDTO> naves, String colorSeleccionado) {
        this.naves = naves;
        this.colorSeleccionado=colorSeleccionado;
        
        // Cambiar el diseño de jBarcos para organizar los grupos verticalmente
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear un mapa para agrupar las naves por tipo
        Map<String, List<NaveDTO>> navesPorTipo = new HashMap<>();
        for (NaveDTO nave : naves) {
            navesPorTipo.computeIfAbsent(nave.getTipo(), k -> new ArrayList<>()).add(nave);
        }

        // Iterar por cada tipo de nave y crear un grupo
        for (Map.Entry<String, List<NaveDTO>> entry : navesPorTipo.entrySet()) {
            String tipo = entry.getKey();
            List<NaveDTO> navesDeEsteTipo = entry.getValue();

            // Crear un panel para este grupo de naves
            JPanel panelGrupo = new JPanel();
            panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.Y_AXIS));

            // Agregar un título para el tipo de nave
            JLabel titulo = new JLabel(tipo);
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            titulo.setFont(new Font("Arial", Font.BOLD, 14));

            panelGrupo.add(titulo);
            panelGrupo.add(Box.createVerticalStrut(5)); // Espaciado debajo del título

            // Crear el panel que contiene las naves
            JPanel panelNaves = new JPanel();
            panelNaves.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Organización horizontal con espacio

            int imageWidth = 50;
            int imageHeight = 50;

            for (NaveDTO nave : navesDeEsteTipo) {
                JLabel naveLabel = new JLabel();
                ImageIcon naveIcon;
                 if (colorSeleccionado.equalsIgnoreCase("Rojo")) {
                    naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoR2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else if (colorSeleccionado.equalsIgnoreCase("Azul")) {
                     naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoA2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else if (colorSeleccionado.equalsIgnoreCase("Verde")) {
                naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoV2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else {
                    naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoDefault.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                }

                naveLabel.setIcon(naveIcon);
                naveLabel.setName(nave.getTipo());

                naveLabel.setTransferHandler(new TransferHandler("name") {
                    @Override
                    protected Transferable createTransferable(JComponent c) {
                        JLabel label = (JLabel) c;
                        return new StringSelection(label.getName());
                    }
                });

                naveLabel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        JComponent comp = (JComponent) e.getSource();
                        comp.getTransferHandler().exportAsDrag(comp, e, TransferHandler.COPY);
                    }
                });

                panelNaves.add(naveLabel);
            }

            panelGrupo.add(panelNaves); // Agregar el panel con las naves
            panelGrupo.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Opcional: Añadir un borde alrededor del grupo

            // Guardar el panelNaves en el mapa por tipo
            panelesPorTipo.put(tipo, panelNaves);

            add(panelGrupo); // Agregar el grupo al panel principal
            add(Box.createVerticalStrut(15)); // Espaciado entre grupos
        }
    }

    public boolean naveDisponible(NaveDTO nave) {
        long count = naves.stream().filter(n -> n.getTipo().equals(nave.getTipo())).count();
        return count > 0;
    }
    
    public void actualizarNavesDisponibles(NaveDTO nave) {
         if (nave == null) {
            System.err.println("La nave es nula, no se puede actualizar la lista de naves disponibles.");
            return;
        }
        
        JPanel panelNaves = panelesPorTipo.get(nave.getTipo());
        if (panelNaves == null) {
            System.err.println("No se encontró el panel para el tipo de nave: " + nave.getTipo());
            return;
        }

        // Actualizar la lista lógica eliminando solo una instancia
        boolean removed = false;
        for (int i = 0; i < naves.size(); i++) {
            if (naves.get(i).getTipo().equals(nave.getTipo())) {
                naves.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.err.println("No se encontró una nave del tipo para eliminar: " + nave.getTipo());
            return;
        }

        // Limpiar el panel que contiene las naves
        panelNaves.removeAll();

        // Reagregar las naves restantes de este tipo
        int imageWidth = 50;
        int imageHeight = 50;

        for (NaveDTO n : naves) {
            if (n.getTipo().equals(nave.getTipo())) {
                JLabel naveLabel = new JLabel();
                ImageIcon naveIcon;
                 if (colorSeleccionado.equalsIgnoreCase("Rojo")) {
                    naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoR2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else if (colorSeleccionado.equalsIgnoreCase("Azul")) {
                     naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoA2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else if (colorSeleccionado.equalsIgnoreCase("Verde")) {
                naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoV2.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                } else {
                    naveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/barcoDefault.png"))
                        .getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
                }
                naveLabel.setIcon(naveIcon);
                naveLabel.setName(n.getTipo());

                naveLabel.setTransferHandler(new TransferHandler("name") {
                    @Override
                    protected Transferable createTransferable(JComponent c) {
                        JLabel label = (JLabel) c;
                        return new StringSelection(label.getName());
                    }
                });

                naveLabel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        JComponent comp = (JComponent) e.getSource();
                        comp.getTransferHandler().exportAsDrag(comp, e, TransferHandler.COPY);
                    }
                });

                panelNaves.add(naveLabel);
            }
        }

        panelNaves.revalidate();
        panelNaves.repaint();
    }
    
    public boolean todasLasNavesColocadas() {
        return naves.isEmpty(); // Si la lista de naves está vacía, todas han sido colocadas
    }
    
    
}
