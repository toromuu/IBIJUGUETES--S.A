package vista;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;

import java.util.List;


import javax.swing.AbstractListModel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;


import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;
import modelo.MateriaPrima;

import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class PanelAltaProducto extends JPanel {

	private JLabel rotulo;
	private JPanel datosProducto;
	private JLabel lnombre, lalerta;
	private JTextField nombre;
	private JButton btnRegistrar;
	static ControladorVista ventana;
	private JLabel lblBeneficio;
	private JTextField textFieldBeneficio;
	private JLabel lblTipo;
	private JRadioButton rdbtnMateriaPrima;
	private JRadioButton rdbtnProductoFinal;
	private JPanel panel_materiasprimas;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JList<String>  listDisponibles;
	private JButton btnseleccionarMateriaPrima;
	private JTable table;
	private JPanel panel_1;
	private HashMap<MateriaPrima,Integer> listaMateriasPrimas;
	private String[] nombreMaterias;
	private List<MateriaPrima> materiasAux;
	private MateriaPrima materiaSeleccionada;
	private JButton button;
	
	
	public PanelAltaProducto(ControladorVista v){
		setMinimumSize(new Dimension(1000, 500));
		ventana=v; 
		crearPantalla();
	}

	private void crearPantalla() {
				
				listaMateriasPrimas = new HashMap<MateriaPrima,Integer>();
				ButtonGroup tipos = new ButtonGroup();
		
				setSize(844,512);
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[]{0, 844, 0, 0};
				gridBagLayout.rowHeights = new int[]{15, 400, 15, 0};
				gridBagLayout.columnWeights = new double[]{10.0, 0.0, 10.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
				setLayout(gridBagLayout);
						
				datosProducto=new JPanel();
				datosProducto.setMinimumSize(new Dimension(800, 450));
				GridBagLayout gbl_datosProducto = new GridBagLayout();
				gbl_datosProducto.columnWidths = new int[]{116, 0, 0, 0, 0};
				gbl_datosProducto.rowHeights = new int[]{60, 22, 25, 0, 0, 0, 0, 0, 0, 22, 0, 30, 16, 0};
				gbl_datosProducto.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_datosProducto.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				datosProducto.setLayout(gbl_datosProducto);
				rotulo=new JLabel(Inter.getInstance().getString("menulbl.alta.rotulo"),JLabel.CENTER);
				GridBagConstraints gbc_rotulo = new GridBagConstraints();
				gbc_rotulo.insets = new Insets(0, 0, 5, 0);
				gbc_rotulo.gridwidth = 4;
				gbc_rotulo.gridx = 0;
				gbc_rotulo.gridy = 0;
				datosProducto.add(rotulo, gbc_rotulo);
				fixedSize(rotulo,Constantes.x_size,60);
				rotulo.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
				
				lnombre=new JLabel(Inter.getInstance().getString("menulbl.alta.nombre"),JLabel.RIGHT);	
				fixedSize(lnombre,170,24);
				GridBagConstraints gbc_lnombre = new GridBagConstraints();
				gbc_lnombre.anchor = GridBagConstraints.EAST;
				gbc_lnombre.insets = new Insets(0, 0, 5, 5);
				gbc_lnombre.gridx = 0;
				gbc_lnombre.gridy = 1;
				datosProducto.add(lnombre, gbc_lnombre);
				nombre=new JTextField(); 
				fixedSize(nombre,140,24);
				GridBagConstraints gbc_nombre = new GridBagConstraints();
				gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombre.insets = new Insets(0, 0, 5, 0);
				gbc_nombre.gridwidth = 3;
				gbc_nombre.gridx = 1;
				gbc_nombre.gridy = 1;
				datosProducto.add(nombre, gbc_nombre);
				
				lblTipo = new JLabel(Inter.getInstance().getString("menulbl.alta.tipo"));
				GridBagConstraints gbc_lblTipo = new GridBagConstraints();
				gbc_lblTipo.anchor = GridBagConstraints.EAST;
				gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
				gbc_lblTipo.gridx = 0;
				gbc_lblTipo.gridy = 2;
				datosProducto.add(lblTipo, gbc_lblTipo);
				
				rdbtnMateriaPrima = new JRadioButton(Inter.getInstance().getString("menurdboton.alta.matPrima"));
				GridBagConstraints gbc_rdbtnMateriaPrima = new GridBagConstraints();
				gbc_rdbtnMateriaPrima.anchor = GridBagConstraints.WEST;
				gbc_rdbtnMateriaPrima.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnMateriaPrima.gridx = 1;
				gbc_rdbtnMateriaPrima.gridy = 2;
				datosProducto.add(rdbtnMateriaPrima, gbc_rdbtnMateriaPrima);
				tipos.add(rdbtnMateriaPrima);
				
				rdbtnMateriaPrima.setSelected(true);
				
				rdbtnProductoFinal = new JRadioButton(Inter.getInstance().getString("menurdboton.alta.prodFinal"));
				GridBagConstraints gbc_rdbtnProductoFinal = new GridBagConstraints();
				gbc_rdbtnProductoFinal.anchor = GridBagConstraints.WEST;
				gbc_rdbtnProductoFinal.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnProductoFinal.gridx = 2;
				gbc_rdbtnProductoFinal.gridy = 2;
				datosProducto.add(rdbtnProductoFinal, gbc_rdbtnProductoFinal);
				tipos.add(rdbtnProductoFinal);
				
				lblBeneficio = new JLabel(Inter.getInstance().getString("menulbl.alta.beneficio"));
				GridBagConstraints gbc_lblBeneficio = new GridBagConstraints();
				gbc_lblBeneficio.anchor = GridBagConstraints.EAST;
				gbc_lblBeneficio.insets = new Insets(0, 0, 5, 5);
				gbc_lblBeneficio.gridx = 0;
				gbc_lblBeneficio.gridy = 3;
				datosProducto.add(lblBeneficio, gbc_lblBeneficio);
				
				textFieldBeneficio = new JTextField();
				GridBagConstraints gbc_textFieldBeneficio = new GridBagConstraints();
				gbc_textFieldBeneficio.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldBeneficio.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldBeneficio.gridx = 1;
				gbc_textFieldBeneficio.gridy = 3;
				datosProducto.add(textFieldBeneficio, gbc_textFieldBeneficio);
				
				panel = new JPanel();
				panel.setBorder(new TitledBorder(null, Inter.getInstance().getString("menulbl.alta.mpDisponibles"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridheight = 4;
				gbc_panel.insets = new Insets(0, 0, 5, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 5;
				datosProducto.add(panel, gbc_panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				
				scrollPane_1 = new JScrollPane();
				panel.add(scrollPane_1);
				
				
				scrollPane_1.setViewportView(listDisponibles);
				
				listDisponibles = generarJList(scrollPane_1);
				actualizarListEvent();
				
				panel_materiasprimas = new JPanel();
				panel_materiasprimas.setBorder(new TitledBorder(null,Inter.getInstance().getString("menulbl.alta.mpCantidad") , TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_materiasprimas = new GridBagConstraints();
				gbc_panel_materiasprimas.gridheight = 4;
				gbc_panel_materiasprimas.insets = new Insets(0, 0, 5, 0);
				gbc_panel_materiasprimas.fill = GridBagConstraints.BOTH;
				gbc_panel_materiasprimas.gridx = 3;
				gbc_panel_materiasprimas.gridy = 5;
				datosProducto.add(panel_materiasprimas, gbc_panel_materiasprimas);
				panel_materiasprimas.setLayout(new BoxLayout(panel_materiasprimas, BoxLayout.X_AXIS));
				
				scrollPane = new JScrollPane();
				panel_materiasprimas.add(scrollPane);
							
				table = new JTable();
				table.setModel(new MateriasSeleccionadasTabla(listaMateriasPrimas));
				scrollPane.setViewportView(table);
				
				button = new JButton("");
				button.setIcon(new ImageIcon(PanelAltaProducto.class.getResource("/vistaRecursos/flechaNegraDerecha.png")));
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						listaMateriasPrimas.remove(materiaSeleccionada, (Integer) spinner.getValue());
						table.setModel(new MateriasSeleccionadasTabla(listaMateriasPrimas));
					}
				});
				
				panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Inter.getInstance().getString("menulbl.alta.cantidad"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 2;
				gbc_panel_1.gridy = 6;
				datosProducto.add(panel_1, gbc_panel_1);
				
				spinner = new JSpinner();
				panel_1.add(spinner);
				
				btnseleccionarMateriaPrima = new JButton("");
				btnseleccionarMateriaPrima.setIcon(new ImageIcon(PanelAltaProducto.class.getResource("/vistaRecursos/flechaNegraIzquierda.png")));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 2;
				gbc_btnNewButton.gridy = 7;
				datosProducto.add(btnseleccionarMateriaPrima, gbc_btnNewButton);
				
				btnseleccionarMateriaPrima.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						listaMateriasPrimas.put(materiaSeleccionada, (Integer) spinner.getValue());
						table.setModel(new MateriasSeleccionadasTabla(listaMateriasPrimas));
					}
				});
				
				
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = 2;
				gbc_button.gridy = 8;
				datosProducto.add(button, gbc_button);
				
				btnRegistrar=new JButton(Inter.getInstance().getString("menuboton.alta.registrar")); 
				fixedSize(btnRegistrar,100,30);
				GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
				gbc_btnRegistrar.anchor = GridBagConstraints.WEST;
				gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
				gbc_btnRegistrar.gridx = 1;
				gbc_btnRegistrar.gridy = 9;
				datosProducto.add(btnRegistrar, gbc_btnRegistrar);
				
				//Manejadores
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {	
						if(nombre.getText().isEmpty()){
							JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.nombre"));
							
						}
						else{
							if (rdbtnMateriaPrima.isSelected()) {
								if (ControladorIbiJuguetes.getUnicaInstancia().registrarMateriaPrima(nombre.getText())) {
									JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.registroMPOk"));
								}else {
									JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.registroMPError"));
								}
							}else {
								if(textFieldBeneficio.getText().isEmpty()){
									JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.beneficio"));
								}
								else
									if(!listaMateriasPrimas.isEmpty()){
										if ( ControladorIbiJuguetes.getUnicaInstancia().registrarProductoFinal(nombre.getText(),textFieldBeneficio.getText(), listaMateriasPrimas)) {
											JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.registroPFOk"));
										}else {
											JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.registroPFError"));
										}
									}else JOptionPane.showMessageDialog(ventana, Inter.getInstance().getString("menutexto.alta.listaMPVacia"));
							}
						}
					}
					
				});
							
			GridBagConstraints gbc_datosProducto = new GridBagConstraints();
			gbc_datosProducto.gridheight = 2;
			gbc_datosProducto.insets = new Insets(0, 0, 5, 5);
			gbc_datosProducto.gridx = 1;
			gbc_datosProducto.gridy = 1;
			add(datosProducto, gbc_datosProducto);
			
			lalerta=new JLabel(Inter.getInstance().getString("menulbl.alta.alerta"),JLabel.CENTER);
			lalerta.setForeground(Color.RED); 
			fixedSize(lalerta,Constantes.x_size,30);
			lalerta.setVisible(false);
			GridBagConstraints gbc_lalerta = new GridBagConstraints();
			gbc_lalerta.insets = new Insets(0, 0, 5, 5);
			gbc_lalerta.anchor = GridBagConstraints.NORTHWEST;
			gbc_lalerta.gridx = 1;
			gbc_lalerta.gridy = 10;
			datosProducto.add(lalerta, gbc_lalerta);
		
			if (rdbtnMateriaPrima.isSelected()) {
				lblBeneficio.setVisible(false);
				textFieldBeneficio.setVisible(false);
				panel_materiasprimas.setVisible(false);
				btnseleccionarMateriaPrima.setVisible(false);
				button.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(false);
				
			}
			
			rdbtnMateriaPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					lblBeneficio.setVisible(false);
					textFieldBeneficio.setVisible(false);
					panel_materiasprimas.setVisible(false);
					btnseleccionarMateriaPrima.setVisible(false);
					button.setVisible(false);
					panel_1.setVisible(false);
					panel.setVisible(false);
				}
			});
			
			
			rdbtnProductoFinal.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					lblBeneficio.setVisible(true);
					textFieldBeneficio.setVisible(true);
					panel_materiasprimas.setVisible(true);
					btnseleccionarMateriaPrima.setVisible(true);
					button.setVisible(true);
					panel_1.setVisible(true);
					panel.setVisible(true);
					
				}
			});
		
		

	}
	
	
	private void fixedSize(JComponent c,int x, int y) {
		c.setMinimumSize(new Dimension(x,y));
		c.setMaximumSize(new Dimension(x,y));
		c.setPreferredSize(new Dimension(x,y));
	}	
	
private class MateriasSeleccionadasTabla extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		
		private LinkedHashMap<MateriaPrima,Integer> Materias;
		private ArrayList<MateriaPrima> claves;
		
		private String[] columnNames = {Inter.getInstance().getString("menurdboton.alta.matPrima"), Inter.getInstance().getString("menulbl.alta.cantidad")};
		
		
		public MateriasSeleccionadasTabla(HashMap<MateriaPrima, Integer> listaMateriasPrimas) {
			this.Materias = new LinkedHashMap<>(listaMateriasPrimas);
			this.claves = new ArrayList<MateriaPrima>(Materias.keySet());
		}
		
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public int getRowCount() {
			return Materias.size();
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
				
				case 1 : valor = Materias.get(materia).toString();
				break;
			}
			
			return valor;
		}
		
		
		public void setValueAt(Object value, int row, int col) { }
		
	}



	private JList<String> generarJList(JScrollPane scrollPane_1) {
		
		materiasAux = ControladorIbiJuguetes.getUnicaInstancia().getMateriasPrimasDisponibles();
		nombreMaterias = new String[materiasAux.size()];
		int i = 0;
		synchronized (nombreMaterias) {
			for (MateriaPrima materia : materiasAux) {
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
		
			listDisponibles.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
				materiaSeleccionada = materiasAux.get(listDisponibles.getSelectedIndex());
				}
			});
			
	}


}
