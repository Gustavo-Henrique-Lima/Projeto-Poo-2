package exception;

public class ContaDesativada extends Exception
{
	private static final long serialVersionUID = 1L;
	public ContaDesativada(String mensagem)
	{
		super(mensagem);
	}
}