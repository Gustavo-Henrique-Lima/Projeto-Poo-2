package persistence;

import java.math.BigDecimal;

import templates.ICliente;
import templates.IConta;

public interface IContaDAO 
{
	public void cadastrarConta(ICliente cliente,IConta conta);
	public IConta localizarContaCc(ICliente cliente);
	public IConta localizarContaCp(ICliente cliente);
	public void sacar(ICliente cliente,IConta conta, BigDecimal valor);
	public void desativarConta(IConta conta, ICliente cliente);
}