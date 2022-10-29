package persistence;

import templates.Endereco;
import templates.ICliente;

public interface IClienteDAO 
{
	public int cadastrarCliente(ICliente cliente);
	public void cadastrarEndereco(ICliente cliente);
	public ICliente localizarClientePf(String login,String senha);
	public ICliente localizarClientePj(String login,String senha);
	public Endereco localizarEndereco(ICliente cliente);
	public void cadastrarTelefone(ICliente cliente,String telefone);
}