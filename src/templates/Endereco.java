package templates;

import java.util.Objects;

public class Endereco {
	private String rua;
	private String bairro;
	private String numero;
	private String cidade;
	private String estado;
	public Endereco(String rua, String bairro, String numero, String cidade, String estado) 
	{
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
	}
	@Override
	public int hashCode() 
	{
		return Objects.hash(bairro, cidade, estado, numero, rua);
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(estado, other.estado) && Objects.equals(numero, other.numero)
				&& Objects.equals(rua, other.rua);
	}
	public String getRua() 
	{
		return rua;
	}
	public void setRua(String rua) 
	{
		this.rua = rua;
	}
	public String getBairro() 
	{
		return bairro;
	}
	public void setBairro(String bairro) 
	{
		this.bairro = bairro;
	}
	public String getNumero() 
	{
		return numero;
	}
	public void setNumero(String numero) 
	{
		this.numero = numero;
	}
	public String getCidade() 
	{
		return cidade;
	}
	public void setCidade(String cidade) 
	{
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Endereco:\nrua: " + rua + "\nbairro: " + bairro + "\nnúmero: " + numero + "\ncidade: " + cidade
				+ "\nestado: " + estado;
	}
}
