/**
 * VentanaDialogo.java
 * 7 nov 2024 8:51:40
 * @author Alfredo José Rodríguez Zurita
 */
package swing_c_p02_rodriguezZuritaAlfredoJose;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The Class VentanaDialogoAltaReserva.
 */
public class VentanaDialogoAltaReserva extends JDialog implements ActionListener {

    /** The panel titulo. */
    // Paneles principales
    private PanelHotel panelTitulo;
    
    /** The panel datos cliente. */
    private PanelDatosCliente panelDatosCliente;
    
    /** The panel datos habitacion. */
    private PanelDatosHabitacion panelDatosHabitacion;
    
    /** The panel con pestana. */
    private PanelConPestana panelConPestana;
    
    /** The panel botones. */
    private JPanel panelBotones;

    /** The dimension pantalla. */
    private Dimension dimensionPantalla;
    
    /** The guardar. */
    private JButton imprimir, nuevo, guardar;

    /**
     * Instantiates a new ventana dialogo alta reserva.
     *
     * @param parent the parent
     */
    public VentanaDialogoAltaReserva(JFrame parent) {
        super(parent, "Alta Reservas", true);
        configurarVentana();
        inicializarComponentes();
        setVisible(true);
    }

    /**
     * Configurar ventana.
     */
    // Configura la ventana principal
    private void configurarVentana() {
        setLayout(new BorderLayout());
        setResizable(true);
        setBackground(new Color(189, 224, 255));

        // Tamaño pantalla relativo y mínimo
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenBounds = ge.getMaximumWindowBounds();
        setBounds(screenBounds);
        setMinimumSize(new Dimension((int) (screenBounds.width / 1.5), screenBounds.height));

        // Logo personalizado
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.jpg")).getImage());
    }

    /**
     * Inicializar componentes.
     */
    // Inicializa componentes de la ventana
    private void inicializarComponentes() {
        panelTitulo = new PanelHotel();
        panelDatosCliente = new PanelDatosCliente();
        panelDatosHabitacion = new PanelDatosHabitacion(panelDatosCliente);
        panelConPestana = new PanelConPestana(panelDatosCliente, panelDatosHabitacion);

        JPanel panelCentral = crearPanelCentral();
        JPanel panelBotones = crearPanelBotones();

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Crear panel central.
     *
     * @return the j panel
     */
    // Crea y organiza el panel central
    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBorder(new EmptyBorder(2, 2, 2, 2));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.insets = new Insets(2, 2, 2, 2);

        constraints.gridy = 0;  
        constraints.weighty = 0.1; 
        panelCentral.add(panelTitulo, constraints);

        constraints.gridy = 1; 
        constraints.weighty = 0.2; 
        panelCentral.add(panelDatosCliente, constraints);

        constraints.gridy = 2; 
        constraints.weighty = 0.2;  
        panelCentral.add(panelDatosHabitacion, constraints);

        constraints.gridy = 3; 
        constraints.weighty = 0.5;  
        panelCentral.add(panelConPestana, constraints);

        return panelCentral;
    }

    /**
     * Crear panel botones.
     *
     * @return the j panel
     */
    // Crea el panel con botones inferiores
    private JPanel crearPanelBotones() {
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panelBotones.setBorder(new EmptyBorder(0, 20, 10, 20));

        imprimir = crearBoton("IMPRIMIR", "imprimir.png");
        nuevo = crearBoton("NUEVO", "nuevo.png");
        guardar = crearBoton("GUARDAR", "guardar.png");

        imprimir.addActionListener(this);
        nuevo.addActionListener(this);
        guardar.addActionListener(this);

        panelBotones.add(imprimir);
        panelBotones.add(nuevo);
        panelBotones.add(guardar);

        return panelBotones;
    }

    /**
     * Crear boton.
     *
     * @param texto the texto
     * @param nombreImagen the nombre imagen
     * @return the j button
     */
    // Crea un botón con icono y texto
    private JButton crearBoton(String texto, String nombreImagen) {
        JButton boton = new JButton(texto);
        dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/recursos/" + nombreImagen));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
            dimensionPantalla.width / 76, dimensionPantalla.height / 43, Image.SCALE_SMOOTH);

        boton.setIcon(new ImageIcon(imagenEscalada));
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        return boton;
    }

    /**
     * Action performed.
     *
     * @param e the e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imprimir) {
            if (panelDatosCliente.validarCampos() && panelDatosHabitacion.validarCamposHabitacion()) {
                panelConPestana.imprimirDatosCliente(panelDatosCliente);
                panelConPestana.imprimirDatosHabitacion(panelDatosHabitacion);
            }
        } else if (e.getSource() == nuevo) {
            panelDatosCliente.limpiarDatosCliente();
            panelDatosHabitacion.limpiarDatosHabitacion();
            panelConPestana.limpiarDatosImprimidos();
            panelDatosCliente.tfNombre.requestFocusInWindow();
        } else if (e.getSource() == guardar) {
            if (panelDatosCliente.validarCampos() && panelDatosHabitacion.validarCamposHabitacion()) {
                JOptionPane.showMessageDialog(this, "REGISTRO GUARDADO");
            }
        }
    }
}