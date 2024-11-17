package swing_c_p02_rodriguezZuritaAlfredoJose;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class PanelConPestana.
 */
public class PanelConPestana extends JPanel implements ChangeListener {
    
    /** The panel datos cliente. */
    private JPanel panelDatosCliente;
    
    /** The panel datos habitacion. */
    private JPanel panelDatosHabitacion;
    
    /** The panel datos cliente oyente. */
    private PanelDatosCliente panelDatosClienteOyente;
    
    /** The panel datos habitacion oyente. */
    private PanelDatosHabitacion panelDatosHabitacionOyente;
    
    /** The tabbed pane. */
    private JTabbedPane tabbedPane;
    
    /** The informacion hotel. */
    private JTextArea informacionCliente, informacionHotel;
    
    /**
     * Instantiates a new panel con pestana.
     *
     * @param panelCliente the panel cliente
     * @param panelHabitacion the panel habitacion
     */
    public PanelConPestana(PanelDatosCliente panelCliente, PanelDatosHabitacion panelHabitacion) {
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        
        // Se registra como oyente a los paneles de datos
        panelDatosClienteOyente = panelCliente;
        panelDatosHabitacionOyente = panelHabitacion;
        panelDatosClienteOyente.addChangeListener(this); 
        panelDatosHabitacionOyente.addChangeListener(this);
        
        // Panel de datos del cliente
        panelDatosCliente = new JPanel();
        panelDatosCliente.setBackground(new Color(249, 230, 160));
        panelDatosCliente.setLayout(new BoxLayout(panelDatosCliente, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Datos del Cliente", panelDatosCliente);
        
        // Panel de datos de la habitación
        panelDatosHabitacion = new JPanel();
        panelDatosHabitacion.setBackground(new Color(249, 230, 160));
        panelDatosHabitacion.setLayout(new BoxLayout(panelDatosHabitacion, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Datos de la Habitación", panelDatosHabitacion);
        
        this.add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
     * Imprimir datos cliente.
     *
     * @param panelCliente the panel cliente
     */
    // Imprimir los datos del cliente
    public void imprimirDatosCliente(PanelDatosCliente panelCliente) {
        panelDatosCliente.removeAll(); // Limpiar datos previos
        informacionCliente = new JTextArea();
        configurarTexto(informacionCliente);

        informacionCliente.append("Nombre completo: " + panelCliente.tfNombre.getText() + " " + panelCliente.tfApellidos.getText() + "\n");
        informacionCliente.append("Teléfono: " + panelCliente.tfTelefono.getText() + "\n");
        informacionCliente.append("DNI: " + panelCliente.tfDNI.getText() + "\n");
        
        // Formatear fechas
        Date fechaEntrada = (Date) panelCliente.spFechaEntrada.getValue();
        Date fechaSalida = (Date) panelCliente.spFechaSalida.getValue();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTextoEntrada = formatoFecha.format(fechaEntrada);
        String fechaTextoSalida = formatoFecha.format(fechaSalida);
        
        // Imprimir fechas y días de estancia
        informacionCliente.append("Fecha de entrada: " + fechaTextoEntrada + " -- Fecha de salida: " + fechaTextoSalida + 
                " -- > Días de estancia: " + panelCliente.tfDiasEstancia.getText() + " días");
        
        panelDatosCliente.add(informacionCliente);
        panelDatosCliente.revalidate();
        panelDatosCliente.repaint();
    }

    /**
     * Imprimir datos habitacion.
     *
     * @param panelHabitacion the panel habitacion
     */
    // Imprimir los datos de la habitación
    public void imprimirDatosHabitacion(PanelDatosHabitacion panelHabitacion) {
        panelDatosHabitacion.removeAll(); // Limpiar datos previos
        informacionHotel = new JTextArea();
        configurarTexto(informacionHotel);

        informacionHotel.append("Tipo de habitación: " + panelHabitacion.roomTypeComboBox.getSelectedItem().toString() + "\n");
        informacionHotel.append("Número de habitaciones: " + panelHabitacion.roomsSpinner.getValue().toString() + "\n");
        informacionHotel.append("¿Con niños?: " + (panelHabitacion.childrenCheckBox.isSelected() ? "Sí" : "No"));
        
        // Si hay niños, agregar más detalles
        if (panelHabitacion.childrenCheckBox.isSelected()) {
            informacionHotel.append(" --> Edad de los niños: " + panelHabitacion.kidsSpinner.getValue().toString() + " -- ");
            informacionHotel.append("Extras: " + panelHabitacion.contenidoExtras.getText());
        }
        informacionHotel.append("\n" + panelHabitacion.precio.getText());

        panelDatosHabitacion.add(informacionHotel);
        panelDatosHabitacion.revalidate();
        panelDatosHabitacion.repaint();
    }
    
    /**
     * Limpiar datos imprimidos.
     */
    // Limpiar los datos impresos en ambos paneles
    public void limpiarDatosImprimidos() {
        panelDatosCliente.removeAll();
        panelDatosHabitacion.removeAll();
        panelDatosCliente.revalidate();
        panelDatosCliente.repaint();
    }

    /**
     * Configurar texto.
     *
     * @param textArea the text area
     */
    // Método común para configurar el estilo de texto de los JTextArea
    private void configurarTexto(JTextArea textArea) {
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textArea.setForeground(new Color(50, 50, 50));
        textArea.setBackground(new Color(249, 230, 160));
        LineBorder outerBorder = new LineBorder(new Color(200, 200, 200), 1);
        EmptyBorder innerBorder = new EmptyBorder(5, 10, 10, 10);
        textArea.setBorder(new CompoundBorder(outerBorder, innerBorder));
    }

    /**
     * State changed.
     *
     * @param e the e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == panelDatosClienteOyente || e.getSource() == panelDatosHabitacionOyente) {
            limpiarDatosImprimidos(); // Limpiar cuando los datos cambian
        }
    }
}