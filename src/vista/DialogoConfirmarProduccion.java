package vista;




import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;
import modelo.ProductoFinal;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoConfirmarProduccion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoConfirmarProduccion dialog = new DialogoConfirmarProduccion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoConfirmarProduccion() {
		setMinimumSize(new Dimension(550, 510));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoConfirmarProduccion.class.getResource("/vistaRecursos/peligro.png")));
		setBounds(100, 100, 550, 510);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 432, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 253, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(DialogoConfirmarProduccion.class.getResource("/vistaRecursos/peligro.png")));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			getContentPane().add(label, gbc_label);
		}
		{
			JLabel lblatencion = new JLabel(Inter.getInstance().getString("menu.confPedido.lblAtencion"), SwingConstants.CENTER);
			lblatencion.setPreferredSize(new Dimension(630, 60));
			lblatencion.setMinimumSize(new Dimension(630, 60));
			lblatencion.setMaximumSize(new Dimension(630, 60));
			lblatencion.setFont(new Font("Arial", Font.BOLD, 32));
			GridBagConstraints gbc_lblatencion = new GridBagConstraints();
			gbc_lblatencion.insets = new Insets(0, 0, 5, 5);
			gbc_lblatencion.gridx = 1;
			gbc_lblatencion.gridy = 2;
			getContentPane().add(lblatencion, gbc_lblatencion);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{5, 0, 0, 0, 0, 5, 0};
			gbl_panel.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblSeVanA = new JLabel(Inter.getInstance().getString("menu.confPedido.lblSenVanA"));
				GridBagConstraints gbc_lblSeVanA = new GridBagConstraints();
				gbc_lblSeVanA.gridwidth = 4;
				gbc_lblSeVanA.insets = new Insets(0, 0, 5, 5);
				gbc_lblSeVanA.gridx = 1;
				gbc_lblSeVanA.gridy = 1;
				panel.add(lblSeVanA, gbc_lblSeVanA);
			}
			{
				JLabel lblX = new JLabel(PanelProduccion.getCantidad());
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 1;
				gbc_lblX.gridy = 2;
				panel.add(lblX, gbc_lblX);
			}
			{
				JLabel lblUnidades = new JLabel(Inter.getInstance().getString("menu.confPedido.lblUnidades"));
				GridBagConstraints gbc_lblUnidades = new GridBagConstraints();
				gbc_lblUnidades.insets = new Insets(0, 0, 5, 5);
				gbc_lblUnidades.gridx = 2;
				gbc_lblUnidades.gridy = 2;
				panel.add(lblUnidades, gbc_lblUnidades);
			}
			{
				JLabel lblDelProductoFinal = new JLabel(Inter.getInstance().getString("menu.confPedido.lblProdFinal"));
				GridBagConstraints gbc_lblDelProductoFinal = new GridBagConstraints();
				gbc_lblDelProductoFinal.insets = new Insets(0, 0, 5, 5);
				gbc_lblDelProductoFinal.gridx = 3;
				gbc_lblDelProductoFinal.gridy = 2;
				panel.add(lblDelProductoFinal, gbc_lblDelProductoFinal);
			}
			{
				JLabel lblNewLabel_1 = new JLabel(PanelProduccion.getProductoFinalSeleccionado().getArticulo());
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 4;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 3;
				panel.add(panel_1, gbc_panel_1);
				
					JButton btnConfirmar = new JButton(Inter.getInstance().getString("menu.confPedido.btnConfirmar"));
					panel_1.add(btnConfirmar);
					DialogoConfirmarProduccion dialogo = this;
					btnConfirmar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ProductoFinal productoFinalProducir = PanelProduccion.getProductoFinalSeleccionado();
							if (ControladorIbiJuguetes.getUnicaInstancia().registrarProduccion(productoFinalProducir, PanelProduccion.getCantidad()) ) {
								JOptionPane.showMessageDialog(PanelAltaProducto.ventana, Inter.getInstance().getString("menu.confPedido.textoProdOK"));
								PanelProduccion.actualizarDatosUltimaProduccion();
								
							}
							else {
								JOptionPane.showMessageDialog(PanelAltaProducto.ventana, Inter.getInstance().getString("menu.confPedido.textoMPInsuficientes"));
							}
							dialogo.setVisible(false);
							
						}
					});
					
				
					JButton btnCancelarProduccion = new JButton(Inter.getInstance().getString("menu.confPedido.bntCancelar"));
					panel_1.add(btnCancelarProduccion);
					btnCancelarProduccion.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(PanelAltaProducto.ventana, Inter.getInstance().getString("menu.confPedido.textoProdCancelada"));
							dialogo.setVisible(false);
							
						}
					});
				
			}
		}
	}

}
