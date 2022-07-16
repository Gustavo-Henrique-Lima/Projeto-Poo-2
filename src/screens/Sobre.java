package screens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Sobre extends JFrame{
	
	private JPanel painel;
	private JLabel lblNome;
	private JTextArea area;
	private JButton voltar;
	private static final long serialVersionUID = 1L;
	public Sobre()
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
		lblNome.setBounds(215,10,600,150);
		painel.add(lblNome);
		
		area= new JTextArea("daojisadjisajio");
		area.setEditable(false);
		area.setFont(new Font("Times New Roman",Font.PLAIN,26));
		area.setBounds(0,100,400,150);
		painel.add(area);

		voltar= new JButton("Voltar");
		voltar.setFont(new Font("Times New Roman",Font.PLAIN,18));
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TelaInicial tela= new TelaInicial();
				tela.setVisible(true);
			}
		});
		voltar.setBounds(0, 0, 40, 40);
		painel.add(voltar);
	}
}
