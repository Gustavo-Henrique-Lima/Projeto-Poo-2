package exception;

public class SaldoInsuficiente extends Exception
{
	private static final long serialVersionUID = 1L;
	public SaldoInsuficiente(String mensagem)
	{
		super(mensagem);
	}
}