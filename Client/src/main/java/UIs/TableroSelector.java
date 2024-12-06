/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIs;

import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.CoordenadaDTO;
import com.id.dtos_sh.TableroDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author JESUS
 */
public class TableroSelector extends JPanel{
    private TableroDTO tableroDTO;
    private int cellSize;
    private Point casillaSeleccionada; 

    public TableroSelector(TableroDTO tableroDTO) {
        this.tableroDTO = copiarTablero(tableroDTO); 

        // Agregar un listener para manejar la selección de casillas
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClick(e.getPoint());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       if (tableroDTO == null) {
            System.out.println("TableroDTO es null, nada que dibujar.");
            return;
        }

        System.out.println("Redibujando TableroRedender con datos: " + tableroDTO);

        int size = tableroDTO.getTamaño();
        CasillaDTO[][] casillas = tableroDTO.getCasillas();
        cellSize = Math.min(getWidth() / size, getHeight() / size); // Tamaño de cada celda

        //System.out.println("Redibujando TableroRedender. Tamaño: " + size);

        for (int fila = 0; fila < size; fila++) {
            for (int columna = 0; columna < size; columna++) {
                CasillaDTO casilla = casillas[columna][fila];
                //System.out.println("Casilla [" + fila + "][" + columna + "]: Estado=" + casilla.isEstado());
                g.setColor(casilla.isEstado() ? Color.RED : Color.BLUE);
                g.fillRect(columna * cellSize, fila * cellSize, cellSize, cellSize);
            }
        }

        // Resaltar la casilla seleccionada si existe
        if (casillaSeleccionada != null) {
            g.setColor(Color.YELLOW); // Color para resaltar la selección
            g.fillRect(casillaSeleccionada.x * cellSize, casillaSeleccionada.y * cellSize, cellSize, cellSize);
        }
    }

    private void manejarClick(Point puntoClick) {
    int fila = puntoClick.y / cellSize; // Calcula la fila basada en Y
        int columna = puntoClick.x / cellSize; // Calcula la columna basada en X

        // Validar que el clic esté dentro del rango del tablero
        if (fila >= 0 && fila < tableroDTO.getTamaño() && columna >= 0 && columna < tableroDTO.getTamaño()) {
            casillaSeleccionada = new Point(columna, fila); // Almacena la casilla seleccionada como columna (X) y fila (Y)

            // Imprimir coordenadas para depuración
            System.out.println("Click detectado: Columna (X) = " + casillaSeleccionada.x + ", Fila (Y) = " + casillaSeleccionada.y);

            repaint(); // Redibuja para reflejar la selección
        }
    }

    private TableroDTO copiarTablero(TableroDTO original) {
        if (original == null) {
            return null;
        }

        TableroDTO copia = new TableroDTO();
        copia.setTamaño(original.getTamaño());

        // Copiar casillas
        CasillaDTO[][] casillasOriginal = original.getCasillas();
        CasillaDTO[][] casillasCopia = new CasillaDTO[casillasOriginal.length][casillasOriginal[0].length];
        for (int i = 0; i < casillasOriginal.length; i++) {
            for (int j = 0; j < casillasOriginal[i].length; j++) {
                casillasCopia[i][j] = copiarCasilla(casillasOriginal[i][j]);
            }
        }
        copia.setCasillas(casillasCopia);

        return copia;
    }

    private CasillaDTO copiarCasilla(CasillaDTO original) {
        if (original == null) {
            return null;
        }
        CasillaDTO copia = new CasillaDTO(new CoordenadaDTO(original.getCoordenada().getX(), original.getCoordenada().getY()));
        copia.setEstado(original.isEstado());
        return copia;
    }

    public Point getCasillaSeleccionada() {
        return casillaSeleccionada;
    }

    public void setTableroDTO(TableroDTO nuevoTableroDTO) {
        this.tableroDTO = nuevoTableroDTO; // Copiar para evitar referencias compartidas
        casillaSeleccionada = null; // Limpiar la selección al actualizar el tablero
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500); // Tamaño predeterminado
    }
    
    public void clearSeleccion() {
        casillaSeleccionada = null;
        repaint();
    }
}
