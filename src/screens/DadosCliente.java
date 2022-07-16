package screens;

import javax.swing.JFrame;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import templates.ICliente;
import templates.IConta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DadosCliente extends JFrame{

	/**
	 * 
	 */
	private JButton btnVoltar;
	private JLabel lblDados;
	private JLabel lblNome;
	private JLabel lblNomeCliente ;
	private JLabel lblSobreNome;
	private JLabel lblSobre;
	private static final long serialVersionUID = 1L;
	private JLabel lblCnpj;
	private JLabel lblCnpjCliente;
	private JLabel lblEmail;
	private JLabel lblEmailCliente;
	private JLabel lblRua;
	private JLabel lblRuaCliente;
	private JLabel lblBairro;
	private JLabel lblBairroCliente;
	private JLabel lblNumero;
	private JLabel lblEndereco;
	private JLabel lblNumeroCliente;
	private JLabel lblCidade;
	private JLabel lblCidadeCliente;
	private JLabel lblNewLabel;
	private JLabel lblEstadoCliente;
	private JLabel lblContas;
	public DadosCliente(ICliente cliente,IConta conta)
	{
		setTitle("Bank of Taboquinha");
		setVisible(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 684, 567);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVoltar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new TelaClienteLogado(cliente, conta);
					}
				});
			}
		});
		btnVoltar.setBounds(10, 11, 89, 23);
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnVoltar);
		
		lblDados = new JLabel("Meus Dados");
		lblDados.setBounds(275, 9, 118, 23);
		lblDados.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblDados);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 68, 55, 23);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNome);
		
		lblNomeCliente = new JLabel(cliente.pegaNome());
		lblNomeCliente.setBounds(66, 68, 143, 23);
		lblNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNomeCliente);
		
		lblSobreNome = new JLabel("Sobrenome/Razão social:");
		lblSobreNome.setBounds(240, 70, 190, 23);
		lblSobreNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblSobreNome);
		
		lblSobre = new JLabel(cliente.pegaSobrenome());
		lblSobre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSobre.setBounds(422, 68, 215, 23);
		getContentPane().add(lblSobre);
		
		lblCnpj = new JLabel("CPF/CNPJ:");
		lblCnpj.setBounds(10, 111, 89, 23);
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCnpj);
		
		lblCnpjCliente = new JLabel(cliente.pegalogin());
		lblCnpjCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCnpjCliente.setBounds(97, 111, 145, 23);
		getContentPane().add(lblCnpjCliente);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(252, 117, 46, 23);
		getContentPane().add(lblEmail);
		
		lblEmailCliente = new JLabel(cliente.pegaEmail());
		lblEmailCliente.setBounds(304, 117, 215, 23);
		lblEmailCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblEmailCliente);
		
		lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRua.setBounds(10, 306, 46, 23);
		getContentPane().add(lblRua);
		
		lblRuaCliente = new JLabel(cliente.pegaRua());
		lblRuaCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRuaCliente.setBounds(45, 306, 176, 23);
		getContentPane().add(lblRuaCliente);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(240, 306, 58, 23);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblBairro);
		
		lblBairroCliente = new JLabel(cliente.pegaBairro());
		lblBairroCliente.setBounds(304, 306, 143, 23);
		lblBairroCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblBairroCliente);
		
		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(10, 340, 63, 23);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNumero);
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereco.setBounds(264, 250, 118, 23);
		getContentPane().add(lblEndereco);
		
		lblNumeroCliente = new JLabel(cliente.pegaNumero());
		lblNumeroCliente.setBounds(73, 340, 83, 23);
		lblNumeroCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNumeroCliente);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(240, 340, 58, 23);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCidade);
		
		lblCidadeCliente = new JLabel(cliente.pegaCidade());
		lblCidadeCliente.setBounds(295, 340, 110, 23);
		lblCidadeCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCidadeCliente);
		
		lblNewLabel = new JLabel("/");
		lblNewLabel.setBounds(412, 340, 18, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel);
		
		lblEstadoCliente = new JLabel(cliente.pegaEstado());
		lblEstadoCliente.setBounds(422, 340, 63, 23);
		lblEstadoCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblEstadoCliente);
		
		lblContas = new JLabel("Contas");
		lblContas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContas.setBounds(275, 379, 118, 23);
		getContentPane().add(lblContas);
	}
}
