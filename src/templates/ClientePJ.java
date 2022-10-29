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


public class ClientePJ implements ICliente,Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeFantasia;
	private String cnpj;
	private String razaoSocial;
	private String email;
	private String senha;
	private Endereco endereco;
	private Set <String> telefones= new HashSet<>();
	private List <IConta> contas= new ArrayList<IConta>();
	public ClientePJ()
	{
	}
	public ClientePJ(String nomeFantasia, String cnpj, String razaoSocial, String email, String senha)
	{
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.senha = senha;
	}
	public ClientePJ(String nomeFantasia, String cnpj, String razaoSocial, String email, String senha, String telefone,String rua, String bairro,String numero,String cidade,String estado) 
	{
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.senha = senha;
		telefones.add(telefone);
		this.endereco=new Endereco(rua, bairro, numero, cidade, estado);
	}
	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientePJ other = (ClientePJ) obj;
		return Objects.equals(cnpj, other.cnpj);
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public Set<String> getTelefones() {
		return telefones;
	}
	public List<IConta> getContas() {
		return contas;
	}
	@Override
	public String toString() {
		return "ClientePJ [nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial
				+ ", email=" + email + ", senha=" + senha + ", endereco=" + endereco + ", telefones=" + telefones
				+ ", contas=" + contas + "]";
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
	public void removerConta(IConta conta) throws ContaJaRemovida 
	{
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
	public void inserirTelefone(String telefone) throws TelefoneJaCadastrado 
	{
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
	public void removerTelefone(String telefone) throws TelefoneJaRemovido 
	{
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
		endereco.setRua(rua);
	}
	@Override
	public void alterarBairro(String bairro) {
		endereco.setBairro(bairro);
	}
	@Override
	public void alterarNumero(String numero) {
		endereco.setNumero(numero);
	}
	@Override
	public void alterarCidade(String cidade) {
		endereco.setCidade(cidade);
	}
	@Override
	public void alterarEstado(String estado) {
		endereco.setEstado(estado);
	}
	@Override
	public void contratarCartao(Byte opcao) throws ClienteJaPossuiCartao {
	}
	@Override
	public String pegaNome() {
		return this.nomeFantasia;
	}
	@Override
	public String pegaSobrenome() {
		return this.razaoSocial;
	}
	@Override
	public String pegalogin() {
		return this.cnpj;
	}
	@Override
	public String pegaEmail() {
		return this.email;
	}
	@Override
	public String pegaSenha() {
		return this.senha;
	}
	@Override
	public Byte pegaCartao() {
		return null;
	}
	@Override
	public String pegaRua() {
		return endereco.getRua();
	}
	@Override
	public String pegaBairro() {
		return endereco.getBairro();
	}
	@Override
	public String pegaNumero() {
		return endereco.getNumero();
	}
	@Override
	public String pegaCidade() {
		return endereco.getCidade();
	}
	@Override
	public String pegaEstado() {
		return endereco.getEstado();
	}
	@Override
	public BigDecimal pegalimiteCartao() {
		return null;
	}
	@Override
	public void adicionarEndereco(Endereco end) {
		this.endereco=end;
	}
}