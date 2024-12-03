/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliares;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.ImageIcon;

/**
 *
 * @author JESUS
 */
public class NaveTransferable implements Transferable {

    private final String tipoNave; // Tipo de la nave
    private final ImageIcon icono; // Ícono de la nave

    public NaveTransferable(String tipoNave, ImageIcon icono) {
        this.tipoNave = tipoNave;
        this.icono = icono;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.stringFlavor, DataFlavor.imageFlavor };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DataFlavor.stringFlavor.equals(flavor) || DataFlavor.imageFlavor.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (DataFlavor.stringFlavor.equals(flavor)) {
            return tipoNave;
        } else if (DataFlavor.imageFlavor.equals(flavor)) {
            return icono;
        }
        throw new UnsupportedFlavorException(flavor);
    }

    public String getTipoNave() {
        return tipoNave;
    }

    public ImageIcon getIcono() {
        return icono;
    }
    
}
