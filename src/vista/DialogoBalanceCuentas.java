package vista;




import javax.swing.JDialog;
import javax.swing.JPanel;




import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import controlador.ControladorIbiJuguetes;
import internalizacion.Inter;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;


public class DialogoBalanceCuentas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoBalanceCuentas dialog = new DialogoBalanceCuentas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoBalanceCuentas() {
		setMinimumSize(new Dimension(610, 450));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoBalanceCuentas.class.getResource("/vistaRecursos/saldo.png")));
		setBounds(100, 100, 610, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 432, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 253, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblBalanceDeCuentas = new JLabel( Inter.getInstance().getString("menu.balance.lblBalance"), SwingConstants.CENTER);
			lblBalanceDeCuentas.setPreferredSize(new Dimension(630, 60));
			lblBalanceDeCuentas.setMinimumSize(new Dimension(630, 60));
			lblBalanceDeCuentas.setMaximumSize(new Dimension(630, 60));
			lblBalanceDeCuentas.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
			GridBagConstraints gbc_lblBalanceDeCuentas = new GridBagConstraints();
			gbc_lblBalanceDeCuentas.insets = new Insets(0, 0, 5, 5);
			gbc_lblBalanceDeCuentas.gridx = 1;
			gbc_lblBalanceDeCuentas.gridy = 1;
			getContentPane().add(lblBalanceDeCuentas, gbc_lblBalanceDeCuentas);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(DialogoBalanceCuentas.class.getResource("/vistaRecursos/management.png")));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 2;
			getContentPane().add(label, gbc_label);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{5, 0, 0, 0, 0, 5, 0};
			gbl_panel.rowHeights = new int[]{5, 0, 0, 0, 0, 0, 5, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblDelProductoFinal = new JLabel(Inter.getInstance().getString("menu.balance.lblCosteCompraMP"));
				GridBagConstraints gbc_lblDelProductoFinal = new GridBagConstraints();
				gbc_lblDelProductoFinal.anchor = GridBagConstraints.EAST;
				gbc_lblDelProductoFinal.insets = new Insets(0, 0, 5, 5);
				gbc_lblDelProductoFinal.gridx = 3;
				gbc_lblDelProductoFinal.gridy = 2;
				panel.add(lblDelProductoFinal, gbc_lblDelProductoFinal);
			}
			{
				double precio=ControladorIbiJuguetes.getUnicaInstancia().precioCompras();
				JLabel lblNewLabel_1 = new JLabel(String.valueOf(precio));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 4;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JLabel label = new JLabel("\u20AC");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.WEST;
				gbc_label.insets = new Insets(0, 0, 5, 0);
				gbc_label.gridx = 5;
				gbc_label.gridy = 2;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblCosteDeProduccin = new JLabel(Inter.getInstance().getString("menu.balance.lblCosteProduccionPF"));
				GridBagConstraints gbc_lblCosteDeProduccin = new GridBagConstraints();
				gbc_lblCosteDeProduccin.insets = new Insets(0, 0, 5, 5);
				gbc_lblCosteDeProduccin.gridx = 3;
				gbc_lblCosteDeProduccin.gridy = 3;
				panel.add(lblCosteDeProduccin, gbc_lblCosteDeProduccin);
			}
			{
				double coste=ControladorIbiJuguetes.getUnicaInstancia().costeProduccionMatPrimas();
				JLabel lblNewLabel = new JLabel(String.valueOf(coste));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 4;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel label = new JLabel("\u20AC");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.WEST;
				gbc_label.insets = new Insets(0, 0, 5, 0);
				gbc_label.gridx = 5;
				gbc_label.gridy = 3;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblBeneficioEsperado = new JLabel(Inter.getInstance().getString("menu.balance.lblBeneficio"));
				GridBagConstraints gbc_lblBeneficioEsperado = new GridBagConstraints();
				gbc_lblBeneficioEsperado.anchor = GridBagConstraints.EAST;
				gbc_lblBeneficioEsperado.insets = new Insets(0, 0, 5, 5);
				gbc_lblBeneficioEsperado.gridx = 3;
				gbc_lblBeneficioEsperado.gridy = 4;
				panel.add(lblBeneficioEsperado, gbc_lblBeneficioEsperado);
			}
			{
				
				Double beneficioEsperado = ControladorIbiJuguetes.getUnicaInstancia().obtenerBeneficioEsperado();
				
				
				JLabel lblNewLabel_2 = new JLabel(String.valueOf( (double)Math.round(beneficioEsperado * 100d) / 100d));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 4;
				gbc_lblNewLabel_2.gridy = 4;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				JLabel label = new JLabel("\u20AC");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.WEST;
				gbc_label.insets = new Insets(0, 0, 5, 0);
				gbc_label.gridx = 5;
				gbc_label.gridy = 4;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblGananciaDeVentas = new JLabel(Inter.getInstance().getString("menu.balance.lblGananciasVentas"));
				GridBagConstraints gbc_lblGananciaDeVentas = new GridBagConstraints();
				gbc_lblGananciaDeVentas.anchor = GridBagConstraints.EAST;
				gbc_lblGananciaDeVentas.insets = new Insets(0, 0, 5, 5);
				gbc_lblGananciaDeVentas.gridx = 3;
				gbc_lblGananciaDeVentas.gridy = 5;
				panel.add(lblGananciaDeVentas, gbc_lblGananciaDeVentas);
			}
			{
				double precio=ControladorIbiJuguetes.getUnicaInstancia().precioVentas();
				JLabel lblNewLabel_3 = new JLabel(String.valueOf(precio));
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 4;
				gbc_lblNewLabel_3.gridy = 5;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JLabel label = new JLabel("\u20AC");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.WEST;
				gbc_label.insets = new Insets(0, 0, 5, 0);
				gbc_label.gridx = 5;
				gbc_label.gridy = 5;
				panel.add(label, gbc_label);
			}
		}
	}

}
