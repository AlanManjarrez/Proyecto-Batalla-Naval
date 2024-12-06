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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author JESUS
 */
public class TableroSelector extends JPanel {

    private TableroDTO tableroDTO;
    private int cellSize;
    private Point casillaSeleccionada;
    private BufferedImage barcoImage;
    private boolean jugador1;
    private int contador;
    private boolean terminar=true;
    private frmJuego j;

    public TableroSelector(TableroDTO tableroDTO, String colorJugador, boolean jugador1, frmJuego j) {
        this.tableroDTO = copiarTablero(tableroDTO);
        this.jugador1 = jugador1;
        this.j = j;
        contador = 0;
        this.barcoImage = asignarImagenPorColor(colorJugador);
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
        boolean no = true;
        int a = 0;
        for (int y = 0; y < size; y++) { // Y representa las filas
            for (int x = 0; x < size; x++) { // X representa las columnas

                CasillaDTO casilla = casillas[x][y]; // Acceder correctamente usando Y como fila y X como columna
                //System.out.println("Casilla [" + y + "][" + x + "]: Estado=" + casilla.isEstado());
                if (casilla.isEstado()) {
                    a++;
                    if (a==25) {
                        if(terminar){
                            j.fin();
                            terminar=false;
                        }
                       
                       return;
                    }
                    if (contador < a) {
                        contador++;
                        no = false;
                        j.mensaje(true);
                    }
                }
            }
        }
        //System.out.println("Redibujando TableroRedender. Tamaño: " + size);
        for (int fila = 0; fila < size; fila++) {
            for (int columna = 0; columna < size; columna++) {
                CasillaDTO casilla = casillas[columna][fila];
                //System.out.println("Casilla [" + fila + "][" + columna + "]: Estado=" + casilla.isEstado());
                g.setColor(casilla.isEstado() ? Color.RED : Color.BLUE);
                g.fillRect(columna * cellSize, fila * cellSize, cellSize, cellSize);
            }
        }
        if (!jugador1) {
            Graphics2D g2d = (Graphics2D) g;
            for (NaveDTO nave : tableroDTO.getNaves()) {
                dibujarNaveConEstado(g2d, nave);
            }
        }

        for (int y = 0; y < size; y++) { // Y representa las filas
            for (int x = 0; x < size; x++) { // X representa las columnas

                CasillaDTO casilla = casillas[x][y]; // Acceder correctamente usando Y como fila y X como columna
                //System.out.println("Casilla [" + y + "][" + x + "]: Estado=" + casilla.isEstado());
                g.setColor(casilla.isEstado() ? Color.RED : Color.BLUE); // Cambiar el color según el estado
                if (casilla.isEstado()) {

                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize); // Usar X para columnas y Y para filas
                }
            }
        }

        // Resaltar la casilla seleccionada si existe
        if (casillaSeleccionada != null) {
            g.setColor(Color.YELLOW); // Color para resaltar la selección
            g.fillRect(casillaSeleccionada.x * cellSize, casillaSeleccionada.y * cellSize, cellSize, cellSize);
        }
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

            if (barcoImage != null) {
                // Dibujar la parte no impactada de la nave
                Image parteNave = recortarImagen(barcoImage, nave.getLongitud(), i, esHorizontal);
                System.out.println("pintando imagen");
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

    public Point getCasillaSeleccionada() {
        return casillaSeleccionada;
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
