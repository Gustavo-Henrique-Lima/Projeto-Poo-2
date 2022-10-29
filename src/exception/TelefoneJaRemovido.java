package exception;

public class TelefoneJaRemovido extends Exception
{
	private static final long serialVersionUID = 1L;
	public TelefoneJaRemovido(String mensagem)
	{
		super(mensagem);
	}
}