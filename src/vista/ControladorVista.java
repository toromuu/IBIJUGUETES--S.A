package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class ControladorVista extends JFrame implements ActionListener{
	
	//OTROS TIPOS DE APARENCIAS
	//UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	//UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	//UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
	//UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
	
	private static final String MINT_THEME = "com.jtattoo.plaf.mint.MintLookAndFeel";
	private static final String BLACK_THEME = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
	//JMENUS
	private JMenuBar menuPrincipal;
	private JMenu mVenta, mInventario, mProduccion;
	private JMenuItem mniAltaProductos, mniGestionarInventario;
	
	// PANELES
	private PanelAltaProducto pantallaAltaProducto;
	private PanelVentas pantallaVenta;
	private PanelGestionInventario pantallaGestionInventario;
	private PanelProduccion pantallaProduccion;
	private JLabel lblSaldoEmpresa;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private JButton btnSaldoEmpresa;
	private DialogoBalanceCuentas  balance;
	private DialogoRegistroOperaciones registroOperaciones;
	private static  JLabel saldoEmpresa;
	private JButton btnSpain;
	private JButton btnENG;
	private Component horizontalStrut_6;
	private JButton btnRegistroOperaciones;
	
	//saldo de la empresa
	
	
	public ControladorVista(String idioma, String tema) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		if (idioma.equals("En")) {
			Inter.getInstance().setIdioma("En");
		}else {
			Inter.getInstance().setIdioma("Esp");
		}
		
		if (tema.equals("Dark")) {
			UIManager.setLookAndFeel(BLACK_THEME);
		}else if (tema.equals("Mint")) {
	
			UIManager.setLookAndFeel(MINT_THEME);
		}
		
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControladorVista.class.getResource("/vistaRecursos/piezas.png")));
		// Configuracion grafica del Jframe
		setMinimumSize(new Dimension(1000, 500));
		setSize(500,411);
		setTitle("IBI JUGUETES");
		configurarMenu();
		this.setJMenuBar(menuPrincipal);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(90, 0));
		horizontalStrut.setMaximumSize(new Dimension(90, 32767));
		horizontalStrut.setMinimumSize(new Dimension(90, 0));
		menuPrincipal.add(horizontalStrut);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_1.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_1.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_1);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_2.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_2.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_2);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_3.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_3.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_3);
		//saldoEmpresa.setColumns(10);
		
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_4.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_4.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_4);
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_6.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_6.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_6);
		
		btnRegistroOperaciones = new JButton("");
		btnRegistroOperaciones.setIcon(new ImageIcon(ControladorVista.class.getResource("/vistaRecursos/study (1).png")));
		menuPrincipal.add(btnRegistroOperaciones);
		btnRegistroOperaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registroOperaciones.dispose();
				registroOperaciones = new DialogoRegistroOperaciones();
				registroOperaciones.setVisible(true);
			}
		});
		
		btnSaldoEmpresa = new JButton("");
		btnSaldoEmpresa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaldoEmpresa.setIcon(new ImageIcon(ControladorVista.class.getResource("/vistaRecursos/ahorro-de-dinero (1).png")));
		menuPrincipal.add(btnSaldoEmpresa);
		
				btnSaldoEmpresa.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						balance.dispose();
						balance = new DialogoBalanceCuentas();
						balance.setVisible(true);
						
					}
				});
		
		lblSaldoEmpresa = new JLabel(Inter.getInstance().getString("menulbl.main.saldoEmpresa"));
		lblSaldoEmpresa.setBorder(null);
		lblSaldoEmpresa.setIcon(null);
		menuPrincipal.add(lblSaldoEmpresa);
		
		saldoEmpresa =  new JLabel();
		
		menuPrincipal.add(saldoEmpresa);
		
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setPreferredSize(new Dimension(90, 0));
		horizontalStrut_5.setMinimumSize(new Dimension(90, 0));
		horizontalStrut_5.setMaximumSize(new Dimension(90, 32767));
		menuPrincipal.add(horizontalStrut_5);
		
		String saldo = String.valueOf(ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa());
		saldoEmpresa.setText(saldo);
		
		btnSpain = new JButton("");
		btnSpain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("unused")
					ControladorVista nuevaInstancia = new ControladorVista("Esp", "Dark");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				balance.dispose();
				registroOperaciones.dispose();
				dispose();
			}
		});
		
		
		btnSpain.setIcon(new ImageIcon(ControladorVista.class.getResource("/vistaRecursos/spain (1).png")));
		menuPrincipal.add(btnSpain);
		
		btnENG = new JButton("");
		btnENG.setIcon(new ImageIcon(ControladorVista.class.getResource("/vistaRecursos/united-kingdom (1).png")));
		btnENG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("unused")
					ControladorVista nuevaInstancia = new ControladorVista("En", "Mint");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {

					e.printStackTrace();
				}
				balance.dispose();
				registroOperaciones.dispose();
				dispose();
			}
		});
		
		
		menuPrincipal.add(btnENG);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*Instanciar Pantallas*/
		instanciarPaneles();
		
		//Pantalla inicial
		setContentPane(pantallaAltaProducto);
		pack();
		setVisible(true);
	}


	private void instanciarPaneles() {
		
		pantallaAltaProducto = new PanelAltaProducto(this);
		pantallaGestionInventario = new PanelGestionInventario(this);
		pantallaProduccion = new PanelProduccion(this);
		pantallaVenta = new PanelVentas(this);
		
		balance = new DialogoBalanceCuentas();
		balance.setVisible(false);
		registroOperaciones = new DialogoRegistroOperaciones();
		registroOperaciones.setVisible(false);
//		
	}
	
	
	// Configuracion grafica del Menu
	private void configurarMenu(){
		menuPrincipal=new JMenuBar();
		
		//1. Inventario
		mInventario= new JMenu(Inter.getInstance().getString("menu.main.inventario"));
		menuPrincipal.add(mInventario); 
		//1.1 Alta Productos
		mniAltaProductos= new JMenuItem(Inter.getInstance().getString("menuitem.main.altaProductos"));
		
		// 1.2 Gestionar Inventario
		mniGestionarInventario= new JMenuItem(Inter.getInstance().getString("menuitem.main.gestionarInv"));
		mInventario.add(mniAltaProductos); mInventario.add(mniGestionarInventario);
		
		//2. Produccion
		mProduccion= new JMenu(Inter.getInstance().getString("menu.main.produccion"));
		menuPrincipal.add(mProduccion);
		
		//3. Venta
		mVenta= new JMenu(Inter.getInstance().getString("menu.main.venta"));
		menuPrincipal.add(mVenta);
		
		
		mniAltaProductos.addActionListener(this);
		mniGestionarInventario.addActionListener(this);
		ControladorVista instanciaActual = this;
		
		mProduccion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pantallaProduccion = new PanelProduccion(instanciaActual);
				setContentPane(pantallaProduccion);
				revalidate();
				repaint();
			}
		});
		
		mVenta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 pantallaVenta = new PanelVentas(instanciaActual);
				 setContentPane(pantallaVenta);
				 revalidate();
				 repaint();
			}
		});
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()== mniAltaProductos) {
			pantallaAltaProducto = new PanelAltaProducto(this);
			setContentPane(pantallaAltaProducto);
			validate();
			return;	
		}
		
		if (e.getSource()== mniGestionarInventario) {
			pantallaGestionInventario = new PanelGestionInventario(this);
			setContentPane(pantallaGestionInventario);
			validate();
			return;
		}
		
		if (e.getSource()== btnSaldoEmpresa) {
			balance.setVisible(true);
			validate();
			return;
		}

	}
	//Metodo para que cada vez que se realice una compra o venta se actualice el saldo de la empresa
	public static void actualizarSaldo(){
		String saldo = String.valueOf(ControladorIbiJuguetes.getUnicaInstancia().getSaldoActualEmpresa());
		saldoEmpresa.setText(saldo);
	}
	
	
	
	
}
