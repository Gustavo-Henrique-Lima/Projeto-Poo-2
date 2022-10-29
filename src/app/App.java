package app;

import javax.swing.SwingUtilities;

import screens.TelaInicial;

public class App {
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				new TelaInicial();
			}
		});
	}
}