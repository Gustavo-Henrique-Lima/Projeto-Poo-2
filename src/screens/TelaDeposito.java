package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exception.ContaDesativada;
import exception.DepositoInvalido;
import formatting.SoNumeros;
import persistence.ContaDAO;
import templates.ICliente;
import templates.IConta;

public class TelaDeposito extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private ContaDAO contadao= new ContaDAO();
	private JButton btnVoltar;
	private static final long serialVersionUID = 1L;
	
	public TelaDeposito(ICliente cliente,IConta conta)
	{
			Locale.setDefault(Locale.US);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 274, 206);
			setResizable(false);
			setVisible(true);
			setTitle("Bank of Taboquinha");
			setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			
			contentPane= new JPanel();
			contentPane.setVisible(true);
			contentPane.setBorder(new EmptyBorder(5,5,5,5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			lblNewLabel = new JLabel("Valor");
			lblNewLabel.setBounds(10, 74, 46, 31);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPane.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setDocument(new SoNumeros());
			textField.setBounds(60, 74, 111, 31);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("Depositar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BigDecimal valor=toDecimal(textField.getText());
					try {
						conta.depositar(valor);
						contadao.sacar(cliente,conta, conta.pegaSaldo());
						contadao.salvarMovimentacao(cliente, valor, Date.valueOf(LocalDate.now()), "Depósito");
						JOptionPane.showMessageDialog(null,"Deposito realizado com sucesso");
						setVisible(false);
						new TelaClienteLogado(cliente, conta);
					} catch (DepositoInvalido | ContaDesativada e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
			btnNewButton.setBounds(42, 121, 129, 35);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPane.add(btnNewButton);
			
			btnVoltar = new JButton("Voltar");
			btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnVoltar.setBounds(159, 0, 89, 31);
			btnVoltar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new TelaClienteLogado(cliente, conta);
				}
			});
			contentPane.add(btnVoltar);
		}
		public static BigDecimal toDecimal(String valor)
		{
			return new BigDecimal(valor);
		}
}