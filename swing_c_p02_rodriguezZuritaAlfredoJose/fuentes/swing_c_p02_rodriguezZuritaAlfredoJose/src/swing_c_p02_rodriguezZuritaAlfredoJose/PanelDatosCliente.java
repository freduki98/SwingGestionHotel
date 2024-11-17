/**
 * PanelDatosCliente.java
 * 13 nov 2024 12:54:00
 * @author Alfredo José Rodríguez Zurita
 */
package swing_c_p02_rodriguezZuritaAlfredoJose;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

/**
 * The Class PanelDatosCliente.
 */
public class PanelDatosCliente extends JPanel implements FocusListener, ChangeListener {

    /** The listeners. */
    private List<ChangeListener> listeners = new ArrayList<>();
    
    /** The layout. */
    private GridBagLayout layout;
    
    /** The constraints. */
    private GridBagConstraints constraints;

    /** The label dias estancia. */
    private JLabel labelNombre, labelApellidos, labelTelefono, labelDNI, labelFechaEntrada, labelFechaSalida, labelDiasEstancia;
    
    /** The tf dias estancia. */
    public JTextField tfNombre, tfApellidos, tfDiasEstancia;
    
    /** The tf DNI. */
    public JFormattedTextField tfTelefono, tfDNI;
    
    /** The sp fecha salida. */
    public JSpinner spFechaEntrada, spFechaSalida;

    /**
     * Instantiates a new panel datos cliente.
     */
    public PanelDatosCliente() {
        configurarPanel();
        inicializarComponentes();
        agregarComponentes();
    }

    /**
     * Configurar panel.
     */
    // Configura el diseño del panel
    private void configurarPanel() {
        layout = new GridBagLayout();
        setLayout(layout);

        TitledBorder titleBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(245, 245, 220), 2), "Datos del cliente");
        titleBorder.setTitleJustification(TitledBorder.LEFT);
        setBorder(titleBorder);
        setBackground(new Color(214, 167, 209));

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 5, 5, 5);
    }

    /**
     * Inicializar componentes.
     */
    // Inicializa los componentes
    private void inicializarComponentes() {
        labelNombre = new JLabel("Nombre:");
        tfNombre = new JTextField(20);
        tfNombre.addFocusListener(this);

        labelApellidos = new JLabel("Apellidos:");
        tfApellidos = new JTextField(20);
        tfApellidos.addFocusListener(this);

        labelTelefono = new JLabel("Teléfono:");
        tfTelefono = crearCampoFormateado("#########");
        tfTelefono.addFocusListener(this);

        labelDNI = new JLabel("DNI:");
        tfDNI = crearCampoFormateado("########U");
        tfDNI.addFocusListener(this);

        labelFechaEntrada = new JLabel("Fecha de Entrada:");
        spFechaEntrada = configurarSpinnerFecha(new SpinnerDateModel());
        spFechaEntrada.addChangeListener(this);

        labelFechaSalida = new JLabel("Fecha de Salida:");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        spFechaSalida = configurarSpinnerFecha(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH));
        spFechaSalida.addChangeListener(this);

        labelDiasEstancia = new JLabel("Número de Días:");
        tfDiasEstancia = new JTextField("1", 20);
        tfDiasEstancia.setEditable(false);
    }

    /**
     * Configurar spinner fecha.
     *
     * @param model the model
     * @return the j spinner
     */
    // Configura un JSpinner con formato de fecha
    private JSpinner configurarSpinnerFecha(SpinnerDateModel model) {
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        return spinner;
    }

    /**
     * Agregar componentes.
     */
    // Agrega los componentes al panel
    private void agregarComponentes() {
        agregarComponente(labelNombre, 0, 0, 1, 1);
        agregarComponente(tfNombre, 1, 0, 1, 1);
        agregarComponente(labelApellidos, 2, 0, 1, 1);
        agregarComponente(tfApellidos, 3, 0, 1, 1);
        agregarComponente(labelTelefono, 0, 1, 1, 1);
        agregarComponente(tfTelefono, 1, 1, 1, 1);
        agregarComponente(labelDNI, 2, 1, 1, 1);
        agregarComponente(tfDNI, 3, 1, 1, 1);
        agregarComponente(labelFechaEntrada, 0, 2, 1, 1);
        agregarComponente(spFechaEntrada, 1, 2, 1, 1);
        agregarComponente(labelFechaSalida, 2, 2, 1, 1);
        agregarComponente(spFechaSalida, 3, 2, 1, 1);
        agregarComponente(labelDiasEstancia, 0, 3, 1, 1);
        agregarComponente(tfDiasEstancia, 1, 3, 1, 1);
    }

    /**
     * Agregar componente.
     *
     * @param component the component
     * @param column the column
     * @param row the row
     * @param width the width
     * @param height the height
     */
    // Método auxiliar para agregar componentes al diseño
    private void agregarComponente(Component component, int column, int row, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.setConstraints(component, constraints);
        add(component);
    }

    /**
     * Crear campo formateado.
     *
     * @param mask the mask
     * @return the j formatted text field
     */
    // Crea un campo de texto formateado
    private JFormattedTextField crearCampoFormateado(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            JFormattedTextField field = new JFormattedTextField(formatter);
            field.setEditable(true);
            field.setFocusLostBehavior(JFormattedTextField.COMMIT);
            return field;
        } catch (ParseException e) {
            throw new RuntimeException("Error al crear la máscara", e);
        }
    }

    /**
     * Gets the calculo dias estancia.
     *
     * @return the calculo dias estancia
     */
    // Calcula los días de estancia entre las fechas seleccionadas
    public int getCalculoDiasEstancia() {
        Date startDate = (Date) spFechaEntrada.getValue();
        Date endDate = (Date) spFechaSalida.getValue();
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return (int) Math.ceil((double) diffInMillis / (1000 * 60 * 60 * 24));
    }

    /**
     * Limpiar datos cliente.
     */
    // Limpia los campos del formulario
    public void limpiarDatosCliente() {
        tfNombre.setText("");
        tfApellidos.setText("");
        tfTelefono.setText("");
        tfDNI.setText("");
        spFechaEntrada.setValue(new Date());
        Date fechaActual = new Date();
        Date fechaEntrada = (Date) spFechaEntrada.getValue();
        Calendar calendar = Calendar.getInstance();
        ajustarFechaSalida(calendar, fechaEntrada);
        tfDiasEstancia.setText("1");
    }

    /**
     * Validar campos.
     *
     * @return true, if successful
     */
    // Valida los campos obligatorios
    public boolean validarCampos() {
        if (tfNombre.getText().trim().isEmpty()) {
            mostrarError("El campo 'Nombre' no puede estar vacío");
            return false;
        }
        if (tfApellidos.getText().trim().isEmpty()) {
            mostrarError("El campo 'Apellidos' no puede estar vacío");
            return false;
        }
        if (!tfTelefono.getText().trim().matches("\\d{9}")) {
            mostrarError("El campo 'Teléfono' debe tener 9 dígitos");
            return false;
        }
        if (!tfDNI.getText().trim().matches("\\d{8}[A-Za-z]")) {
            mostrarError("El campo 'DNI' debe tener el formato correcto (8 dígitos + 1 letra)");
            return false;
        }
        return true;
    }

    /**
     * Mostrar error.
     *
     * @param mensaje the mensaje
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Notify change listeners.
     */
    // Notifica a los listeners sobre un cambio
    private void notifyChangeListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

    /**
     * Adds the change listener.
     *
     * @param listener the listener
     */
    // Agrega un listener para cambios
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * State changed.
     *
     * @param e the e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        notifyChangeListeners();
        tfDiasEstancia.setText(String.valueOf(getCalculoDiasEstancia()));

        Date fechaEntrada = (Date) spFechaEntrada.getValue();
        Date fechaSalida = (Date) spFechaSalida.getValue();
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();

        if (e.getSource() == spFechaEntrada) {
            if (fechaEntrada.before(fechaActual)) spFechaEntrada.setValue(fechaActual);
            if (!fechaEntrada.before(fechaSalida)) ajustarFechaSalida(calendar, fechaEntrada);
        } else if (e.getSource() == spFechaSalida && !fechaSalida.after(fechaEntrada)) {
            ajustarFechaSalida(calendar, fechaEntrada);
        }
    }

    /**
     * Ajustar fecha salida.
     *
     * @param calendar the calendar
     * @param fechaEntrada the fecha entrada
     */
    private void ajustarFechaSalida(Calendar calendar, Date fechaEntrada) {
        calendar.setTime(fechaEntrada);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        spFechaSalida.setValue(calendar.getTime());
    }

    /**
     * Focus gained.
     *
     * @param e the e
     */
    @Override
    public void focusGained(FocusEvent e) {
        notifyChangeListeners();
    }

    /**
     * Focus lost.
     *
     * @param e the e
     */
    @Override
    public void focusLost(FocusEvent e) {
    }
}

