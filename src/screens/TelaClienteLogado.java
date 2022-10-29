package screens;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import exception.ContaDesativada;
import exception.DesativarContaComSaldo;
import persistence.ClienteDAO;
import persistence.ContaDAO;
import templates.Endereco;
import templates.ICliente;
import templates.IConta;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaClienteLogado extends JFrame{
	private JLabel lblboas;
	private JLabel lblNome;
	private JLabel lblSaldo;
	private JLabel lblsaldo;
	private JButton btnsair;
	private JButton btnTransferir;
	private JButton btnDepositar;
	private JButton btnSacar;
	private JButton btnConsultar;
	private JMenu perfil;
	private JMenuItem meuperfil;
	private JMenuItem editarperfil;
	private JMenuBar barra;
	private ClienteDAO clientedao=new ClienteDAO();
	private ContaDAO contadao=new ContaDAO();
	private static final long serialVersionUID = 1L;
	private JButton btnDesativar;
	
	public TelaClienteLogado(ICliente cliente,IConta conta)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 468);
		setResizable(false);
		setVisible(true);
		setTitle("Bank of Taboquinha");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		Endereco end=clientedao.localizarEndereco(cliente);
		cliente.adicionarEndereco(end);
		lblboas = new JLabel("Bem vindo");
		lblboas.setBounds(10, 28, 94, 25);
		lblboas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblboas);
		
		lblNome = new JLabel(cliente.pegaNome()+",");
		lblNome.setBounds(114, 28, 251, 25);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNome);
		
		lblSaldo = new JLabel("saldo atual, R$");
		lblSaldo.setBounds(10, 63, 141, 25);
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblSaldo);
		
		lblsaldo = new JLabel(conta.pegaSaldo().toString());
		lblsaldo.setBounds(146, 64, 104, 25);
		lblsaldo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblsaldo);
		
		btnsair = new JButton("Sair");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaInicial();
			}
		});
		btnsair.setBounds(308, 0, 89, 25);
		btnsair.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnsair);
		
		btnTransferir = new JButton("Transferir");
		btnTransferir.setBounds(15, 112, 203, 42);
		btnTransferir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnTransferir);
		
		btnDepositar = new JButton("Depositar");
		btnDepositar.setBounds(15, 165, 203, 42);
		btnDepositar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDepositar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaDeposito(cliente, conta);
			}
		});
		getContentPane().add(btnDepositar);
		
		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(15, 218, 203, 42);
		btnSacar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaSaque(cliente, conta);
			}
		});
		getContentPane().add(btnSacar);
		
		btnConsultar = new JButton("Consultar Extrato");
		btnConsultar.setBounds(15, 271, 203, 42);
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaExtrato(cliente, conta);
			}
		});
		getContentPane().add(btnConsultar);
		
		btnDesativar = new JButton("Desativar Conta");
		btnDesativar.setBounds(15, 324, 203, 42);
		btnDesativar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDesativar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					conta.desativarConta();
					contadao.desativarConta(conta, cliente);
					setVisible(false);
					new TelaInicial();
				} catch (ContaDesativada | DesativarContaComSaldo e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		getContentPane().add(btnDesativar);
		perfil(cliente,conta);
	}
	
	public void perfil(ICliente cliente, IConta conta)
	{
		perfil= new JMenu("Meu perfil");
		meuperfil= new JMenuItem("Consultar meus dados");
		meuperfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new DadosCliente(cliente, conta);
			}
		});
		perfil.add(meuperfil);
		editarperfil= new JMenuItem("Editar meus dados");
		editarperfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Sobre();
			}
		});
		perfil.add(editarperfil);
		barra= new JMenuBar();
		setJMenuBar(barra);
		barra.add(perfil);
	}
}