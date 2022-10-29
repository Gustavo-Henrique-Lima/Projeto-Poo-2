package exception;

public class DepositoInvalido extends Exception
{
	private static final long serialVersionUID = 1L;
	public DepositoInvalido(String mensagem)
	{
		super(mensagem);
	}
}