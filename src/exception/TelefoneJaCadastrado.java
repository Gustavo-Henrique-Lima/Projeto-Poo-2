package exception;

public class TelefoneJaCadastrado extends Exception
{
	private static final long serialVersionUID = 1L;
	public TelefoneJaCadastrado(String mensagem)
	{
		super(mensagem);
	}
}