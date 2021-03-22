package vista;




import javax.swing.JDialog;
import javax.swing.JPanel;




import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



import javax.swing.table.AbstractTableModel;


import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;

import modelo.RegistroOperacion;


import java.awt.Toolkit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class DialogoRegistroOperaciones extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoRegistroOperaciones dialog = new DialogoRegistroOperaciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoRegistroOperaciones() {
		setMinimumSize(new Dimension(635, 545));
		ButtonGroup tipoOperacion= new ButtonGroup();
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoRegistroOperaciones.class.getResource("/vistaRecursos/study (1).png")));
		setBounds(100, 100, 635, 545);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 432, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 253, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 140, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label = new JLabel(Inter.getInstance().getString("menu.RegistroOperacion.LimiteInferior"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		
		JLabel label_1 = new JLabel(Inter.getInstance().getString("menu.RegistroOperacion.LimiteSuperior"));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 0;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel(Inter.getInstance().getString("menu.RegistroOperacion.IntervalodeFechas"));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel_1.add(label_2, gbc_label_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 1;
		panel_1.add(dateChooser, gbc_dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser_1.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser_1.gridx = 2;
		gbc_dateChooser_1.gridy = 1;
		panel_1.add(dateChooser_1, gbc_dateChooser_1);
		
		JCheckBox chckbxCompras = new JCheckBox(Inter.getInstance().getString("menu.RegistroOperacion.Compras"));
		GridBagConstraints gbc_chckbxCompras = new GridBagConstraints();
		gbc_chckbxCompras.anchor = GridBagConstraints.EAST;
		gbc_chckbxCompras.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCompras.gridx = 0;
		gbc_chckbxCompras.gridy = 2;
		panel_1.add(chckbxCompras, gbc_chckbxCompras);
		
		
		JCheckBox chckbxProduccion = new JCheckBox(Inter.getInstance().getString("menu.RegistroOperacion.Produccion"));
		GridBagConstraints gbc_chckbxProduccion = new GridBagConstraints();
		gbc_chckbxProduccion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxProduccion.gridx = 1;
		gbc_chckbxProduccion.gridy = 2;
		panel_1.add(chckbxProduccion, gbc_chckbxProduccion);
		
		JCheckBox chckbxVentas = new JCheckBox(Inter.getInstance().getString("menu.RegistroOperacion.Ventas"));
		chckbxVentas.setSelected(true);
		GridBagConstraints gbc_chckbxVentas = new GridBagConstraints();
		gbc_chckbxVentas.anchor = GridBagConstraints.WEST;
		gbc_chckbxVentas.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxVentas.gridx = 2;
		gbc_chckbxVentas.gridy = 2;
		panel_1.add(chckbxVentas, gbc_chckbxVentas);
		
		tipoOperacion.add(chckbxVentas);
		tipoOperacion.add(chckbxProduccion);
		tipoOperacion.add(chckbxCompras);
		
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{82, 113, 15, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{10, 25, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton button = new JButton(Inter.getInstance().getString("menu.RegistroOperacion.LimpiarFiltros"));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dateChooser.setDate(null);
				dateChooser_1.setDate(null);
			
				chckbxCompras.setSelected(false);
				chckbxProduccion.setSelected(false);
				chckbxVentas.setSelected(false);
				table.setModel(new RegistroOperacionesTabla(ControladorIbiJuguetes.getUnicaInstancia().obtenerRegistroOperaciones(null,null,"")));

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		panel_2.add(button, gbc_button);
		
		JButton button_1 = new JButton(Inter.getInstance().getString("menu.RegistroOperacion.FiltrarRegistros"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String opcion ="";
				
				if(chckbxCompras.isSelected()) {
					opcion = "Compra";
				}
				
				if (chckbxProduccion.isSelected()) {
					opcion = "Producción";
				
				}
				
				if(chckbxVentas.isSelected()) {
					opcion = "Venta";
					
				}

				Date dateInferior = (Date) dateChooser.getDate();
				Date dateSuperior = (Date) dateChooser_1.getDate();

				// FECHAS
				if( dateInferior != null && dateSuperior != null  ) {

					if ( !dateInferior.after(dateSuperior) && !dateSuperior.before(dateInferior) ) {
						
					}
					else {
						JOptionPane.showMessageDialog(PanelAltaProducto.ventana, Inter.getInstance().getString("menu.RegistroOperacion.LimiteFechasIncorrecto"));
						dateChooser.setDate(null);
						dateChooser_1.setDate(null);
					}
				}
				
				table.setModel(new RegistroOperacionesTabla(ControladorIbiJuguetes.getUnicaInstancia().obtenerRegistroOperaciones(dateInferior,dateSuperior,opcion)));
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_1.gridx = 4;
		gbc_button_1.gridy = 1;
		panel_2.add(button_1, gbc_button_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(new RegistroOperacionesTabla(ControladorIbiJuguetes.getUnicaInstancia().obtenerRegistroOperaciones(null,null,"")));

	
	}

	
	
	
private class RegistroOperacionesTabla extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		
		
		private LinkedList<RegistroOperacion> operaciones;
		
		private String[] columnNames = {Inter.getInstance().getString("menu.RegistroOperacion.Operacion"),Inter.getInstance().getString("menu.RegistroOperacion.Fecha"),Inter.getInstance().getString("menu.RegistroOperacion.Articulo"),
				Inter.getInstance().getString("menu.RegistroOperacion.Proveedor_Cliente") ,Inter.getInstance().getString("menu.RegistroOperacion.Cantidad"), Inter.getInstance().getString("menu.RegistroOperacion.Precio")    };
		
		
		public RegistroOperacionesTabla(List<RegistroOperacion> listaOperaciones) {
			this.operaciones = new LinkedList<RegistroOperacion>(listaOperaciones);
		}
		
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public int getRowCount() {
			return operaciones.size();
		}
		public int getColumnCount() {
			return 5;
		}
		
		public Class<?> getColumnClass(int column) {
	        switch(column) {
	            case 0: return String.class;
	            case 1: return String.class;
	            case 2: return String.class;
	            case 3: return String.class;
	            case 4: return String.class;
	            case 5: return String.class;
	            default: return Object.class;

	        }
	    }
		

		public Object getValueAt(int row, int col) {
			String valor = "";

			RegistroOperacion registroOperacion = operaciones.get(row);
			
			switch (col) {
			
				case 0 : valor = registroOperacion.getOperacion();
				break;
				
				case 1 :
					valor = String.valueOf(registroOperacion.getFecha());
				break;
				
				case 2 : valor= registroOperacion.getArticulo();
				break;
				
				case 3 : valor = registroOperacion.getProveedorCliente();
							
				break;
				
				case 4 : valor = String.valueOf(registroOperacion.getCantidad());
				break;
				
				case 5 : valor = String.valueOf(registroOperacion.getPrecio());
				break;
				
			}
			
			return valor;
		}
		
		public void setValueAt(Object value, int row, int col) { }
		
	}	
	
	
	
	
	
	
}
