package formatting;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class SoNumeros extends PlainDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		// TODO Auto-generated method stub
		super.insertString(offs, str.replaceAll("[^0-9]", ""), a);
	}
}
