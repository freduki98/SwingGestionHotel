/**
 * Ventana.java
 * 2 oct 2024 9:47:33
 * @author Alfredo José Rodríguez Zurita
 */
package swing_c_p02_rodriguezZuritaAlfredoJose;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Class Ventana.
 */
public class Ventana extends JFrame implements ActionListener {

    /** The dimension pantalla. */
    private Dimension dimensionPantalla;
    
    /** The barra menu. */
    private JMenuBar barraMenu;
    
    /** The ayuda menu. */
    private JMenu archivoMenu, registroMenu, ayudaMenu;
    
    /** The salir item. */
    private JMenuItem altaReservasItem, bajaReservasItem, acercaDeItem, salirItem;
    
    /** The panel botones. */
    private JPanel panelPrincipal, panelPintura, panelBotones;
    
    /** The btn baja reserva. */
    private JButton btnAltaReserva, btnBajaReserva;

    /**
     * Instantiates a new ventana.
     */
    public Ventana() {
        super("Gestión Hotel \"THE GRAND BUDAPEST HOTEL\"");
        configurarVentana();
        inicializarComponentes();
        setVisible(true);
    }

    /**
     * Configurar ventana.
     */
    // Configura propiedades principales de la ventana
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimensionPantalla.width / 2, dimensionPantalla.height / 2);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.jpg")).getImage());
        setResizable(false);
    }

    /**
     * Inicializar componentes.
     */
    // Inicializa paneles, botones y menús
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(198, 205, 247));

        panelPintura = new JPanel();
        panelPintura.add(crearPinturaHotel());
        panelPintura.setBackground(new Color(198, 205, 247));

        panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        btnAltaReserva = crearBoton("ALTA RESERVA", "nuevo.png", this);
        btnBajaReserva = crearBoton("BAJA RESERVA", "eliminar.png", this);
        panelBotones.add(btnAltaReserva);
        panelBotones.add(btnBajaReserva);

        panelPrincipal.add(panelPintura, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        iniciarMenu();
        add(panelPrincipal);
    }

    /**
     * Crear pintura hotel.
     *
     * @return the j label
     */
    // Crea y devuelve una etiqueta con la imagen del hotel escalada
    private JLabel crearPinturaHotel() {
        Image imagenHotel = new ImageIcon(getClass().getResource("/recursos/hotel.jpg"))
            .getImage()
            .getScaledInstance(dimensionPantalla.width / 2, (int) (dimensionPantalla.height / 2.7), Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(imagenHotel));
    }

    /**
     * Iniciar menu.
     */
    // Configura la barra de menú y los menús
    private void iniciarMenu() {
        barraMenu = new JMenuBar();

        archivoMenu = crearMenu("Archivo", KeyEvent.VK_F, null);
        registroMenu = crearMenu("Registro", KeyEvent.VK_S, this);
        ayudaMenu = crearMenu("Ayuda", KeyEvent.VK_H, null);

        salirItem = crearMenuItem("Salir", this);
        altaReservasItem = crearMenuItem("Alta Reservas", KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), this);
        bajaReservasItem = crearMenuItem("Baja Reservas", KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), this);
        acercaDeItem = crearMenuItem("Acerca de ...", this);

        archivoMenu.add(salirItem);
        registroMenu.add(altaReservasItem);
        registroMenu.add(bajaReservasItem);
        ayudaMenu.add(acercaDeItem);

        barraMenu.add(archivoMenu);
        barraMenu.add(registroMenu);
        barraMenu.add(ayudaMenu);

        setJMenuBar(barraMenu);
    }

    /**
     * Crear boton.
     *
     * @param texto the texto
     * @param nombreImagen the nombre imagen
     * @param listener the listener
     * @return the j button
     */
    // Crea un botón con texto, ícono y evento asociado
    private JButton crearBoton(String texto, String nombreImagen, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.setIcon(new ImageIcon(
            new ImageIcon(getClass().getResource("/recursos/" + nombreImagen))
                .getImage()
                .getScaledInstance(dimensionPantalla.width / 76, dimensionPantalla.height / 43, Image.SCALE_SMOOTH)));
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.addActionListener(listener);
        return boton;
    }

    /**
     * Crear menu.
     *
     * @param texto the texto
     * @param mnemonic the mnemonic
     * @param listener the listener
     * @return the j menu
     */
    private JMenu crearMenu(String texto, int mnemonic, ActionListener listener) {
        JMenu menu = new JMenu(texto);
        menu.setMnemonic(mnemonic);
        if (listener != null) menu.addActionListener(listener);
        return menu;
    }

    /**
     * Crear menu item.
     *
     * @param texto the texto
     * @param listener the listener
     * @return the j menu item
     */
    private JMenuItem crearMenuItem(String texto, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(texto);
        menuItem.addActionListener(listener);
        return menuItem;
    }

    /**
     * Crear menu item.
     *
     * @param texto the texto
     * @param atajo the atajo
     * @param listener the listener
     * @return the j menu item
     */
    private JMenuItem crearMenuItem(String texto, KeyStroke atajo, ActionListener listener) {
        JMenuItem menuItem = crearMenuItem(texto, listener);
        menuItem.setAccelerator(atajo);
        return menuItem;
    }

    /**
     * Action performed.
     *
     * @param e the e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        if (fuente == altaReservasItem || fuente == btnAltaReserva) {
            new VentanaDialogoAltaReserva(this);
        } else if (fuente == bajaReservasItem || fuente == btnBajaReserva) {
            JOptionPane.showMessageDialog(this, "Esta opción aún no ha sido desarrollada.");
        } else if (fuente == salirItem) {
            dispose();
        } else if (fuente == acercaDeItem) {
            JOptionPane.showMessageDialog(this, "Aplicación: GRAND HOTEL BUDAPEST\n" +
                                                "Dueño: Alfredo José Rodríguez Zurita\n" +
                                                "Objetivo: Gestionar un hotel\n" +
                                                "Versión: 1.0.0");
        }
    }
}
