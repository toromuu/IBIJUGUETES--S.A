package vista;

import javax.swing.JPanel;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;



import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;
import modelo.ProductoFinal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;

import javax.swing.JButton;

public class PanelVentas extends JPanel {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTextField textCantidad;
	private JTextField textCliente;
	private String[] nombreProductos;
	private static ProductoFinal productoSeleccionado;
	public static String cantidad;
	private List<ProductoFinal> productosAux;
	private JComboBox<String> comboBoxProductosFinalesDisponibles;
	
	private int stock = 0;
	private double pum = 0.0;
	private String productoName = "";
	private double precioTotal;
	private int unidadesVender =0;
	private int margen =0;
	private int nuevoStock =0;
	
	private JLabel lblstockText;
	private JLabel lblPumText;
	private JLabel lblPrecioTotal;
	private JLabel lblUnidadesVender;
	private JLabel lblProductoName;
	private JLabel lblMargen;
	private JLabel lblNuevoStockDisponible;

	private JLabel lblStockText;
	

	/**
	 * Create the panel.
	 */
	public PanelVentas(JFrame v) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, Inter.getInstance().getString("menu.ventas.lblConfigVenta"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{271, 0, 0};
		gbl_panel.rowHeights = new int[]{53, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Inter.getInstance().getString("menu.ventas.lblProdFinStock"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{31, 0};
		gbl_panel_2.rowHeights = new int[]{22, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		comboBoxProductosFinalesDisponibles= generarJComboBox();
		actualizarComboBoxEvent();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel_2.add(comboBoxProductosFinalesDisponibles, gbc_comboBox);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{5, 92, 0, 5, 0};
		gbl_panel_3.rowHeights = new int[]{5, 16, 0, 0, 0, 5, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblStockDisponible = new JLabel(Inter.getInstance().getString("menu.ventas.lblStockDisponible"));
		GridBagConstraints gbc_lblStockDisponible = new GridBagConstraints();
		gbc_lblStockDisponible.anchor = GridBagConstraints.EAST;
		gbc_lblStockDisponible.insets = new Insets(0, 0, 5, 5);
		gbc_lblStockDisponible.gridx = 1;
		gbc_lblStockDisponible.gridy = 1;
		panel_3.add(lblStockDisponible, gbc_lblStockDisponible);
		
		lblstockText = new JLabel(String.valueOf(stock));
		GridBagConstraints gbc_lblstockText = new GridBagConstraints();
		gbc_lblstockText.anchor = GridBagConstraints.WEST;
		gbc_lblstockText.insets = new Insets(0, 0, 5, 5);
		gbc_lblstockText.gridx = 2;
		gbc_lblstockText.gridy = 1;
		panel_3.add(lblstockText, gbc_lblstockText);
		
		JLabel lblPum = new JLabel(Inter.getInstance().getString("menu.ventas.lblPUM"));
		GridBagConstraints gbc_lblPum = new GridBagConstraints();
		gbc_lblPum.anchor = GridBagConstraints.EAST;
		gbc_lblPum.insets = new Insets(0, 0, 5, 5);
		gbc_lblPum.gridx = 1;
		gbc_lblPum.gridy = 2;
		panel_3.add(lblPum, gbc_lblPum);
		
		lblPumText = new JLabel(String.valueOf(pum));
		GridBagConstraints gbc_lblPumText = new GridBagConstraints();
		gbc_lblPumText.anchor = GridBagConstraints.WEST;
		gbc_lblPumText.insets = new Insets(0, 0, 5, 5);
		gbc_lblPumText.gridx = 2;
		gbc_lblPumText.gridy = 2;
		panel_3.add(lblPumText, gbc_lblPumText);
		
		JLabel lblCantidadAVender = new JLabel(Inter.getInstance().getString("menu.ventas.lbCantAVender"));
		GridBagConstraints gbc_lblCantidadAVender = new GridBagConstraints();
		gbc_lblCantidadAVender.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadAVender.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadAVender.gridx = 1;
		gbc_lblCantidadAVender.gridy = 3;
		panel_3.add(lblCantidadAVender, gbc_lblCantidadAVender);
		
		textCantidad = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel_3.add(textCantidad, gbc_textField);
		textCantidad.setColumns(10);
		
		JLabel lblCliente = new JLabel(Inter.getInstance().getString("menu.ventas.lbCliente"));
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 1;
		gbc_lblCliente.gridy = 4;
		panel_3.add(lblCliente, gbc_lblCliente);
		
		textCliente = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		panel_3.add(textCliente, gbc_textField_1);
		textCliente.setColumns(10);
		
		JButton btnEstimar = new JButton(Inter.getInstance().getString("menu.ventas.btnEstimar"));
		btnEstimar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				productoSeleccionado = productosAux.get(comboBoxProductosFinalesDisponibles.getSelectedIndex());
				stock = productoSeleccionado.getStock();
				productoName = productoSeleccionado.getArticulo();
				margen = productoSeleccionado.getMargen(); 
				if (!textCantidad.getText().equals("")) {
					unidadesVender =  Integer.parseInt(textCantidad.getText());
				}
				
				precioTotal = ControladorIbiJuguetes.getUnicaInstancia().obtenerPrecioVenta(unidadesVender, productoSeleccionado);
				setLblProductoName(String.valueOf(productoName));
				
				int nuevoStock = stock - unidadesVender;
				
				setLblPrecioTotal(String.valueOf(precioTotal));
				setLblUnidadesVender(String.valueOf(unidadesVender));
				setLblstockText(String.valueOf(stock));
				setLblMargen(String.valueOf(margen)+" %");
				setLblNuevoStockDisponible(String.valueOf(nuevoStock));				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		panel_3.add(btnEstimar, gbc_btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, Inter.getInstance().getString("menu.ventas.lblInfo"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{5, 0, 0, 5, 0};
		gbl_panel_1.rowHeights = new int[]{5, 0, 0, 0, 0, 0, 5, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblArticulo = new JLabel(Inter.getInstance().getString("menu.produccion.lbArticulo"));
		GridBagConstraints gbc_lblArticulo = new GridBagConstraints();
		gbc_lblArticulo.anchor = GridBagConstraints.EAST;
		gbc_lblArticulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticulo.gridx = 1;
		gbc_lblArticulo.gridy = 1;
		panel_1.add(lblArticulo, gbc_lblArticulo);
		
		lblProductoName = new JLabel(productoName);
		GridBagConstraints gbc_lblProductoName = new GridBagConstraints();
		gbc_lblProductoName.anchor = GridBagConstraints.WEST;
		gbc_lblProductoName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductoName.gridx = 2;
		gbc_lblProductoName.gridy = 1;
		panel_1.add(lblProductoName, gbc_lblProductoName);
		
		JLabel lblNewLabel_2 = new JLabel(Inter.getInstance().getString("menu.ventas.lblPrecioTotal"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblPrecioTotal = new JLabel(String.valueOf(precioTotal));
		GridBagConstraints gbc_lblPrecioTotal = new GridBagConstraints();
		gbc_lblPrecioTotal.anchor = GridBagConstraints.WEST;
		gbc_lblPrecioTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioTotal.gridx = 2;
		gbc_lblPrecioTotal.gridy = 2;
		panel_1.add(lblPrecioTotal, gbc_lblPrecioTotal);
		
		JLabel lblBeneficio = new JLabel(Inter.getInstance().getString("menu.ventas.lblBeneficio"));
		GridBagConstraints gbc_lblBeneficio = new GridBagConstraints();
		gbc_lblBeneficio.anchor = GridBagConstraints.EAST;
		gbc_lblBeneficio.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeneficio.gridx = 1;
		gbc_lblBeneficio.gridy = 3;
		panel_1.add(lblBeneficio, gbc_lblBeneficio);
		
		lblMargen = new JLabel(String.valueOf(margen));
		GridBagConstraints gbc_lblMargen = new GridBagConstraints();
		gbc_lblMargen.anchor = GridBagConstraints.WEST;
		gbc_lblMargen.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargen.gridx = 2;
		gbc_lblMargen.gridy = 3;
		panel_1.add(lblMargen, gbc_lblMargen);
		
		JLabel lblNDeUnidades = new JLabel(Inter.getInstance().getString("menu.ventas.lblNDeUnidades"));
		GridBagConstraints gbc_lblNDeUnidades = new GridBagConstraints();
		gbc_lblNDeUnidades.anchor = GridBagConstraints.EAST;
		gbc_lblNDeUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_lblNDeUnidades.gridx = 1;
		gbc_lblNDeUnidades.gridy = 4;
		panel_1.add(lblNDeUnidades, gbc_lblNDeUnidades);
		
		lblUnidadesVender = new JLabel(String.valueOf(unidadesVender));
		GridBagConstraints gbc_lblUnidadesVender = new GridBagConstraints();
		gbc_lblUnidadesVender.anchor = GridBagConstraints.WEST;
		gbc_lblUnidadesVender.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidadesVender.gridx = 2;
		gbc_lblUnidadesVender.gridy = 4;
		panel_1.add(lblUnidadesVender, gbc_lblUnidadesVender);
		
		lblNuevoStockDisponible = new JLabel(Inter.getInstance().getString("menu.ventas.lblNuevoStockDisponible"));
		GridBagConstraints gbc_lblNuevoStockDisponible = new GridBagConstraints();
		gbc_lblNuevoStockDisponible.anchor = GridBagConstraints.EAST;
		gbc_lblNuevoStockDisponible.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevoStockDisponible.gridx = 1;
		gbc_lblNuevoStockDisponible.gridy = 5;
		panel_1.add(lblNuevoStockDisponible, gbc_lblNuevoStockDisponible);
		
		lblStockText = new JLabel(String.valueOf(nuevoStock));
		GridBagConstraints gbc_lblStockText = new GridBagConstraints();
		gbc_lblStockText.anchor = GridBagConstraints.WEST;
		gbc_lblStockText.insets = new Insets(0, 0, 5, 5);
		gbc_lblStockText.gridx = 2;
		gbc_lblStockText.gridy = 5;
		panel_1.add(lblStockText, gbc_lblStockText);
		
		JButton btnRegistrar = new JButton(Inter.getInstance().getString("menu.ventas.btnRegistrar"));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 3;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 6;
		panel_1.add(btnRegistrar, gbc_btnNewButton_1);
		

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((productoSeleccionado!=null)&&(!textCantidad.getText().isEmpty())&&(!textCliente.getText().isEmpty())){
					if ( !ControladorIbiJuguetes.getUnicaInstancia().registrarVenta(textCantidad.getText(),textCliente.getText(), productoSeleccionado) ) {
							JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.ventas.textoVentaError"));
					}else {
							JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.ventas.textoVentaOK"));
							ControladorVista.actualizarSaldo();
							setLblstockText(String.valueOf(stock));
							
							
					} 
				}
				else{
					JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.ventas.textoCamposNecesarios"));
				}
				
			}
		});
	
	}	
	
private JComboBox<String> generarJComboBox() {
		
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
	
		return new JComboBox<String>(nombreProductos);
	}
	

	private void actualizarComboBoxEvent() {
		
		comboBoxProductosFinalesDisponibles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productoSeleccionado = productosAux.get(comboBoxProductosFinalesDisponibles.getSelectedIndex());
				stock = productoSeleccionado.getStock();
				pum =0;
				setLblstockText(String.valueOf(stock));
				setLblPumText(String.valueOf(pum));
			}
		});
			
	}


	public void setLblPrecioTotal(String texto) {
		this.lblPrecioTotal.setText(texto);
	}


	public void setLblUnidadesVender(String texto) {
		this.lblUnidadesVender.setText(texto);
	}


	public void setLblProductoName(String texto) {
		this.lblProductoName.setText(texto);
	}
	
	
	public void setLblstockText(String texto) {
		this.lblstockText.setText(texto);
	}


	public void setLblPumText(String texto) {
		this.lblPumText.setText(texto);
	}

	public void setLblMargen(String texto) {
		this.lblMargen.setText(texto);
	}

	public void setLblNuevoStockDisponible(String texto) {
		this.lblStockText.setText(texto);
	}
	
	


}
