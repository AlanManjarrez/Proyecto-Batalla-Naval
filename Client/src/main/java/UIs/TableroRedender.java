/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIs;

import com.id.dtos_sh.CasillaDTO;
import com.id.dtos_sh.CoordenadaDTO;
import com.id.dtos_sh.NaveDTO;
import com.id.dtos_sh.OrientacionDTO;
import com.id.dtos_sh.TableroDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author JESUS
 */
public class TableroRedender extends JPanel{
     private TableroDTO tableroDTO;
    private int cellSize;
    private BufferedImage barcoImage; // Imagen base para los barcos

    public TableroRedender(TableroDTO tableroDTO, String colorJugador) {
        this.tableroDTO = copiarTablero(tableroDTO); // Copiar el tablero para evitar referencias compartidas
        this.barcoImage = asignarImagenPorColor(colorJugador); // Cargar la imagen como BufferedImage
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

        // Dibujar las casillas
       
       //System.out.println("Redibujando TableroRedender. Tamaño: " + size);

        for (int y = 0; y < size; y++) { // Y representa las filas
            for (int x = 0; x < size; x++) { // X representa las columnas
                CasillaDTO casilla = casillas[x][y]; // Acceder correctamente usando Y como fila y X como columna
                //System.out.println("Casilla [" + y + "][" + x + "]: Estado=" + casilla.isEstado());
                g.setColor(casilla.isEstado() ? Color.RED : Color.BLUE); // Cambiar el color según el estado
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize); // Usar X para columnas y Y para filas
            }
        }

        // Dibujar las naves
        Graphics2D g2d = (Graphics2D) g;
        for (NaveDTO nave : tableroDTO.getNaves()) {
            dibujarNaveConEstado(g2d, nave);
        }
    }

    private void dibujarNaveConEstado(Graphics2D g2d, NaveDTO nave) {
        CoordenadaDTO coordenadaInicial = nave.getCasilla().getCoordenada();
        int fila = coordenadaInicial.getX();
        int columna = coordenadaInicial.getY();

        boolean esHorizontal = nave.getDireccion() == OrientacionDTO.HORIZONTAL;

        // Dibujar cada parte de la nave según las casillas que ocupa
        for (int i = 0; i < nave.getLongitud(); i++) {
            int x = columna * cellSize + (esHorizontal ? i * cellSize : 0);
            int y = fila * cellSize + (esHorizontal ? 0 : i * cellSize);

            boolean impactado = tableroDTO.getCasillas()[fila + (esHorizontal ? 0 : i)][columna + (esHorizontal ? i : 0)].isEstado();

            if (impactado) {
                // Si está impactado, marcar de rojo
                g2d.setColor(Color.RED);
                g2d.fillRect(x, y, cellSize, cellSize);
            } else if (barcoImage != null) {
                // Dibujar la parte no impactada de la nave
                Image parteNave = recortarImagen(barcoImage, nave.getLongitud(), i, esHorizontal);
                g2d.drawImage(parteNave, x, y, cellSize, cellSize, this);
            }
        }
    }

    private BufferedImage recortarImagen(BufferedImage img, int totalPartes, int parteActual, boolean esHorizontal) {
        int ancho = img.getWidth();
        int alto = img.getHeight();
        int recorteAncho = esHorizontal ? ancho / totalPartes : ancho;
        int recorteAlto = esHorizontal ? alto : alto / totalPartes;

        int x = esHorizontal ? parteActual * recorteAncho : 0;
        int y = esHorizontal ? 0 : parteActual * recorteAlto;

        return img.getSubimage(x, y, recorteAncho, recorteAlto);
    }

    private BufferedImage asignarImagenPorColor(String colorSeleccionado) {
        try {
            String ruta = "";
            if (colorSeleccionado.equalsIgnoreCase("Rojo")) {
                ruta = "/barcoR2.png";
            } else if (colorSeleccionado.equalsIgnoreCase("Azul")) {
                ruta = "/barcoA2.png";
            } else if (colorSeleccionado.equalsIgnoreCase("Verde")) {
                ruta = "/barcoV2.png";
            } else {
                ruta = "/barcoDefault.png";
            }
            // Cargar la imagen como BufferedImage
            return ImageIO.read(getClass().getResource(ruta));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Devuelve null si no se puede cargar la imagen
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

        // Copiar naves
        List<NaveDTO> navesCopia = new ArrayList<>();
        for (NaveDTO nave : original.getNaves()) {
            navesCopia.add(copiarNave(nave));
        }
        copia.setNaves(navesCopia);

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

    private NaveDTO copiarNave(NaveDTO original) {
        if (original == null) {
            return null;
        }
        NaveDTO copia = new NaveDTO();
        copia.setTipo(original.getTipo());
        copia.setLongitud(original.getLongitud());
        copia.setDireccion(original.getDireccion());
        copia.setCasilla(copiarCasilla(original.getCasilla()));
        return copia;
    }

    public void setTableroDTO(TableroDTO nuevoTableroDTO) {
        this.tableroDTO = nuevoTableroDTO; // Copiar para evitar referencias compartidas
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500); // Tamaño predeterminado
    }
}
