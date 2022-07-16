package exception;

public class ClienteJaCadastrado extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteJaCadastrado(String mensagem)
	{
		super(mensagem);
	}
}
