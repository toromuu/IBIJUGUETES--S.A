package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.table.AbstractTableModel;

import com.lowagie.text.DocumentException;

import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;
import modelo.Articulo;
import modelo.MateriaPrima;




import javax.swing.BoxLayout;


public class PanelGestionInventario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame ventana;
	private JTextField textFieldReponer;
	private JTable table;
	private JList<String>  listMateriaPrimasDisponibles;
	private String[] nombreMaterias;
	private MateriaPrima materiaSeleccionada;
	private List<MateriaPrima> materiaAux;
	private JLabel lblSaldoActualValor;
	private JLabel lblCosteRepoValor;
	private static JLabel lblSaldoResultanteValor;
	private double saldoActual;
	private JTextField textProveedor;
	private JTextField textPrecio;
	private File Directorio;
	private JFileChooser chooser;
	/**
	 * Create the panel.
	 */
	public PanelGestionInventario(JFrame v) {
		saldoActual = ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 0, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{55, 91, 0, 71, 0, 5, 0};
		gbl_panel_2.rowHeights = new int[]{16, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblSaldoActual = new JLabel(Inter.getInstance().getString("menu.gestion.lblSaldoActual"));
		GridBagConstraints gbc_lblSaldoActual = new GridBagConstraints();
		gbc_lblSaldoActual.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSaldoActual.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaldoActual.gridx = 1;
		gbc_lblSaldoActual.gridy = 0;
		panel_2.add(lblSaldoActual, gbc_lblSaldoActual);
		
		JLabel label = new JLabel("-");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		JLabel lblCosteReposicin = new JLabel(Inter.getInstance().getString("menu.gestion.lblCosteRepo"));
		GridBagConstraints gbc_lblCosteReposicin = new GridBagConstraints();
		gbc_lblCosteReposicin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCosteReposicin.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosteReposicin.gridx = 3;
		gbc_lblCosteReposicin.gridy = 0;
		panel_2.add(lblCosteReposicin, gbc_lblCosteReposicin);
		
		JLabel label_1 = new JLabel("=");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 0;
		panel_2.add(label_1, gbc_label_1);
		
		JLabel lblSaldoResultante = new JLabel(Inter.getInstance().getString("menu.gestion.lblSaldoResultante"));
		GridBagConstraints gbc_lblSaldoResultante = new GridBagConstraints();
		gbc_lblSaldoResultante.insets = new Insets(0, 0, 5, 0);
		gbc_lblSaldoResultante.gridx = 5;
		gbc_lblSaldoResultante.gridy = 0;
		panel_2.add(lblSaldoResultante, gbc_lblSaldoResultante);
		
		JLabel lblSaldoEmpresa = new JLabel(Inter.getInstance().getString("menu.gestion.lblSaldoEmpresa"));
		GridBagConstraints gbc_lblSaldoEmpresa = new GridBagConstraints();
		gbc_lblSaldoEmpresa.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSaldoEmpresa.insets = new Insets(0, 0, 0, 5);
		gbc_lblSaldoEmpresa.gridx = 0;
		gbc_lblSaldoEmpresa.gridy = 1;
		panel_2.add(lblSaldoEmpresa, gbc_lblSaldoEmpresa);
		
		lblSaldoActualValor = new JLabel(String.valueOf(ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa()));
		GridBagConstraints gbc_lblSaldoActualValor = new GridBagConstraints();
		gbc_lblSaldoActualValor.insets = new Insets(0, 0, 0, 5);
		gbc_lblSaldoActualValor.gridx = 1;
		gbc_lblSaldoActualValor.gridy = 1;
		panel_2.add(lblSaldoActualValor, gbc_lblSaldoActualValor);
		
		JLabel label_3 = new JLabel("-");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridx = 2;
		gbc_label_3.gridy = 1;
		panel_2.add(label_3, gbc_label_3);
		
		lblCosteRepoValor = new JLabel("0.0");
		GridBagConstraints gbc_lblCosteRepoValor = new GridBagConstraints();
		gbc_lblCosteRepoValor.insets = new Insets(0, 0, 0, 5);
		gbc_lblCosteRepoValor.gridx = 3;
		gbc_lblCosteRepoValor.gridy = 1;
		panel_2.add(lblCosteRepoValor, gbc_lblCosteRepoValor);
		
		JLabel label_2 = new JLabel("=");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 1;
		panel_2.add(label_2, gbc_label_2);
		
		lblSaldoResultanteValor = new JLabel(String.valueOf(ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa()));
		GridBagConstraints gbc_lblSaldoResultanteValor = new GridBagConstraints();
		gbc_lblSaldoResultanteValor.gridx = 5;
		gbc_lblSaldoResultanteValor.gridy = 1;
		panel_2.add(lblSaldoResultanteValor, gbc_lblSaldoResultanteValor);
		
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JButton btnImprimirInv = new JButton(Inter.getInstance().getString("menu.gestion.btnImprimirInv"));
		
		btnImprimirInv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(ventana);
				Directorio = chooser.getSelectedFile();
				
				try {
					if (Directorio.toString() !=null) {
						ControladorIbiJuguetes.getUnicaInstancia().generarPDF(Directorio.toString());
						JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.gestion.textoPDFOk"));
					}
					
				} catch (FileNotFoundException | DocumentException e1) {
					JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.gestion.textoPDFError"));
				}
				
				
			}
		});
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		add(btnImprimirInv, gbc_btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, Inter.getInstance().getString("menu.gestion.btnReponer"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{5, 0, 0, 0, 0, 0, 5, 0};
		gbl_panel.rowHeights = new int[]{5, 0, 0, 5, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		listMateriaPrimasDisponibles = generarJList(scrollPane);
		actualizarListEvent();
	
		
		JLabel lblCantidadReponer = new JLabel(Inter.getInstance().getString("menu.gestion.lblCantidadReponer"));
		GridBagConstraints gbc_lblCantidadReponer = new GridBagConstraints();
		gbc_lblCantidadReponer.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadReponer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadReponer.gridx = 1;
		gbc_lblCantidadReponer.gridy = 2;
		panel.add(lblCantidadReponer, gbc_lblCantidadReponer);
		
		textFieldReponer = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		panel.add(textFieldReponer, gbc_textField);
		textFieldReponer.setColumns(10);
		
		JLabel lblProveedor = new JLabel(Inter.getInstance().getString("menu.gestion.lblProveedor"));
		GridBagConstraints gbc_lblProveedor = new GridBagConstraints();
		gbc_lblProveedor.anchor = GridBagConstraints.EAST;
		gbc_lblProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblProveedor.gridx = 3;
		gbc_lblProveedor.gridy = 2;
		panel.add(lblProveedor, gbc_lblProveedor);
		
		textProveedor = new JTextField();
		GridBagConstraints gbc_textProveedor = new GridBagConstraints();
		gbc_textProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_textProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textProveedor.gridx = 4;
		gbc_textProveedor.gridy = 2;
		panel.add(textProveedor, gbc_textProveedor);
		textProveedor.setColumns(10);
		
		JButton btnReponer = new JButton(Inter.getInstance().getString("menu.gestion.btnReponer"));
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 5;
		gbc_btnNewButton_11.gridy = 2;
		panel.add(btnReponer, gbc_btnNewButton_11);
		
		JLabel lblPrecio = new JLabel(Inter.getInstance().getString("menu.gestion.lblPrecio"));
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.anchor = GridBagConstraints.EAST;
		gbc_lblPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrecio.gridx = 1;
		gbc_lblPrecio.gridy = 3;
		panel.add(lblPrecio, gbc_lblPrecio);
		
		textPrecio = new JTextField();
		GridBagConstraints gbc_textPrecio = new GridBagConstraints();
		gbc_textPrecio.insets = new Insets(0, 0, 0, 5);
		gbc_textPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrecio.gridx = 2;
		gbc_textPrecio.gridy = 3;
		panel.add(textPrecio, gbc_textPrecio);
		textPrecio.setColumns(10);
		
		JButton btnEstimarReposicion = new JButton(Inter.getInstance().getString("menu.gestion.lblEstimarPrecio"));
		GridBagConstraints gbc_btnEstimarReposicion = new GridBagConstraints();
		gbc_btnEstimarReposicion.insets = new Insets(0, 0, 0, 5);
		gbc_btnEstimarReposicion.gridx = 5;
		gbc_btnEstimarReposicion.gridy = 3;
		panel.add(btnEstimarReposicion, gbc_btnEstimarReposicion);
		
		btnEstimarReposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int cantidad =0;
				double costeValorRepo=0.0;
				if  ( !textFieldReponer.getText().equals("") && !textPrecio.getText().equals("") ){
					cantidad= Integer.parseInt(textFieldReponer.getText());
					costeValorRepo= Double.parseDouble(textFieldReponer.getText());
				}
				
			    costeValorRepo = cantidad*costeValorRepo;
				lblCosteRepoValor.setText(String.valueOf(costeValorRepo));
				
				double SaldoResultante =0.0;
				
				if (saldoActual > costeValorRepo) {
					SaldoResultante= saldoActual -costeValorRepo;
				}
				lblSaldoResultanteValor.setText(String.valueOf(SaldoResultante));
				
				
			}
		});
		
		btnReponer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((materiaSeleccionada != null )&&(!textProveedor.getText().isEmpty())&&(!textFieldReponer.getText().isEmpty())&&(!textPrecio.getText().isEmpty())) {
					
					if ( !ControladorIbiJuguetes.getUnicaInstancia().registrarCompra(materiaSeleccionada,textProveedor.getText(),Integer.parseInt(textFieldReponer.getText()),textPrecio.getText()) ) {
						JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.gestion.textoSaldoInsuficiente"));
					}else {
						table.setModel(new InventarioTabla(ControladorIbiJuguetes.getUnicaInstancia().obtenerTodosArticulos()));
						JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.gestion.textoCompraOK"));
						saldoActual = ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa();
						lblSaldoActualValor.setText(String.valueOf(saldoActual));
						ControladorVista.actualizarSaldo();
						
						lblCosteRepoValor.setText("0.0");
						lblSaldoResultanteValor.setText(String.valueOf(ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa()));
					} 
				}
				else{
					JOptionPane.showMessageDialog(v, Inter.getInstance().getString("menu.gestion.textoCampos"));
				}
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null,Inter.getInstance().getString("menu.gestion.lblConsultaInv") , TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 8;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new InventarioTabla(ControladorIbiJuguetes.getUnicaInstancia().obtenerTodosArticulos()));
		scrollPane_1.setViewportView(table);
		setVentana(v); 
		
	}
	
	
private JList<String> generarJList(JScrollPane scrollPane_1) {
		
		materiaAux = ControladorIbiJuguetes.getUnicaInstancia().getMateriasPrimasDisponibles();
		//PARA CREAR EL ARRAY PARA EL MODELO DEL JLIST
		nombreMaterias = new String[materiaAux.size()];
		int i = 0;
		synchronized (nombreMaterias) {
			for (MateriaPrima materia : materiaAux) {
				nombreMaterias[i] = materia.getArticulo();
				i++;
			}
		}	
		
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
		
			private static final long serialVersionUID = 1L;
			String[] values = nombreMaterias;
		
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
		
			listMateriaPrimasDisponibles.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					materiaSeleccionada = materiaAux.get(listMateriaPrimasDisponibles.getSelectedIndex());
				}
			});
			
	}
	
public JFrame getVentana() {
		return ventana;
	}


	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

private class InventarioTabla extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		
		
		private LinkedList<Articulo> articulos;
		
		private String[] columnNames = {Inter.getInstance().getString("menu.gestion.lblArticulo"),Inter.getInstance().getString("menu.gestion.lblTipo") ,Inter.getInstance().getString("menu.gestion.lblStock"),Inter.getInstance().getString("menu.gestion.lblPUM")};
		
		
		public InventarioTabla(List<Articulo> listaArticulos) {
			this.articulos = new LinkedList<Articulo>(listaArticulos);
		}
		
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public int getRowCount() {
			return articulos.size();
		}
		public int getColumnCount() {
			return 4;
		}
		
		public Class<?> getColumnClass(int column) {
	        switch(column) {
	            case 0: return String.class;
	            case 1: return String.class;
	            case 2: return Integer.class;
	            case 3: return String.class;
	            default: return Object.class;

	        }
	    }
		
		public Object getValueAt(int row, int col) {
			String valor = "";
			Articulo articulo = articulos.get(row);
			
			switch (col) {
				case 0 : valor = articulo.getArticulo();
				break;
				
				case 1 : if ( articulo.getIdTipoArticulo() == 1) {
					
								valor = "Materia Prima";
								
						 }else {
							 	valor = "Producto Final";
					 }
				break;
				
				
				case 2 : return articulo.getStock();
				
				case 3 : if ( articulo.getIdTipoArticulo() == 1) {
							MateriaPrima aux = (MateriaPrima) articulo;
							valor = String.valueOf(aux.getPrecio());
						}
						else {
							valor = "";
						}
				break;
				
			}
			return valor;
		}
		
		public void setValueAt(Object value, int row, int col) { }
		
	}
	
	

}
