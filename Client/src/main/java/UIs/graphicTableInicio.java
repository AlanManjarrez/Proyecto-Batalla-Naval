/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIs;

import com.id.dtos_sh.TableroDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class graphicTableInicio extends JPanel {

    private int cellSize;
    private TableroDTO tableroDTO;

    public graphicTableInicio(TableroDTO tableroDTO) {
        this.tableroDTO = tableroDTO;
        setLayout(new BorderLayout());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getWidth(), getParent().getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = tableroDTO.getTamaño();
        int availableWidth = getWidth();
        int availableHeight = getHeight();

        int cellSize = Math.min(availableWidth / size, availableHeight / size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                g.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

}
