package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class TipoCliente extends JFrame{
	private JLabel lblPf;
	private JButton btPf;
	private JLabel lblPJ;
	private JButton btnPessoaJuridica;
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	
	public TipoCliente() 
	{
		setTitle("Bank of Taboquinha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400,0,650,440);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		painel= new JPanel();
		painel.setVisible(true);
		painel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(painel);
		getContentPane().setLayout(null);
		
		lblPf = new JLabel("");
		lblPf.setIcon(new ImageIcon(TipoCliente.class.getResource("/images/PF.jpg")));
		lblPf.setBounds(83, 24, 175, 258);
		painel.add(lblPf);
		
		btPf = new JButton("Pessoa Física");
		btPf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ClientePfAbrirConta();
			}
		});
		btPf.setBounds(83, 302, 175, 23);
		painel.add(btPf);
		
		lblPJ = new JLabel("");
		lblPJ.setIcon(new ImageIcon(TipoCliente.class.getResource("/images/pessoa-juridica.jpg")));
		lblPJ.setBounds(376, 24, 175, 258);
		painel.add(lblPJ);
		
		btnPessoaJuridica = new JButton("Pessoa Jurídica");
		btnPessoaJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ClientePjAbrirConta();
			}
		});
		btnPessoaJuridica.setBounds(376, 302, 175, 23);
		painel.add(btnPessoaJuridica);
	}
}