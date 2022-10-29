package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import persistence.ClienteDAO;
import persistence.ConexaoMySql;
import persistence.ContaDAO;
import persistence.IConexao;
import templates.ClientePF;
import templates.ContaCorrente;
import templates.ContaPoupanca;
import templates.ICliente;
import templates.IConta;

public class ClientePfAbrirConta extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField nome;
	private JTextField sobreNome;
	private JTextField email;
	private JTextField cpf;
	private JTextField txttelefone;
	private JPasswordField senha;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JLabel lblboasvindas;
	private JButton voltar;
	private JButton login;
	private JLabel lblNome;
	private JLabel lblSobreNome;
	private JLabel lblEmail;
	private JLabel lblCpf;
	private MaskFormatter mascaraCpf;
	private JLabel lblSenha;
	private JLabel lblTelefone;
	private  MaskFormatter mascaraTelefone;
	private ClienteDAO clientedao= new ClienteDAO();
	private ContaDAO contadao= new ContaDAO();
	private JLabel lblRua;
	private JLabel lblBairro;
	private	JLabel lblNumero;
	private	JLabel lblEstado;
	private	JLabel lblCidade;
	private	JComboBox<String> comboBox;
	private	JLabel lblTipodeConta;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_1;
	
	public ClientePfAbrirConta()
	{
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 906, 567);
			setResizable(false);
			setVisible(true);
			setLocationRelativeTo(null);
			setTitle("Bank of Taboquinha");
			
			painel= new JPanel();
			painel.setVisible(true);
			painel.setBorder(new EmptyBorder(5,5,5,5));
			setContentPane(painel);
			getContentPane().setLayout(null);
			
			lblboasvindas= new JLabel("Abrir conta");
			lblboasvindas.setFont(new Font("Times New Roman",Font.PLAIN,42));
			lblboasvindas.setBounds(364, 0, 200, 40);
			painel.add(lblboasvindas);
			
			voltar= new JButton("Voltar para tela inicial");
			voltar.setFont(new Font("Times New Roman",Font.PLAIN,16));
			voltar.setBounds(0, 0, 170, 35);
			voltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new TelaInicial();
				}
			});
			painel.add(voltar);
			
			login=new JButton("Ir para tela de login");
			login.setFont(new Font("Times New Roman",Font.PLAIN,16));
			login.setBounds(710, 0, 180, 35);
			login.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new TelaLogin();
				}
			});
			painel.add(login);
			
			lblNome = new JLabel("Nome");
	        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNome.setBounds(30, 48, 60, 40);
	        painel.add(lblNome);
	        
	        nome = new JTextField();
	        nome.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        nome.setBounds(100, 45, 280, 35);
	        painel.add(nome);
	        nome.setColumns(10);
	        
	        lblSobreNome= new JLabel("Sobrenome");
	        lblSobreNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblSobreNome.setBounds(500, 40, 150, 50);
	        painel.add(lblSobreNome);
	        
	        sobreNome= new JTextField();
	        sobreNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        sobreNome.setBounds(610, 45, 280, 35);
	        painel.add(sobreNome);
	       
	        lblEmail= new JLabel("Email");
	        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblEmail.setBounds(30, 15, 200, 250);
	        painel.add(lblEmail);
	        
	        email=new JTextField();
	        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        email.setBounds(100, 125, 280, 35);
	        painel.add(email);
	        
	        lblCpf= new JLabel("CPF");
	        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblCpf.setBounds(500, 40, 200, 200);
	        painel.add(lblCpf);
	        
	        mascaraCpf= new MaskFormatter("###.###.###-##");
			cpf=new JFormattedTextField(mascaraCpf);
		    cpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    cpf.setBounds(610, 122, 280, 35);
		    painel.add(cpf);
		   
		    lblSenha = new JLabel("Senha");
		    lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    lblSenha.setBounds(30, 180, 140, 90);
		    painel.add(lblSenha);
		    
		    senha=new JPasswordField();
		    senha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    senha.setBounds(100, 200, 280, 35);
		    painel.add(senha);
		    
		    lblTelefone= new JLabel("Telefone");
		    lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    lblTelefone.setBounds(500, 183, 90, 85);
		    painel.add(lblTelefone);
		    
		    mascaraTelefone= new MaskFormatter("(##)#.####-####");
		    txttelefone=new JFormattedTextField(mascaraTelefone);
		    txttelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    txttelefone.setBounds(610, 200, 280, 35);
		    painel.add(txttelefone);
		    
		    lblRua = new JLabel("Rua");
		    lblRua.setBounds(544, 340, 46, 23);
		    lblRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblRua);
		    
		    txtRua = new JTextField();
		    txtRua.setBounds(610, 336, 280, 31);
		    txtRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtRua);
		    txtRua.setColumns(10);
		    
		    lblBairro = new JLabel("Bairro");
		    lblBairro.setBounds(30, 340, 60, 23);
		    lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblBairro);
		    
		    txtBairro = new JTextField();
		    txtBairro.setBounds(100, 336, 280, 31);
		    txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtBairro);
		    txtBairro.setColumns(10);
		    
		    lblNumero = new JLabel("Número");
		    lblNumero.setBounds(10, 396, 80, 23);
		    lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblNumero);
		    
		    txtNumero = new JTextField();
		    txtNumero.setBounds(100, 392, 280, 31);
		    txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtNumero);
		    txtNumero.setColumns(10);
		    
		    lblEstado = new JLabel("Estado");
		    lblEstado.setBounds(30, 273, 76, 23);
		    lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblEstado);
		    
		    lblCidade = new JLabel("Cidade");
		    lblCidade.setBounds(510, 267, 76, 35);
		    lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblCidade);
		    
		    comboBox = new JComboBox<String>();
		    comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Conta Corrente", "Conta Poupança"}));
		    comboBox.setBounds(620, 392, 270, 31);
		    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(comboBox);
		    
		    lblTipodeConta = new JLabel("Tipo de conta");
		    lblTipodeConta.setBounds(468, 396, 122, 23);
		    lblTipodeConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblTipodeConta);
		    
		    comboBox_1 = new JComboBox<String>();
		    comboBox_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		String cidades[]=String.valueOf(comboBox_1.getSelectedItem()).split("-");
		    		if(!cidades[0].equalsIgnoreCase("Escolha"))
		    		{
		    			comboBox_2.removeAllItems();
		    			listarCidades(cidades[0]);
		    		}
		    		else
		    		{
		    			comboBox_2.removeAllItems();
		    			comboBox_2.addItem("Escolha");
		    		}
		    	}
		    });
		    comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha"}));
		    listarEstados();
		    comboBox_1.setBounds(100, 269, 280, 31);
		    comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(comboBox_1);
		    
		    comboBox_2= new JComboBox<String>();
		    comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha"}));
		    comboBox_2.setBounds(620, 269, 270, 31);
		    painel.add(comboBox_2);
		    
		    JButton btnAbrir = new JButton("Abrir Conta");
		    btnAbrir.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		String nomeCliente=nome.getText();
		    		String sobreNomeCliente=sobreNome.getText();
		    		String emailCliente=email.getText();
		    		String cpfCliente=cpf.getText().replace(".", "");
		    		String novoCpf=cpfCliente.replace("-", "");
		    		String senhaCliente=senha.getText();
		    		String tel=txttelefone.getText().replace("-", "");
		    		String tele=tel.replace(".", "");
		    		String telefo=tele.replace("(", "");
		    		String telefone=telefo.replace(")", "");
		    		String ruaCliente=txtRua.getText();
		    		String bairroCliente=txtBairro.getText();
		    		String numeroCliente=txtNumero.getText();
		    		String cidade[]=String.valueOf(comboBox_2.getSelectedItem()).split("-");
		    		String estado[]=String.valueOf(comboBox_1.getSelectedItem()).split("-");
		    		if(nomeCliente.isBlank() || sobreNomeCliente.isBlank() || emailCliente.isBlank() || novoCpf.isBlank() || senhaCliente.isBlank()
		    			|| telefone.isBlank() || ruaCliente.isBlank() || bairroCliente.isBlank() || numeroCliente.isBlank() || comboBox_2.getSelectedItem().equals("Escolha") || comboBox_1.getSelectedItem().equals("Escolha"))
		    		{
		    			JOptionPane.showMessageDialog(null, "Voce deve preencher todos os campos!!");
		    		}
		    		else
		    		{
		    			String cidadeCliente=cidade[1];
		    			String estadoCliente= estado[1];
		    			ICliente clientepf=new ClientePF(nomeCliente,sobreNomeCliente,novoCpf,emailCliente,senhaCliente,telefone,ruaCliente,bairroCliente,numeroCliente,cidadeCliente,estadoCliente);
		    			String tipoConta=(String) comboBox.getSelectedItem();
		    			if(tipoConta.equals("Conta Corrente"))
		    			{
		    				IConta conta=new ContaCorrente();
		    				int resultado=clientedao.cadastrarCliente(clientepf);
		    				if(resultado==1)
		    				{
		    					contadao.cadastrarConta(clientepf, conta);
			    				clientedao.cadastrarEndereco(clientepf);
			    				clientedao.cadastrarTelefone(clientepf, telefone);
			    				JOptionPane.showMessageDialog(null, "Bem vindo "+clientepf.pegaNome()+", sua conta foi aberta com sucesso\npor favor anote o número da sua conta: "+conta.pegaNumero()+"\nAgora voce será redirecionado para a tela de login.");
			    				setVisible(false);
			    				new TelaLogin();
		    				}
		    				else
		    				{
		    					JOptionPane.showMessageDialog(null, "CPF já cadastrado");
		    				}
		    			}
		    			else
		    			{
		    				ICliente clientep=new ClientePF(nomeCliente,sobreNomeCliente,novoCpf,emailCliente,senhaCliente,telefone,ruaCliente,bairroCliente,numeroCliente,cidadeCliente,estadoCliente);
		    				IConta conta=new ContaPoupanca();
		    				clientedao.cadastrarCliente(clientepf);
		    				contadao.cadastrarConta(clientepf, conta);
		    				clientedao.cadastrarEndereco(clientep);
		    				clientedao.cadastrarTelefone(clientepf, telefone);
		    				JOptionPane.showMessageDialog(null, "Bem vindo "+clientepf.pegaNome()+", sua conta foi aberta com sucesso\npor favor anote o número da sua conta: "+conta.pegaNumero()+"\nAgora voce será redirecionado para a tela de login.");
		    				setVisible(false);
		    				new TelaLogin();
		    			}
		    		}
		    	}
		    });
		    btnAbrir.setBounds(100, 459, 193, 40);
		    btnAbrir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(btnAbrir);
		    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void listarEstados()
	{
		IConexao conexao=new ConexaoMySql();
		Connection conn = conexao.getConexao();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM estados;");
			ps.execute();
			ResultSet rs = ps.getResultSet();
	        while(rs.next()) 
            {
	         comboBox_1.addItem(String.valueOf(rs.getObject("id"))+"-"+rs.getObject("estado"));
            }
	        ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void listarCidades(String codEstado)
	{
		IConexao conexao=new ConexaoMySql();
		Connection conn = conexao.getConexao();
		PreparedStatement ps;
		Integer i=Integer.parseInt(codEstado);
		try {
			ps = conn.prepareStatement("SELECT * FROM cidades WHERE id_estado=?;");
			ps.setInt(1,i);
			ps.execute();
			ResultSet rs = ps.getResultSet();
	        while(rs.next()) 
            {
	         comboBox_2.addItem(String.valueOf(rs.getObject("id"))+"-"+rs.getObject("cidade"));
            }
	        ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}