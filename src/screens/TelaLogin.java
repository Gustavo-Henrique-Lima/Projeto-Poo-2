package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import persistence.ClienteDAO;
import persistence.ContaDAO;
import templates.ICliente;
import templates.IConta;

public class TelaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblConta;
	private JComboBox comboBox;
	private JButton btnEntrar;
	private JButton btnVoltar;
	private ClienteDAO clientedao= new ClienteDAO();
	private ContaDAO contadao= new ContaDAO();
	
	public TelaLogin()
	{
		setVisible(true);
		setSize(355,299);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bank of Taboquinha");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblLogin = new JLabel("CNPJ/CPF");
		lblLogin.setBounds(10, 46, 89, 24);
		lblLogin.setFont(new Font("Times New Roman",Font.PLAIN,18));
		getContentPane().add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(96, 44, 198, 30);
		getContentPane().add(txtLogin);
		txtLogin.setFont(new Font("Times New Roman",Font.PLAIN,18));
		txtLogin.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 83, 46, 33);
		lblSenha.setFont(new Font("Times New Roman",Font.PLAIN,18));
		getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(66, 85, 135, 30);
		txtSenha.setFont(new Font("Times New Roman",Font.PLAIN,18));
		getContentPane().add(txtSenha);
		
		lblConta = new JLabel("Qual tipo de conta voce irá acessar");
		lblConta.setBounds(10, 127, 251, 35);
		lblConta.setFont(new Font("Times New Roman",Font.PLAIN,18));
		getContentPane().add(lblConta);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Conta Poupança", "Conta Corrente"}));
		comboBox.setBounds(10, 173, 162, 41);
		comboBox.setFont(new Font("Times New Roman",Font.PLAIN,18));
		getContentPane().add(comboBox);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(0, 225, 89, 23);
		btnEntrar.setFont(new Font("Times New Roman",Font.PLAIN,18));
		btnEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String login=txtLogin.getText();
				String senha=txtSenha.getText();
				String conta=(String) comboBox.getSelectedItem();
				if(login.length()==11)
				{
					ICliente clientepf;
					clientepf=clientedao.localizarClientePf(login, senha);
					if(clientepf==null)
					{
						JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
					}
					else
					{
						if(conta.equals("Conta Corrente"))
						{
							IConta contaCliente;
							contaCliente=contadao.localizarContaCc(clientepf);
							if(contaCliente==null)
							{
								JOptionPane.showMessageDialog(null, "Erro ao acessar sua conta, revise seus dados e tente novamente");
							}
							else
							{
								if(contaCliente.pegaStatus()==0)
								{
									JOptionPane.showMessageDialog(null, "O cliente não tem nenhuma conta ativa no momento");
								}
								else
								{
									setVisible(false);
									new TelaClienteLogado(clientepf, contaCliente);
								}
							}
						}
						else
						{
							IConta contaCliente;
							contaCliente=contadao.localizarContaCp(clientepf);
							if(contaCliente==null)
							{
								JOptionPane.showMessageDialog(null, "Erro ao acessar sua conta, revise seus dados e tente novamente");
							}
							else
							{
								if(contaCliente.pegaStatus()==0)
								{
									JOptionPane.showMessageDialog(null, "O cliente não tem nenhuma conta ativa no momento");
								}
								else
								{
									setVisible(false);
									new TelaClienteLogado(clientepf, contaCliente);
								}
							}
						}
					}
				}
				
				else if(login.length()==14)
				{
					ICliente clientepj;
					clientepj=clientedao.localizarClientePj(login, senha);
					if(clientepj==null)
					{
						JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
					}
					else
					{
						if(conta.equals("Conta Corrente"))
						{
							IConta contaCliente;
							contaCliente=contadao.localizarContaCc(clientepj);
							if(contaCliente==null)
							{
								JOptionPane.showMessageDialog(null, "Erro ao acessar sua conta, revise seus dados e tente novamente");
							}
							else
							{
								if(contaCliente.pegaStatus()==0)
								{
									JOptionPane.showMessageDialog(null, "O cliente não tem nenhuma conta ativa no momento");
								}
								else
								{
									setVisible(false);
									new TelaClienteLogado(clientepj, contaCliente);
								}
							}
						}
						else
						{
							IConta contaCliente;
							contaCliente=contadao.localizarContaCp(clientepj);
							if(contaCliente==null)
							{
								JOptionPane.showMessageDialog(null, "Erro ao acessar sua conta, revise seus dados e tente novamente");
							}
							else
							{
								if(contaCliente.pegaStatus()==0)
								{
									JOptionPane.showMessageDialog(null, "O cliente não tem nenhuma conta ativa no momento");
								}
								else
								{
									setVisible(false);
									new TelaClienteLogado(clientepj, contaCliente);
								}
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário inválido");
				}
			}
		});
		getContentPane().add(btnEntrar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Times New Roman",Font.PLAIN,18));
		btnVoltar.setBounds(250, 0, 89, 30);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaInicial();
			}
		});
		getContentPane().add(btnVoltar);
	}
}