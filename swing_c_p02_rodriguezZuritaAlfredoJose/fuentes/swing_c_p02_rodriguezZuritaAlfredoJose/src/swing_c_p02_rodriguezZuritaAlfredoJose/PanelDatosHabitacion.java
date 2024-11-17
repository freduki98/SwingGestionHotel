/**
 * PanelDatosHabitacion.java
 * 13 nov 2024 12:54:20
 * @author Alfredo José Rodríguez Zurita
 */
package swing_c_p02_rodriguezZuritaAlfredoJose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Class PanelDatosHabitacion.
 */
public class PanelDatosHabitacion extends JPanel implements ChangeListener, ActionListener {

    /** The listeners. */
    // Variables para almacenar el estado del panel y los listeners
    private List<ChangeListener> listeners = new ArrayList<>();
    
    /** The panel datos cliente. */
    private PanelDatosCliente panelDatosCliente;
    
    /** The room type combo box. */
    // Componentes de la interfaz de usuario
    public JComboBox<String> roomTypeComboBox;
    
    /** The kids spinner. */
    public JSpinner roomsSpinner, kidsSpinner;
    
    /** The children check box. */
    public JCheckBox childrenCheckBox;
    
    /** The precio. */
    public JLabel contenidoExtras, edad, precio;
    
    /** The extras. */
    public JTextField extras;
    
    /** The panel imagenes. */
    private JPanel panelNinos, panelAdultos, panelPresupuesto, panelPersonas, panelImagenes;
    
    /** The adquirir presupuesto. */
    private JButton adquirirPresupuesto;
    
    /** The Constant valorSimple. */
    // Constantes para valores de habitaciones y extras
    public static final int valorSimple = 50;
    
    /** The Constant valorDoble. */
    public static final int valorDoble = 75;
    
    /** The Constant valorSuite. */
    public static final int valorSuite = 125;
    
    /** The Constant valorCunaCamaSup. */
    public static final int valorCunaCamaSup = 20;

    /** The valor extras. */
    // Variables internas para manejar el presupuesto y la habitación seleccionada
    private int valorExtras = 0;
    
    /** The valor habitacion seleccionada. */
    private int valorHabitacionSeleccionada = valorSimple;

    /** The pintura 3. */
    // Imágenes de las habitaciones
    private JLabel pintura1, pintura2, pintura3;

    /**
     * Instantiates a new panel datos habitacion.
     *
     * @param panelCli the panel cli
     */
    // Constructor del panel
    public PanelDatosHabitacion(PanelDatosCliente panelCli) {
        panelDatosCliente = panelCli;
        
        this.setLayout(new BorderLayout());
        this.setBorder(new TitledBorder(new LineBorder(new Color(245,245,220), 2), 
                "Detalles de la Habitación", TitledBorder.LEFT, TitledBorder.TOP));
        this.setBackground(new Color(167,216,210));
        
        panelDatosCliente.addChangeListener(this); // Registrar listener del panel cliente

        // Inicialización de los componentes de la interfaz
        inicializarComponentes();
        
        // Añadir los paneles al panel principal
        this.add(panelPersonas, BorderLayout.NORTH);
        this.add(panelImagenes, BorderLayout.CENTER);
        this.add(crearPanelInferior(), BorderLayout.SOUTH);

        this.setVisible(true);
    }
    
    /**
     * Inicializar componentes.
     */
    // Método para inicializar los componentes
    private void inicializarComponentes() {
        Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        // Panel de adultos
        panelAdultos = crearPanelAdultos();
        
        // Panel de niños
        panelNinos = crearPanelNinos();
        
        // Panel para presupuesto
        panelPresupuesto = crearPanelPresupuesto();
        
        // Panel de personas (adultos + niños)
        panelPersonas = new JPanel(new GridLayout(2, 1));
        panelPersonas.setOpaque(false);
        panelPersonas.add(panelAdultos);
        panelPersonas.add(panelNinos);

        // Panel de imágenes
        panelImagenes = crearPanelImagenes(dimensionPantalla);
    }
    
    /**
     * Crear panel adultos.
     *
     * @return the j panel
     */
    // Crear panel de adultos
    private JPanel crearPanelAdultos() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        
        JLabel habitacion = new JLabel("Tipo de habitación:");
        roomTypeComboBox = new JComboBox<>(new String[] {"Individual", "Doble", "Suite"});
        roomTypeComboBox.addActionListener(this);
        
        JLabel numHab = new JLabel("Número de habitaciones:");
        roomsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        roomsSpinner.addChangeListener(this);
        
        JLabel ninos = new JLabel("¿Con niños?");
        childrenCheckBox = new JCheckBox();
        childrenCheckBox.addActionListener(this);
        
        panel.add(habitacion);
        panel.add(roomTypeComboBox);
        panel.add(numHab);
        panel.add(roomsSpinner);
        panel.add(ninos);
        panel.add(childrenCheckBox);
        
        return panel;
    }
    
    /**
     * Crear panel ninos.
     *
     * @return the j panel
     */
    // Crear panel de niños
    private JPanel crearPanelNinos() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        
        edad = new JLabel("Edad de niños:");
        kidsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 14, 1));
        kidsSpinner.setValue(0);
        kidsSpinner.setEnabled(false);
        kidsSpinner.addChangeListener(this);
        
        extras = new JTextField("EXTRAS");
        extras.setBackground(new Color(0,200,0));
        extras.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1)));
        extras.setFocusable(false);
        extras.setEditable(false);
        
        contenidoExtras = new JLabel(" CUNA");
        contenidoExtras.setVisible(false);
        
        // Focus listener para mostrar/ocultar contenido extras
        extras.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                contenidoExtras.setVisible(true);
            }
            @Override
            public void focusLost(FocusEvent e) {
                contenidoExtras.setVisible(false);
            }
        });
        
        panel.add(edad);
        panel.add(kidsSpinner);
        panel.add(extras);
        panel.add(contenidoExtras);
        
        return panel;
    }
    
    /**
     * Crear panel presupuesto.
     *
     * @return the j panel
     */
    // Crear panel de presupuesto
    private JPanel crearPanelPresupuesto() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setOpaque(false);
        
        adquirirPresupuesto = new JButton("Adquirir presupuesto");
        adquirirPresupuesto.addActionListener(this);
        
        precio = new JLabel("Precio total: ");
        precio.setVisible(false);
        
        panel.add(adquirirPresupuesto);
        panel.add(precio);
        
        return panel;
    }
    
    /**
     * Crear panel imagenes.
     *
     * @param dimensionPantalla the dimension pantalla
     * @return the j panel
     */
    // Crear panel de imágenes
    private JPanel crearPanelImagenes(Dimension dimensionPantalla) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        
        Image imagen1 = new ImageIcon(getClass().getResource("/recursos/suiteHotel.jpg")).getImage();
        pintura1 = new JLabel(new ImageIcon(imagen1.getScaledInstance(dimensionPantalla.width / 7, dimensionPantalla.width / 11, Image.SCALE_SMOOTH)));
        
        Image imagen2 = new ImageIcon(getClass().getResource("/recursos/dobleHotel.jpg")).getImage();
        pintura2 = new JLabel(new ImageIcon(imagen2.getScaledInstance(dimensionPantalla.width / 7, dimensionPantalla.width / 11, Image.SCALE_SMOOTH)));
        
        Image imagen3 = new ImageIcon(getClass().getResource("/recursos/individualHotel.jpg")).getImage();
        pintura3 = new JLabel(new ImageIcon(imagen3.getScaledInstance(dimensionPantalla.width / 7, dimensionPantalla.width / 11, Image.SCALE_SMOOTH)));
        
        pintura1.setVisible(false);
        pintura2.setVisible(false);
        pintura3.setVisible(true);
        
        panel.add(pintura1);
        panel.add(pintura2);
        panel.add(pintura3);
        
        return panel;
    }
    
    /**
     * Crear panel inferior.
     *
     * @return the j panel
     */
    // Crear panel inferior para el presupuesto
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(panelPresupuesto);
        return panel;
    }

    /**
     * State changed.
     *
     * @param e the e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        // Actualización de precio y otros datos cuando cambian los valores
        if (e.getSource() == roomsSpinner || e.getSource() == kidsSpinner || e.getSource() instanceof PanelDatosCliente) {
            notifyChangeListeners();
            precio.setText("Total: " + obtenerPresupuesto() + " €");
        }
    }

    /**
     * Action performed.
     *
     * @param e the e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	notifyChangeListeners();
        // Acciones relacionadas con los cambios en la interfaz
        if (e.getSource() == adquirirPresupuesto) {
            precio.setVisible(true);
            precio.setText("Total: " + obtenerPresupuesto() + " €");
        } else if (e.getSource() == roomTypeComboBox) {
            actualizarTipoHabitacion();
        } else if (e.getSource() == childrenCheckBox) {
            actualizarOpcionesNinos();
        }
    }
    
    /**
     * Actualizar tipo habitacion.
     */
    // Actualizar la habitación seleccionada y la imagen correspondiente
    private void actualizarTipoHabitacion() {
        String tipoHabitacion = (String) roomTypeComboBox.getSelectedItem();
        switch (tipoHabitacion) {
            case "Individual":
                valorHabitacionSeleccionada = valorSimple;
                pintura1.setVisible(false);
                pintura2.setVisible(false);
                pintura3.setVisible(true);
                break;
            case "Doble":
                valorHabitacionSeleccionada = valorDoble;
                pintura1.setVisible(false);
                pintura2.setVisible(true);
                pintura3.setVisible(false);
                break;
            case "Suite":
                valorHabitacionSeleccionada = valorSuite;
                pintura1.setVisible(true);
                pintura2.setVisible(false);
                pintura3.setVisible(false);
                break;
        }
        precio.setText("Total: " + obtenerPresupuesto() + " €");
    }

    /**
     * Actualizar opciones ninos.
     */
    // Actualizar opciones si hay niños
    private void actualizarOpcionesNinos() {
        if (childrenCheckBox.isSelected()) {
            extras.setFocusable(true);
            kidsSpinner.setEnabled(true);
            contenidoExtras.setText(" CUNA");
            valorExtras = 20;
        } else {
            extras.setFocusable(false);
            contenidoExtras.setVisible(false);
            kidsSpinner.setValue(0);
            kidsSpinner.setEnabled(false);
            valorExtras = 0;
        }
        precio.setText("Total: " + obtenerPresupuesto() + " €");
    }

    /**
     * Limpiar datos habitacion.
     */
    // Limpiar los datos del panel de habitación
    public void limpiarDatosHabitacion() {
        roomTypeComboBox.setSelectedIndex(0);
        roomsSpinner.setValue(0);
        childrenCheckBox.setSelected(false);
        kidsSpinner.setValue(0); 
        kidsSpinner.setEnabled(false);
        valorExtras = 0;
        extras.setFocusable(false);
        contenidoExtras.setText(" CUNA");
        
        precio.setVisible(false);
        precio.setText("Precio total: ");
        pintura1.setVisible(false);
        pintura2.setVisible(false);
        pintura3.setVisible(true);
    }
    
    /**
     * Obtener presupuesto.
     *
     * @return the int
     */
    // Calcular el presupuesto basado en los datos actuales
    private int obtenerPresupuesto() {
        return (valorHabitacionSeleccionada * panelDatosCliente.getCalculoDiasEstancia()) * (int) roomsSpinner.getValue() + valorExtras;
    }

    /**
     * Validar campos habitacion.
     *
     * @return true, if successful
     */
    // Validar que el número de habitaciones sea mayor a 0
    public boolean validarCamposHabitacion() {
        int numeroHabitaciones = (int) roomsSpinner.getValue();
        if (numeroHabitaciones <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos 1 habitación", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Adds the change listener.
     *
     * @param listener the listener
     */
    // Registrar un listener para los cambios
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Notify change listeners.
     */
    // Notificar a los listeners registrados sobre los cambios
    private void notifyChangeListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }
}