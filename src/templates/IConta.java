package templates;

import java.math.BigDecimal;
import java.sql.Date;

import exception.*;
public interface IConta {
	static final float taxaSaqueContaCorrente= 0.02f;
	static final float taxaAdmContaCorrente=0.035f;
	public void sacar(BigDecimal valor) throws SaldoInsuficiente,ContaDesativada;
	public void depositar(BigDecimal valor)throws DepositoInvalido,ContaDesativada;
	public void transferir(BigDecimal valor, IConta contaDestino);
	public void desativarConta() throws ContaDesativada,DesativarContaComSaldo;
	public String pegaNumero();
	public BigDecimal pegaSaldo();
	public String pegaAgencia();
	public Byte pegaStatus();
	public Date pegaData();
	public void setSaldo(BigDecimal valor);
	public void setStatus(Byte opcao);
	public BigDecimal toBigDecimaL(String valor);
}
