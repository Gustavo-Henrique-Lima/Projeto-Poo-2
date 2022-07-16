package exception;

public class ClienteJaPossuiCartao extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteJaPossuiCartao(String mensagem)
	{
		super(mensagem);
	}
}
