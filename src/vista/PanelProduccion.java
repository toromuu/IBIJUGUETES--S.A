package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;
import modelo.MateriaPrima;
import modelo.Produccion;
import modelo.ProductoFinal;

import java.awt.GridLayout;

public class PanelProduccion extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoCantidad;
	public static  JTable table;
	private JTable table_1;
	private JList<String>  listProductosFinalesDisponibles;
	private String[] nombreProductos;
	private static ProductoFinal productoSeleccionado;
	public static String cantidad;
	private List<ProductoFinal> productosAux;
	public static JLabel lblCosteTotal;
	public static JLabel labelarticuloNombre;
	public static JLabel lblPUMproductofinal;

	/**
	 * Create the panel.
	 */
	public PanelProduccion(JFrame v) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Inter.getInstance().getString("menu.produccion.lblConfigProduccion"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Inter.getInstance().getString("menu.produccion.lblProductoAProducir"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_2.add(scrollPane_1, gbc_scrollPane_1);
		
		listProductosFinalesDisponibles = generarJList(scrollPane_1);
		actualizarListEvent();
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, Inter.getInstance().getString("menu.produccion.lblMatNeces"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{2, 0};
		gbl_panel_4.rowHeights = new int[]{2, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_4.add(scrollPane, gbc_scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{5, 92, 0, 5, 0};
		gbl_panel_3.rowHeights = new int[]{5, 0, 5, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblCantidadAVender = new JLabel(Inter.getInstance().getString("menu.produccion.lblCantProd"));
		GridBagConstraints gbc_lblCantidadAVender = new GridBagConstraints();
		gbc_lblCantidadAVender.gridwidth = 2;
		gbc_lblCantidadAVender.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadAVender.gridx = 1;
		gbc_lblCantidadAVender.gridy = 0;
		panel_3.add(lblCantidadAVender, gbc_lblCantidadAVender);
		
		campoCantidad = new JTextField();
		GridBagConstraints gbc_campoCantidad = new GridBagConstraints();
		gbc_campoCantidad.gridwidth = 2;
		gbc_campoCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_campoCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCantidad.gridx = 1;
		gbc_campoCantidad.gridy = 1;
		panel_3.add(campoCantidad, gbc_campoCantidad);
		campoCantidad.setColumns(10);
		
		JButton btnProducir = new JButton(Inter.getInstance().getString("menu.produccion.btnProducir"));
		btnProducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!campoCantidad.getText().isEmpty() && productoSeleccionado!=null ){
					cantidad = campoCantidad.getText();
					DialogoConfirmarProduccion dialogo = new DialogoConfirmarProduccion();
					dialogo.setVisible(true);
				}else
					JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.produccion.textoCantidad"));
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_3.add(btnProducir, gbc_btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Inter.getInstance().getString("menu.produccion.lblUltimaProd"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{5, 0, 0, 5, 0};
		gbl_panel_1.rowHeights = new int[]{5, 0, 0, 0, 0, 0, 5, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblArticulo = new JLabel(Inter.getInstance().getString("menu.produccion.lbArticulo"));
		GridBagConstraints gbc_lblArticulo = new GridBagConstraints();
		gbc_lblArticulo.anchor = GridBagConstraints.EAST;
		gbc_lblArticulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticulo.gridx = 1;
		gbc_lblArticulo.gridy = 1;
		panel_1.add(lblArticulo, gbc_lblArticulo);
		
		labelarticuloNombre = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 1;
		panel_1.add(labelarticuloNombre, gbc_lblNewLabel_6);
				
		JLabel lblBeneficio = new JLabel(Inter.getInstance().getString("menu.produccion.lbMatEmpleados"));
		GridBagConstraints gbc_lblBeneficio = new GridBagConstraints();
		gbc_lblBeneficio.anchor = GridBagConstraints.EAST;
		gbc_lblBeneficio.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeneficio.gridx = 1;
		gbc_lblBeneficio.gridy = 2;
		panel_1.add(lblBeneficio, gbc_lblBeneficio);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 2;
		panel_1.add(scrollPane_2, gbc_scrollPane_2);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		
		JLabel lblNDeUnidades = new JLabel(Inter.getInstance().getString("menu.produccion.lblCosteFinalUnidad"));
		GridBagConstraints gbc_lblNDeUnidades = new GridBagConstraints();
		gbc_lblNDeUnidades.anchor = GridBagConstraints.EAST;
		gbc_lblNDeUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_lblNDeUnidades.gridx = 1;
		gbc_lblNDeUnidades.gridy = 4;
		panel_1.add(lblNDeUnidades, gbc_lblNDeUnidades);
		
		lblPUMproductofinal = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 4;
		panel_1.add(lblPUMproductofinal, gbc_lblNewLabel_5);
		
		JLabel lblNuevoStockDisponible = new JLabel(Inter.getInstance().getString("menu.produccion.lbCosteTotal"));
		GridBagConstraints gbc_lblNuevoStockDisponible = new GridBagConstraints();
		gbc_lblNuevoStockDisponible.anchor = GridBagConstraints.EAST;
		gbc_lblNuevoStockDisponible.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevoStockDisponible.gridx = 1;
		gbc_lblNuevoStockDisponible.gridy = 5;
		panel_1.add(lblNuevoStockDisponible, gbc_lblNuevoStockDisponible);
		
		lblCosteTotal = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 5;
		panel_1.add(lblCosteTotal, gbc_lblNewLabel_7);
		actualizarDatosUltimaProduccion();
	}
	
	
	
private JList<String> generarJList(JScrollPane scrollPane_1) {
		
		productosAux = ControladorIbiJuguetes.getUnicaInstancia().getProductosFinalesDisponibles();

		//PARA CREAR EL ARRAY PARA EL MODELO DEL JLIST
		nombreProductos = new String[productosAux.size()];
		int i = 0;
		synchronized (nombreProductos) {
			for (ProductoFinal producto : productosAux) {
				nombreProductos[i] = producto.getArticulo();
				i++;
			}
		}	
		
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
		
			private static final long serialVersionUID = 1L;
			String[] values = nombreProductos;
		
			public int getSize() {
				return values.length;
			}
		
			public String getElementAt(int index) {
				return values[index];
			}
		});
				
		scrollPane_1.setViewportView(list);
		return list;
		
	}
	
	
	private void actualizarListEvent() {
		
			listProductosFinalesDisponibles.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					productoSeleccionado = productosAux.get(listProductosFinalesDisponibles.getSelectedIndex());
					table_1.setModel(new DesgloseProductoFinalTabla(ControladorIbiJuguetes.getUnicaInstancia().getMateriasPrimasNecesarias(productoSeleccionado)));
				}
			});
			
	}

	
public static class DesgloseProductoFinalTabla extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		
		private LinkedHashMap<MateriaPrima,Integer> Materias;
		private ArrayList<MateriaPrima> claves;
		
		private String[] columnNames = {Inter.getInstance().getString("menurdboton.alta.matPrima"), Inter.getInstance().getString("menu.produccion.lbnecesaria"), Inter.getInstance().getString("menu.produccion.Stock")};
		
		
		public DesgloseProductoFinalTabla(Map<MateriaPrima, Integer> map) {
			this.Materias = new LinkedHashMap<>(map);
			this.claves = new ArrayList<MateriaPrima>(Materias.keySet());
		}
		
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public int getRowCount() {
			return Materias.size();
		}
		public int getColumnCount() {
			return 3;
		}
		
		public Class<?> getColumnClass(int column) {
	        switch(column) {
	            case 0: return String.class;
	            case 1: return Integer.class;
	            case 2: return Integer.class;
	            default: return Object.class;
	     
	        }
	    }
		
		public Object getValueAt(int row, int col) {
			String valor = "";

			MateriaPrima materia = claves.get(row);
			
			
			switch (col) {
				case 0 : valor = materia.getArticulo();
				break;
				
				case 1 :
					     valor = Materias.get(materia).toString();
						
				break;
				
				case 2 : 
						 
					     valor =  String.valueOf(materia.getStock());
						
				break;
			}
			
			return valor;
		}
		
		
		public void setValueAt(Object value, int row, int col) { }
		
	}



public static class UltimaProduccionTabla extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<MateriaPrima,Integer> materias;
	private ArrayList<MateriaPrima> claves;
	private Produccion ultimaproduccion;
	
	private String[] columnNames = {Inter.getInstance().getString("menurdboton.alta.matPrima"), Inter.getInstance().getString("menu.produccion.Used")};
	
	
	public UltimaProduccionTabla(Produccion ultimaproduccion, Map<MateriaPrima, Integer> map) {
		this.materias = new LinkedHashMap<>(map);
		this.claves = new ArrayList<MateriaPrima>(materias.keySet());
		this.ultimaproduccion = ultimaproduccion;
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public int getRowCount() {
		return materias.size();
	}
	public int getColumnCount() {
		return 2;
	}
	
	public Class<?> getColumnClass(int column) {
        switch(column) {
            case 0: return String.class;
            case 1: return Integer.class;
            default: return Object.class;
     
        }
    }
	
	public Object getValueAt(int row, int col) {
		String valor = "";
		MateriaPrima materia = claves.get(row);
		
		
		switch (col) {
			case 0 : valor = materia.getArticulo();
			break;
			
			case 1 : valor = String.valueOf(ultimaproduccion.getCantidad()*materias.get(materia));
			break;
		}
		
		return valor;
	}
	
	public void setValueAt(Object value, int row, int col) { }
	
}



public static ProductoFinal getProductoFinalSeleccionado() {
	return productoSeleccionado;
}

public static String getCantidad () {
	return cantidad;
}

public static void actualizarDatosUltimaProduccion() {
	Produccion ultimaproduccion = ControladorIbiJuguetes.getUnicaInstancia().getUltimaProduccion();
	
	if (ultimaproduccion != null) {
		ProductoFinal productoProducido = ultimaproduccion.getProductoFinal(); //No confundir con 
		table.setModel(new UltimaProduccionTabla(ultimaproduccion,  ControladorIbiJuguetes.getUnicaInstancia().getMateriasPrimasNecesarias( productoProducido) ));
		SetLabelarticuloNombre(productoProducido.getArticulo());
		SetLblPUMproductofinal(String.valueOf(ultimaproduccion.getCoste()));
		SetLblCosteTotal(String.valueOf(ultimaproduccion.getCosteTotal()));
	}
	
}






public static void SetLblCosteTotal(String texto) {
	lblCosteTotal.setText(texto);
}

public static void  SetLabelarticuloNombre(String texto) {
	labelarticuloNombre.setText(texto);
}

public static void SetLblPUMproductofinal(String texto) {
	lblPUMproductofinal.setText(texto);
}
	
}
