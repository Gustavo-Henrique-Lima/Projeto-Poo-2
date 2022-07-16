package app;

import javax.swing.SwingUtilities;

import screens.TelaInicial;

public class App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new TelaInicial();
			}
		});
	}
}
