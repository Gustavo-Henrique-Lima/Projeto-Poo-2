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
import formatting.SoNumeros;
import persistence.ClienteDAO;
import persistence.ConexaoMySql;
import persistence.ContaDAO;
import persistence.IConexao;
import templates.ClientePJ;
import templates.ContaCorrente;
import templates.ContaPoupanca;
import templates.ICliente;
import templates.IConta;

public class ClientePjAbrirConta extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField nome;
	private JTextField razaoSocial;
	private JTextField email;
	private JTextField cnpj;
	private JTextField txttelefone;
	private JPasswordField senha;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private	JLabel lblboasvindas;
	private	JButton voltar;
	private	JButton login;
	private	JLabel lblNomeFantasia;
	private	JLabel lblRazaoSocial;
	private JLabel lblEmail;
	private	JLabel lblCnpj;
	private	JLabel lblSenha;
	private JLabel lblTelefone;
	private	MaskFormatter mascaraTelefone;
	private	JLabel lblRua;
	private	JLabel lblBairro;
	private	JLabel lblNumero;
	private	JLabel lblEstado;
	private	JLabel lblCidade;
	private	JComboBox comboBox;
	private	JLabel lblTipodeConta;
	private	JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private	JButton btnAbrir;
	private ClienteDAO clientedao= new ClienteDAO();
	private ContaDAO contadao= new ContaDAO();
	public ClientePjAbrirConta()
	{
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 906, 554);
			setResizable(false);
			setVisible(true);
			setTitle("Bank of Taboquinha");
			setLocationRelativeTo(null);
			
			painel= new JPanel();
			painel.setVisible(true);
			painel.setBorder(new EmptyBorder(5,5,5,5));
			setContentPane(painel);
			getContentPane().setLayout(null);
			
			lblboasvindas= new JLabel("Abrir conta");
			lblboasvindas.setFont(new Font("Times New Roman",Font.PLAIN,42));
			lblboasvindas.setBounds(364, 0, 200, 35);
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
			login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					new TelaLogin();
				}
			});
			login.setBounds(710, 0, 180, 35);
			painel.add(login);
			
			lblNomeFantasia = new JLabel("Nome Fantasia");
			lblNomeFantasia.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNomeFantasia.setBounds(0, 43, 133, 40);
	        painel.add(lblNomeFantasia);
	        
	        nome = new JTextField();
	        nome.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        nome.setBounds(135, 46, 280, 35);
	        painel.add(nome);
	        nome.setColumns(10);
	        
	        lblRazaoSocial= new JLabel("Razão Social");
	        lblRazaoSocial.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblRazaoSocial.setBounds(468, 38, 150, 50);
	        painel.add(lblRazaoSocial);
	        
	        razaoSocial= new JTextField();
	        razaoSocial.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        razaoSocial.setBounds(610, 45, 280, 35);
	        painel.add(razaoSocial);
	       
	        lblEmail= new JLabel("Email");
	        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblEmail.setBounds(30, 15, 200, 250);
	        painel.add(lblEmail);
	        
	        email=new JTextField();
	        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        email.setBounds(100, 125, 280, 35);
	        painel.add(email);
	        
	        lblCnpj= new JLabel("CNPJ");
	        lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblCnpj.setBounds(500, 40, 200, 200);
	        painel.add(lblCnpj);
	        
			cnpj=new JTextField();
		    cnpj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    cnpj.setDocument(new SoNumeros());
		    cnpj.setBounds(610, 122, 280, 35);
		    painel.add(cnpj);
		   
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
		    lblTelefone.setBounds(500, 180, 90, 90);
		    painel.add(lblTelefone);
		    
		    mascaraTelefone= new MaskFormatter("(##)#.####-####");
		    txttelefone=new JFormattedTextField(mascaraTelefone);
		    txttelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    txttelefone.setBounds(610, 200, 280, 35);
		    painel.add(txttelefone);
		    
		    lblRua= new JLabel("Rua");
		    lblRua.setBounds(44, 404, 46, 23);
		    lblRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblRua);
		    
		    txtRua = new JTextField();
		    txtRua.setBounds(100, 400, 280, 31);
		    txtRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtRua);
		    txtRua.setColumns(10);
		    
		    lblBairro= new JLabel("Bairro");
		    lblBairro.setBounds(515, 340, 60, 23);
		    lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblBairro);
		    
		    lblNumero= new JLabel("Número");
		    lblNumero.setBounds(10, 340, 80, 23);
		    lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblNumero);
		    
		    txtNumero = new JTextField();
		    txtNumero.setBounds(100, 332, 280, 31);
		    txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtNumero);
		    txtNumero.setColumns(10);
		    
		    lblEstado= new JLabel("Estado");
		    lblEstado.setBounds(14, 277, 76, 23);
		    lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblEstado);
		    
		    lblCidade= new JLabel("Cidade");
		    lblCidade.setBounds(510, 277, 76, 23);
		    lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(lblCidade);
		    
		    txtBairro = new JTextField();
		    txtBairro.setBounds(610, 336, 280, 31);
		    txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(txtBairro);
		    txtBairro.setColumns(10);
		    
		    comboBox= new JComboBox();
		    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Conta Corrente", "Conta Poupança"}));
		    comboBox.setBounds(610, 400, 270, 31);
		    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(comboBox);
		    
		    lblTipodeConta= new JLabel("Tipo de conta");
		    lblTipodeConta.setBounds(468, 400, 122, 23);
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
		    
		    comboBox_2= new JComboBox<String>();
		    comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Escolha"}));
		    comboBox_2.setBounds(620, 269, 270, 31);
		    painel.add(comboBox_2);
		    
		    comboBox_1.setBounds(100, 269, 280, 31);
		    comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    painel.add(comboBox_1);
		    
		    btnAbrir= new JButton("Abrir Conta");
		    btnAbrir.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		String nomeCliente=nome.getText();
		    		String razaoSocialCliente=razaoSocial.getText();
		    		String emailCliente=email.getText();
		    		String cnpjCliente=cnpj.getText();
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
		    		if(nomeCliente.isBlank() || razaoSocialCliente.isBlank() || emailCliente.isBlank() || senhaCliente.isBlank()
		    			|| telefone.isBlank() || ruaCliente.isBlank() || bairroCliente.isBlank() || numeroCliente.isBlank() || comboBox_2.getSelectedItem().equals("Escolha") || comboBox_1.getSelectedItem().equals("Escolha"))
		    		{
		    			JOptionPane.showMessageDialog(null, "Voce deve preencher todos os campos!!");
		    		}
		    		else if(cnpjCliente.length()!=14)
		    		{
		    			JOptionPane.showMessageDialog(null, "CNPJ inválido");
		    		}
		    		else
		    		{
		    			String cidadeCliente=cidade[1];
		    			String estadoCliente= estado[1];
		    			ICliente clientepj= new ClientePJ(nomeCliente, cnpjCliente, razaoSocialCliente, emailCliente, senhaCliente, telefone, ruaCliente, bairroCliente, numeroCliente, cidadeCliente, estadoCliente);
		    			String tipoConta=(String) comboBox.getSelectedItem();
		    			if(tipoConta.equals("Conta Corrente"))
		    			{
		    				IConta conta=new ContaCorrente();
		    				int resultado=clientedao.cadastrarCliente(clientepj);
		    				if(resultado==1)
		    				{
		    					contadao.cadastrarConta(clientepj, conta);
			    				clientedao.cadastrarEndereco(clientepj);
			    				clientedao.cadastrarTelefone(clientepj, telefone);
			    				JOptionPane.showMessageDialog(null, "Bem vindo "+clientepj.pegaNome()+", sua conta foi aberta com sucesso\npor favor anote o número da sua conta: "+conta.pegaNumero()+"\nAgora voce será redirecionado para a tela de login.");
			    				setVisible(false);
			    				new TelaLogin();
		    				}
		    				else
		    				{
		    					JOptionPane.showMessageDialog(null, "CNPJ já cadastrado");
		    				}
		    			}
		    			else
		    			{
		    				ICliente clientep= new ClientePJ(nomeCliente, cnpjCliente, razaoSocialCliente, emailCliente, senhaCliente, telefone, ruaCliente, bairroCliente, numeroCliente, cidadeCliente, estadoCliente);
		    				IConta conta=new ContaPoupanca();
		    				clientedao.cadastrarCliente(clientep);
		    				contadao.cadastrarConta(clientepj, conta);
		    				clientedao.cadastrarEndereco(clientep);
		    				clientedao.cadastrarTelefone(clientepj, telefone);
		    				JOptionPane.showMessageDialog(null, "Bem vindo "+clientep.pegaNome()+", sua conta foi aberta com sucesso\npor favor anote o número da sua conta:"+conta.pegaNumero()+"\nAgora voce será redirecionado para a tela de login.");
		    				setVisible(false);
		    				new TelaLogin();
		    			}
		    		}
		    	}
		    });
		    btnAbrir.setBounds(100, 454, 200, 50);
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
