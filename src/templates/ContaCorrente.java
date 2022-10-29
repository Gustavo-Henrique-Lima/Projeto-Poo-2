package templates;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import exception.ContaDesativada;
import exception.DepositoInvalido;
import exception.DesativarContaComSaldo;
import exception.SaldoInsuficiente;

public class ContaCorrente implements IConta,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String numeroConta;
	private String agencia;
	private BigDecimal saldo;
	private Byte status;
	private Date dataAbertura;
	
	public ContaCorrente(String numero,BigDecimal valor, Byte status)
	{
		this.numeroConta=numero;
		this.saldo=valor;
		this.status=status;
	}
	public ContaCorrente()
	{
		this.numeroConta=gerarNumero();
		this.agencia="2";
		this.saldo=BigDecimal.ZERO;
		this.status=1;
		this.dataAbertura=Date.valueOf(LocalDate.now());
	}
	public String gerarNumero() 
	{
		String NumeroConta="";
		for(int i=0;i<=5;i++)
		{
			int numero=((int)(10*Math.random()));
			NumeroConta+=numero;
		}
		return NumeroConta;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public Byte getStatus() {
		return status;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	@Override
	public int hashCode() {
		return Objects.hash(numeroConta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(numeroConta, other.numeroConta);
	}
	@Override
	public String toString() {
		return "ContaCorrente [numeroConta=" + numeroConta + ", agencia=" + agencia + ", saldo=" + saldo + ", status="
				+ status + ", dataAbertura=" + dataAbertura + "]";
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	@Override
	public void sacar(BigDecimal valor) throws SaldoInsuficiente, ContaDesativada 
	{
		BigDecimal taxa=ContaPoupanca.toBigDecimal(taxaSaqueContaCorrente);
		BigDecimal valorTaxado=valor.add(taxa.multiply(valor));
		if(valor.compareTo(BigDecimal.ZERO)!=0 && valorTaxado.compareTo(this.saldo)<=0 && this.status==1)
		{
			setSaldo(getSaldo().subtract(valorTaxado));
		}
		else if(this.status==0)
		{
			throw new ContaDesativada("A conta se encontra desativada");
		}
		else
		{
			throw new SaldoInsuficiente("Saldo insuficiente");
		}
	}
	@Override
	public void depositar(BigDecimal valor) throws DepositoInvalido, ContaDesativada 
	{
		if(valor.compareTo(BigDecimal.ZERO)==0)
		{
			throw new DepositoInvalido("O valor de depósito deve ser maior do que zero");
		}
		else if(valor.compareTo(BigDecimal.ZERO)!=0 && this.status==1)
		{
			setSaldo(this.saldo.add(valor));
		}
		else if(this.status==0)
		{
			throw new ContaDesativada("A conta se encontra desativada");
		}
	}
	@Override
	public void transferir(BigDecimal valor, IConta contaDestino) 
	{
		BigDecimal taxa=ContaPoupanca.toBigDecimal(taxaAdmContaCorrente);
		BigDecimal valorTaxado=valor.add(taxa.multiply(valor));
		if(contaDestino instanceof ContaCorrente && valor.compareTo(BigDecimal.ZERO)!=0 && valorTaxado.compareTo(this.saldo)>=0)
		{
			
			try {
				setSaldo(getSaldo().subtract(valorTaxado));
				contaDestino.depositar(valor);
			} catch (DepositoInvalido | ContaDesativada e) {
				e.getMessage();
			}
		}
		else if(this.saldo.compareTo(valor)>=0)
		{
			setSaldo(getSaldo().subtract(valorTaxado));
			try {
				contaDestino.depositar(valor);
			} catch (DepositoInvalido | ContaDesativada e) {
				e.getMessage();
			}
		}
	}
	@Override
	public void desativarConta() throws ContaDesativada, DesativarContaComSaldo 
	{
		if(this.status==1 && this.saldo.compareTo(BigDecimal.ZERO)==0)
		{
			this.status=0;
		}
		else if(this.saldo.compareTo(BigDecimal.ZERO)!=0)
		{
			throw new DesativarContaComSaldo("Você deve zerar seu saldo antes de desativar sua conta");
		}
		else if(this.status==0)
		{
			throw new ContaDesativada("A conta já se encontra desativada");
		}
	}
	@Override
	public String pegaNumero() {
		return this.numeroConta;
	}
	@Override
	public BigDecimal pegaSaldo() {
		return this.saldo;
	}
	@Override
	public String pegaAgencia() {
		return this.agencia;
	}
	@Override
	public Byte pegaStatus() {
		return this.status;
	}
	@Override
	public BigDecimal toBigDecimaL(String valor) {
		return new BigDecimal(valor);
	}
	@Override
	public java.sql.Date pegaData() {
		return this.dataAbertura;
	}
}