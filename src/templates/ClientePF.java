package templates;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import exception.ClienteJaPossuiCartao;
import exception.ContaJaCadastrada;
import exception.ContaJaRemovida;
import exception.TelefoneJaCadastrado;
import exception.TelefoneJaRemovido;


public class ClientePF implements ICliente,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String ultimoNome;
	private String cpf;
	private String email;
	private String senha;
	private Byte cartaoCredito;
	private Endereco endereco;
	private Set <String> telefones= new HashSet<>();
	private List <IConta> contas= new ArrayList<IConta>();
	
	public ClientePF()
	{
	}
	public ClientePF(String nome, String ultimoNome, String cpf, String email, String senha, Byte cartao)
	{	
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.cartaoCredito =cartao;
	}
	public ClientePF(String nome, String ultimoNome, String cpf, String email, String senha,String telefone,String rua, String bairro,String numero,String cidade,String estado) 
	{
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		telefones.add(telefone);
		this.cartaoCredito =0;
		this.endereco=new Endereco(rua, bairro, numero, cidade, estado);
	}
	public ClientePF(String nome, String ultimoNome, String cpf, String email, String senha, Byte cartaoCredito,String rua, String bairro,String numero,String cidade,String estado) 
	{
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.cartaoCredito =cartaoCredito;
		this.endereco=new Endereco(rua, bairro, numero, cidade, estado);
	}
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientePF other = (ClientePF) obj;
		return Objects.equals(cpf, other.cpf);
	}
	public String getNome() 
	{
		return nome;
	}

	public String getUltimoNome() 
	{
		return ultimoNome;
	}
	public String getCpf() 
	{
		return cpf;
	}

	public String getEmail() 
	{
		return email;
	}

	public String getSenha() 
	{
		return senha;
	}

	public Byte getCartaoCredito() 
	{
		return cartaoCredito;
	}

	public Endereco getEndereco() 
	{
		return endereco;
	}

	public Set<String> getTelefones() 
	{
		return telefones;
	}

	public List<IConta> getContas() 
	{
		return contas;
	}

	@Override
	public String toString() 
	{
		return "ClientePF [nome=" + nome + ", ultimoNome=" + ultimoNome + ", cpf=" + cpf + ", email=" + email
				+ ", senha=" + senha + ", cartaoCredito=" + cartaoCredito + ", endereco=" + endereco + ", telefones="
				+ telefones + ", contas=" + contas + "]";
	}

	@Override
	public void adicionarConta(IConta conta) throws ContaJaCadastrada {
		if(contas.contains(conta))
		{
			throw new ContaJaCadastrada("Conta já cadastrada");
		}
		else
		{
			contas.add(conta);
		}
	}

	@Override
	public void removerConta(IConta conta) throws ContaJaRemovida {
		if(!contas.contains(conta))
		{
			throw new ContaJaRemovida("Conta já removida");
		}
		else
		{
			contas.remove(conta);
		}
	}

	@Override
	public void inserirTelefone(String telefone) throws TelefoneJaCadastrado {
		if(telefones.contains(telefone))
		{
			throw new TelefoneJaCadastrado("Telefone já cadastrado");
		}
		else
		{
			telefones.add(telefone);
		}
	}

	@Override
	public void removerTelefone(String telefone) throws TelefoneJaRemovido {
		// TODO Auto-generated method stub
		if(!telefones.contains(telefone))
		{
			throw new TelefoneJaRemovido("Telefone já removido");
		}
		else
		{
			telefones.remove(telefone);
		}
	}

	@Override
	public void alterarRua(String rua) {
		// TODO Auto-generated method stub
		endereco.setRua(rua);
	}

	@Override
	public void alterarBairro(String bairro) {
		// TODO Auto-generated method stub
		endereco.setBairro(bairro);
	}

	@Override
	public void alterarNumero(String numero) {
		// TODO Auto-generated method stub
		endereco.setNumero(numero);
	}

	@Override
	public void alterarCidade(String cidade) {
		// TODO Auto-generated method stub
		endereco.setCidade(cidade);
	}

	@Override
	public void alterarEstado(String estado) {
		endereco.setEstado(estado);
		
	}

	@Override
	public void contratarCartao(Byte opcao) throws ClienteJaPossuiCartao{
		if(this.cartaoCredito==0)
		{
			this.cartaoCredito=1;
		}
		else
		{
			throw new ClienteJaPossuiCartao("Cliente já possui cartão de crédito");
		}
	}

	@Override
	public String pegaNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public String pegaSobrenome() {
		// TODO Auto-generated method stub
		return this.ultimoNome;
	}

	@Override
	public String pegalogin() {
		// TODO Auto-generated method stub
		return this.cpf;
	}

	@Override
	public String pegaEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String pegaSenha() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public Byte pegaCartao() {
		// TODO Auto-generated method stub
		return this.cartaoCredito;
	}

	@Override
	public String pegaRua() {
		// TODO Auto-generated method stub
		return endereco.getRua();
	}

	@Override
	public String pegaBairro() {
		// TODO Auto-generated method stub
		return endereco.getBairro();
	}

	@Override
	public String pegaNumero() {
		// TODO Auto-generated method stub
		return endereco.getNumero();
	}

	@Override
	public String pegaCidade() {
		// TODO Auto-generated method stub
		return endereco.getCidade();
	}

	@Override
	public String pegaEstado() {
		return endereco.getEstado();
	}

	@Override
	public BigDecimal pegalimiteCartao() {
		BigDecimal limite=BigDecimal.valueOf(500);
		return limite;
	}
	@Override
	public void adicionarEndereco(Endereco end) {
		// TODO Auto-generated method stub
		this.endereco=end;
	}
}
