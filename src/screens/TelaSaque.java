package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exception.ContaDesativada;
import exception.SaldoInsuficiente;
import formatting.SoNumeros;
import persistence.ContaDAO;
import templates.ICliente;
import templates.IConta;

public class TelaSaque extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private ContaDAO contadao= new ContaDAO();
	private JButton btnVoltar;
	public TelaSaque(ICliente cliente,IConta conta)
	{
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
		
		JButton btnNewButton = new JButton("Sacar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal valor=toDecimal(textField.getText());
				try {
					conta.sacar(valor);
					contadao.sacar(cliente,conta, conta.pegaSaldo());
					contadao.salvarMovimentacao(cliente, valor, Date.valueOf(LocalDate.now()), "Saque");
					JOptionPane.showMessageDialog(null,"Saque realizado com sucesso");
					setVisible(false);
					new TelaClienteLogado(cliente, conta);
				} catch (SaldoInsuficiente e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
					setVisible(false);
					new TelaClienteLogado(cliente, conta);
				} catch (ContaDesativada e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
					setVisible(false);
					new TelaClienteLogado(cliente, conta);
				}
			}
		});
		btnNewButton.setBounds(42, 121, 89, 35);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new TelaClienteLogado(cliente, conta);
			}
		});
		btnVoltar.setBounds(159, 0, 89, 31);
		contentPane.add(btnVoltar);
	}
	public static BigDecimal toDecimal(String valor)
	{
		return new BigDecimal(valor);
	}
}
