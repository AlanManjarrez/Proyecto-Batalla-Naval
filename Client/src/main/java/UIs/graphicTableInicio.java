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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
public class graphicTableInicio extends JPanel {
    
    private int cellSize;
    private TableroDTO tableroDTO;
    private NaveDTO[][] tablero; 
    private Image naveImage;

    private final List<Point> posicionesNaves = new ArrayList<>();  // Para almacenar las posiciones de las naves
    private final List<String> tiposNaves = new ArrayList<>(); // Para almacenar los tipos de naves
    private final List<ImageIcon> imagenesNaves = new ArrayList<>(); // Para almacenar las imágenes de las naves    
    private graphicNaves panelNaves;
    private NaveDTO naveSeleccionada = null;
    private int filaInicio = -1;
    private int columnaInicio = -1;

    public graphicTableInicio(TableroDTO tableroDTO, graphicNaves panelNaves, String colorSeleccionado) {
        this.tableroDTO = tableroDTO;
        this.panelNaves=panelNaves;
        this.tablero = new NaveDTO[10][10];
        
         naveImage = asignarImagenPorColor(colorSeleccionado);

         // Habilitar el panel para recibir el drop de las naves
        setDropTarget(new DropTarget(this, DnDConstants.ACTION_COPY, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent e) {}

            @Override
            public void dragOver(DropTargetDragEvent e) {}

            @Override
            public void dropActionChanged(DropTargetDragEvent e) {}

            @Override
            public void dragExit(DropTargetEvent e) {}

           @Override
            public void drop(DropTargetDropEvent e) {
                e.acceptDrop(DnDConstants.ACTION_COPY);

                Transferable transferable = e.getTransferable();
                try {
                    if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String nombreNave = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        System.out.println("Nombre de nave transferido: " + nombreNave);

                        NaveDTO naveColocada = obtenerNavePorNombre(nombreNave);

                        if (naveColocada == null) {
                            System.err.println("No se encontró ninguna nave correspondiente al nombre: " + nombreNave);
                            return;
                        }

                        if (naveColocada.getDireccion() == null) {
                            System.err.println("La nave no tiene una dirección asignada.");
                            return;
                        }

                        // Obtener la posición del mouse relativa al componente
                        Point dropPoint = e.getLocation();
                        Point relativePoint = SwingUtilities.convertPoint(graphicTableInicio.this, dropPoint, graphicTableInicio.this);

                        // Calcular la posición en la cuadrícula
                        int x = relativePoint.x / cellSize;
                        int y = relativePoint.y / cellSize;

                        System.out.println("Intentando colocar la nave en posición: (" + x + ", " + y + ")");
                        System.out.println("Dirección de la nave: " + naveColocada.getDireccion());

                        // Validar si la nave cabe en la orientación seleccionada
                        boolean cabe = validarEspacio(naveColocada, y, x); // Nota: y corresponde a la fila y x a la columna
                        if (!cabe) {
                            System.err.println("No hay espacio suficiente para colocar la nave.");
                            return;
                        }

                        // Colocar la nave en la matriz del tablero
                        colocarNaveEnTablero(naveColocada, y, x); // Nota: y corresponde a la fila y x a la columna

                        System.out.println("Nave colocada correctamente en el tablero.");

                        posicionesNaves.add(new Point(x, y)); // Guardar la posición inicial
                        imagenesNaves.add(new ImageIcon(getClass().getResource("/barcoR2.png"))); // Agregar la imagen

                        repaint();

                        panelNaves.actualizarNavesDisponibles(naveColocada);
                    } else {
                        System.err.println("El tipo de dato transferido no es válido.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
            
        }));
            addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Detectar la posición del clic en el tablero
                Point clickPoint = e.getPoint();
                int fila = clickPoint.y / cellSize;
                int columna = clickPoint.x / cellSize;

                // Validar si la posición es válida
                if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[fila].length) {
                    NaveDTO nave = tablero[fila][columna];

                    // Si hay una nave en la celda clicada
                    if (nave != null) {
                        // Eliminar temporalmente la nave del tablero
                        removerNaveDelTablero(nave, fila, columna);

                        // Alternar la dirección de la nave
                        OrientacionDTO direccionOriginal = nave.getDireccion();
                        nave.setDireccion(direccionOriginal == OrientacionDTO.HORIZONTAL ? OrientacionDTO.VERTICAL : OrientacionDTO.HORIZONTAL);

                        // Validar si la nueva dirección es válida
                        if (!validarEspacio(nave, fila, columna)) {
                            // Revertir si la dirección no es válida
                            System.err.println("No se puede cambiar la dirección: no cabe o está demasiado cerca de otra nave.");
                            nave.setDireccion(direccionOriginal);
                            colocarNaveEnTablero(nave, fila, columna); // Restaurar la nave en su posición original
                        } else {
                            // Recolocar la nave en su nueva orientación
                            colocarNaveEnTablero(nave, fila, columna);
                            repaint(); // Redibujar el tablero
                        }
                    }
                }
            }
        });
            addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Detectar la posición inicial al hacer clic
                Point clickPoint = e.getPoint();
                int fila = clickPoint.y / cellSize;
                int columna = clickPoint.x / cellSize;

                // Validar si hay una nave en la celda seleccionada
                if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[fila].length) {
                    naveSeleccionada = tablero[fila][columna];
                    if (naveSeleccionada != null) {
                        filaInicio = fila;
                        columnaInicio = columna;
                        removerNaveDelTablero(naveSeleccionada, filaInicio, columnaInicio); // Eliminar temporalmente
                        repaint(); // Redibujar sin la nave
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Detectar la posición donde se suelta la nave
                if (naveSeleccionada != null) {
                    Point releasePoint = e.getPoint();
                    int fila = releasePoint.y / cellSize;
                    int columna = releasePoint.x / cellSize;

                    // Validar si la nueva posición es válida
                    if (validarEspacio(naveSeleccionada, fila, columna)) {
                        // Colocar la nave en la nueva posición
                        colocarNaveEnTablero(naveSeleccionada, fila, columna);
                    } else {
                        // Restaurar la nave en su posición original si no es válida
                        colocarNaveEnTablero(naveSeleccionada, filaInicio, columnaInicio);
                        System.err.println("No se puede mover la nave aquí: no cabe o está demasiado cerca de otra nave.");
                    }

                    naveSeleccionada = null; // Limpiar la selección
                    filaInicio = -1;
                    columnaInicio = -1;
                    repaint(); // Redibujar el tablero
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Redibujar mientras se arrastra
                if (naveSeleccionada != null) {
                    repaint();
                }
            }
        });
            
        
    }

    private NaveDTO obtenerNavePorIcono(ImageIcon naveIcon) {
        if (naveIcon == null || naveIcon.getDescription() == null) {
            System.err.println("El ícono de la nave es nulo o no tiene descripción.");
            return null; // Devuelve null si el ícono no tiene información válida
        }
        for (NaveDTO nave : panelNaves.naves) {
            if (nave.getTipo().equals(naveIcon.getDescription())) { // Asegúrate de usar una propiedad única
                return nave;
            }
        }
        return null; // Devuelve null si no encuentra una coincidencia
    }
    
    private NaveDTO obtenerNavePorNombre(String nombre) {
        for (NaveDTO nave : panelNaves.naves) {
            if (nombre.startsWith(nave.getTipo())) {
                return nave;
            }
        }
        return null; // Devuelve null si no encuentra la nave
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getWidth(), getParent().getHeight());
    }

   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = 10; // Tamaño del tablero (10x10)
        int availableWidth = getWidth();
        int availableHeight = getHeight();

        // Calcular el tamaño de las celdas
        cellSize = Math.min(availableWidth / size, availableHeight / size);

        // Dibujar las celdas del tablero
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                g.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }

        // Dibujar las naves en el tablero
        Graphics2D g2d = (Graphics2D) g;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                NaveDTO nave = tablero[fila][columna];
                if (nave != null && esInicioDeNave(fila, columna, nave)) {
                    dibujarNave(g2d, nave, fila, columna);
                }
            }
        }

        // Dibujar la nave seleccionada mientras se arrastra
        if (naveSeleccionada != null) {
            Point mousePos = getMousePosition();
            if (mousePos != null) {
                int x = mousePos.x / cellSize;
                int y = mousePos.y / cellSize;
                dibujarNave(g2d, naveSeleccionada, y, x);
            }
        }
    }

    // Método para rotar una imagen en un ángulo especificado
    private Image rotarImagen(Image img, double angulo) {
        int w = img.getWidth(this);
        int h = img.getHeight(this);
        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(angulo), w / 2, h / 2);
        g2d.drawImage(img, 0, 0, this);
        g2d.dispose();
        return rotated;
    }
    
    private boolean validarEspacio(NaveDTO nave, int fila, int columna) {
        int longitud = nave.getLongitud();

        // Verificar si la nave cabe dentro del tablero según su orientación
        if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
            if (columna + longitud > tablero[0].length) { // Excede el borde derecho
                return false;
            }
        } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
            if (fila + longitud > tablero.length) { // Excede el borde inferior
                return false;
            }
        }

        // Verificar todas las celdas ocupadas por la nave y su espacio adyacente
        for (int i = -1; i <= longitud; i++) { // Desde una casilla antes hasta una casilla después de la longitud
            for (int j = -1; j <= 1; j++) { // Desde una casilla arriba hasta una casilla abajo
                int checkFila = fila;
                int checkColumna = columna;

                if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
                    checkColumna += i; // Mover a lo largo de la longitud de la nave
                    checkFila += j;    // Revisar las casillas adyacentes arriba y abajo
                } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
                    checkFila += i;    // Mover a lo largo de la longitud de la nave
                    checkColumna += j; // Revisar las casillas adyacentes a la izquierda y derecha
                }

                // Verificar si la celda está dentro del tablero
                if (checkFila >= 0 && checkFila < tablero.length && checkColumna >= 0 && checkColumna < tablero[0].length) {
                    // Si la casilla está ocupada por otra nave, no es válido
                    if (tablero[checkFila][checkColumna] != null) {
                        return false;
                    }
                }
            }
        }

        return true; // La nave puede colocarse
    }

    private void colocarNaveEnTablero(NaveDTO nave, int fila, int columna) {
        int longitud = nave.getLongitud();

        // Validar si la nave cabe dentro del tablero (seguridad adicional)
        if (!validarEspacio(nave, fila, columna)) {
            System.err.println("Intento de colocar una nave fuera de los límites del tablero.");
            return;
        }

        // Colocar la nave en las celdas correspondientes
        for (int i = 0; i < longitud; i++) {
            if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
                if (columna + i < tablero[0].length) { // Evitar exceder el borde derecho
                    tablero[fila][columna + i] = nave;
                }
            } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
                if (fila + i < tablero.length) { // Evitar exceder el borde inferior
                    tablero[fila + i][columna] = nave;
                }
            }
        }
    }
    
    private boolean esInicioDeNave(int fila, int columna, NaveDTO nave) {
        if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
            // La posición inicial de una nave horizontal es su primera celda
            return columna == 0 || tablero[fila][columna - 1] != nave;
        } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
            // La posición inicial de una nave vertical es su primera celda
            return fila == 0 || tablero[fila - 1][columna] != nave;
        }
        return true; // Por defecto, considera que es el inicio
    }
    
    private void removerNaveDelTablero(NaveDTO nave, int fila, int columna) {
        int longitud = nave.getLongitud();

        for (int i = 0; i < longitud; i++) {
            if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
                tablero[fila][columna + i] = null;
            } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
                tablero[fila + i][columna] = null;
            }
        }
    }
    
    private void dibujarNave(Graphics2D g2d, NaveDTO nave, int fila, int columna) {
        int x = columna * cellSize;
        int y = fila * cellSize;

        // Calcular el ancho y alto de la nave según su longitud y dirección
        int naveWidth = cellSize;
        int naveHeight = cellSize;

        if (nave.getDireccion() == OrientacionDTO.HORIZONTAL) {
            naveWidth = cellSize * nave.getLongitud();
        } else if (nave.getDireccion() == OrientacionDTO.VERTICAL) {
            naveHeight = cellSize * nave.getLongitud();
        }

        // Dibujar la nave
        g2d.drawImage(naveImage, x, y, naveWidth, naveHeight, this);
    }
    
    public List<NaveDTO> obtenerNavesConPosiciones() {
        List<NaveDTO> navesPosiciones = new ArrayList<>();

        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                NaveDTO nave = tablero[fila][columna];

                // Verificar si es la posición inicial de una nave
                if (nave != null && esInicioDeNave(fila, columna, nave)) {
                    // Crear una copia de la nave con la posición inicial como CasillaDTO
                    NaveDTO naveCopia = new NaveDTO();
                    naveCopia.setTipo(nave.getTipo());
                    naveCopia.setDireccion(nave.getDireccion());
                    naveCopia.setLongitud(nave.getLongitud());

                    // Asignar la posición inicial como CasillaDTO
                    CoordenadaDTO coordenadaInicial = new CoordenadaDTO(fila, columna);
                    CasillaDTO casillaInicial = new CasillaDTO(coordenadaInicial);
                    naveCopia.setCasilla(casillaInicial); // Método en NaveDTO para establecer la posición inicial

                    navesPosiciones.add(naveCopia);
                }
            }
        }

        return navesPosiciones;
    }
    
    private Image asignarImagenPorColor(String colorSeleccionado) {
        if (colorSeleccionado.equalsIgnoreCase("Rojo")) {
            return new ImageIcon(getClass().getResource("/barcoR2.png")).getImage();
        } else if (colorSeleccionado.equalsIgnoreCase("Azul")) {
            return new ImageIcon(getClass().getResource("/barcoA2.png")).getImage();
        } else if (colorSeleccionado.equalsIgnoreCase("Verde")) {
            return new ImageIcon(getClass().getResource("/barcoV2.png")).getImage();
        } else {
            return new ImageIcon(getClass().getResource("/barcoDefault.png")).getImage();
        }
    }
    
}
