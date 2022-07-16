package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaInicial extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JButton abrirConta;
	private JButton entrar;
	private	JLabel lblNome;
	private	ImageIcon imageIcon;
	private	JLabel imagem;
	private	JMenu menuContato;
	private	JMenuItem contatos;
	private	JMenu menuSobre;
	private	JMenuItem sobre;
	private	JMenuBar barra;
	
	public TelaInicial()
	{
		setTitle("Bank of Taboquinha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400,0,650,650);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		painel= new JPanel();
		painel.setVisible(true);
		painel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(painel);
		setLayout(null);
		
		lblNome= new JLabel("Bank Of Taboquinha");
		lblNome.setFont(new Font("Times New Roman",Font.PLAIN,26));
		lblNome.setBounds(325,10,220,150);
		painel.add(lblNome);
		
		imageIcon= new ImageIcon("C:\\Users\\gusta\\eclipse-workspace\\projeto\\src\\imagens\\cartao.png");
		imagem= new JLabel(imageIcon);
		imagem.setBounds(0, 0, 300, 600);
		painel.add(imagem);
		
		abrirConta= new JButton("Abrir conta");
		abrirConta.setBounds(325, 400, 130, 40);
		abrirConta.setFont(new Font("Times New Roman",Font.PLAIN,18));
		abrirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TipoCliente();
			}
		});
		painel.add(abrirConta);
		
		entrar= new JButton("Entrar na minha conta");
		entrar.setBounds(325, 460, 200, 40);
		entrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//new TipoClienteLogin();
				new TelaLogin();
			}
		});
		entrar.setFont(new Font("Times New Roman",Font.PLAIN,18));
		painel.add(entrar);
		informacoes();
	}
	private void informacoes()
	{
		menuContato= new JMenu("Contato");
		contatos= new JMenuItem("Contatos");
		contatos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Capitais e regiões metropolitanas: 0800 3375\n Demais regiões: 0800 3684\n E-mail: bankoftaboquinha@taboquinha.com");
			}
		});
		menuContato.add(contatos);
		menuSobre= new JMenu("Sobre");
		sobre= new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Sobre();
			}
		});
		menuSobre.add(sobre);
		barra= new JMenuBar();
		setJMenuBar(barra);
		barra.add(menuContato);
		barra.add(menuSobre);
	}
}
