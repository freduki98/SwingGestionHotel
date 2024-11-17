/**
 * PanelHotel.java
 * 13 nov 2024 10:35:51
 * @author Alfredo José Rodríguez Zurita
 */
package swing_c_p02_rodriguezZuritaAlfredoJose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Class PanelHotel.
 */
public class PanelHotel extends JPanel {

    /**
     * Instantiates a new panel hotel.
     */
    public PanelHotel() {

        // Configuración del panel principal
        this.setLayout(new BorderLayout());
        Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBorder(BorderFactory.createLineBorder(new Color(245, 245, 220), 1)); // Borde del panel
        this.setBackground(new Color(230, 160, 196)); // Color de fondo
        this.setPreferredSize(new Dimension(dimensionPantalla.width, dimensionPantalla.height / 24)); // Tamaño preferido

        // Crear el título del hotel
        JLabel tituloHotel = new JLabel("THE GRAND HOTEL BUDAPEST", SwingConstants.CENTER);
        tituloHotel.setForeground(new Color(90, 90, 90)); // Color del texto del título

        // Crear el panel superior y agregar el título
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(248, 184, 192)); // Fondo del panel superior
        panelSuperior.add(tituloHotel, BorderLayout.CENTER);

        // Añadir el panel superior al panel principal
        this.add(panelSuperior);

        // Hacer visible el panel
        this.setVisible(true);
    }
}
