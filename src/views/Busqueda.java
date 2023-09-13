package views;

import controller.HuespedController;
import controller.PersonalController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import java.awt.Toolkit;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;

	private ReservaController reservaController;

	private HuespedController huespedController;

	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()){
					List<Reserva> reservaciones = reservaController.buscar(Integer.valueOf(txtBuscar.getText()));
					cargarTabla(modelo, reservaciones);
				}
				else{
					List<Huesped> huespedes = huespedController.buscar(txtBuscar.getText());
					cargarTablaH(modeloHuesped, huespedes);
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.isShowing()){
					editarReservacion(tbReservas);
				}
				else{
					editarHuesped(tbHuespedes);
				}
			}
		});

		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.isShowing()){
					eliminarReservacion(tbReservas);
					limpiarTabla(modelo);
				}
				else{

				}
			}
		});
	}

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}

	private void cargarTabla(DefaultTableModel tabla, List<Reserva> listaObjetos){
		limpiarTabla(tabla);
		if(listaObjetos.isEmpty()){
			JOptionPane.showMessageDialog(null, "No existe reservacion con ese numero de reservacion");
		}
		else {
			listaObjetos.forEach(reservacion -> tabla.addRow(new Object[]{reservacion.getId(), reservacion.getFecha_entrada(),
					reservacion.getFecha_salida(), reservacion.getValor(), reservacion.getForma_de_pago()}));
		}
	}

	private void cargarTablaH(DefaultTableModel tabla, List<Huesped> listaObjetos){
		limpiarTabla(tabla);
		if(listaObjetos.isEmpty()){
			JOptionPane.showMessageDialog(null, "No existe ningun huesped con ese apellido");
		}
		else {
			listaObjetos.forEach(huesped -> tabla.addRow(new Object[]{huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFecha_de_nacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getId_reserva()}));
		}
	}

	private void limpiarTabla(DefaultTableModel tabla){
		tabla.getDataVector().clear();
	}

	private void editarReservacion(JTable tabla){
		if(tieneFilaElegida(tabla)){
			JOptionPane.showMessageDialog(null,"Por favor, elije una reservacion");
		}
		else{
			Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
						Date fecha_entrada = Date.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 1).toString());
						Date fecha_salida = Date.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 2).toString());
						Integer valor = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());
						String forma_de_pago = (String) modelo.getValueAt(tabla.getSelectedRow(), 4);

						int filasModificadas;

						filasModificadas = this.reservaController.editar(id, fecha_entrada, fecha_salida, valor, forma_de_pago);

						JOptionPane.showMessageDialog(this, String.format("%d Reservacion modificada con exito", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una reservacion"));
		}
	}

	private void editarHuesped(JTable tabla){
		if(tieneFilaElegida(tabla)){
			JOptionPane.showMessageDialog(null,"Por favor, elije un huesped");
		}
		else{
			Optional.ofNullable(modeloHuesped.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloHuesped.getValueAt(tabla.getSelectedRow(), 0).toString());
						String nombre = (String) modeloHuesped.getValueAt(tabla.getSelectedRow(), 1);
						String apellido = (String) modeloHuesped.getValueAt(tabla.getSelectedRow(), 2);
						Date fecha_de_nacimiento = Date.valueOf(modeloHuesped.getValueAt(tabla.getSelectedRow(), 3).toString());
						String nacionalidad = (String) modeloHuesped.getValueAt(tabla.getSelectedRow(), 4);
						String telefono = (String) modeloHuesped.getValueAt(tabla.getSelectedRow(), 5);
						Integer id_reserva = Integer.valueOf(modeloHuesped.getValueAt(tabla.getSelectedRow(), 6).toString());

						int filasModificadas;

						filasModificadas = this.huespedController.editar(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva);

						JOptionPane.showMessageDialog(this, String.format("%d Huesped modificado con exito", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un huesped"));
		}
	}

	private void eliminarReservacion(JTable tabla) {
		if (tieneFilaElegida(tabla)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije una reservacion");
			return;
		}
		Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
					int cantidadEliminada;

					cantidadEliminada = this.reservaController.eliminar(id);

					modelo.removeRow(tabla.getSelectedRow());

					JOptionPane.showMessageDialog(this, cantidadEliminada + " Reservacion eliminada con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una reservacion"));
	}


	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
