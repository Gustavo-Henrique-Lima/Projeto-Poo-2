package templates;

import java.math.BigDecimal;

import exception.*;

public interface ICliente {
	public void adicionarConta(IConta conta) throws ContaJaCadastrada;
	public void removerConta(IConta conta) throws ContaJaRemovida;
	public void inserirTelefone(String telefone)throws TelefoneJaCadastrado;
	public void removerTelefone(String telefone) throws TelefoneJaRemovido;
	public void alterarRua(String rua);
	public void alterarBairro(String bairro);
	public void alterarNumero(String numero);
	public void alterarCidade(String cidade);
	public void alterarEstado(String estado);
	public void contratarCartao(Byte opcao) throws ClienteJaPossuiCartao;
	public String pegaNome();
	public String pegaSobrenome();
	public String pegalogin();
	public String pegaEmail();
	public String pegaSenha();
	public Byte pegaCartao();
	public String pegaRua();
	public String pegaBairro();
	public String pegaNumero();
	public String pegaCidade();
	public String pegaEstado();
	public BigDecimal pegalimiteCartao();
	public void adicionarEndereco(Endereco end);
}
