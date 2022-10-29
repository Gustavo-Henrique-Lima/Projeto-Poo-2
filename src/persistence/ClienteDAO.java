package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import templates.ClientePF;
import templates.ClientePJ;
import templates.Endereco;
import templates.ICliente;

public class ClienteDAO implements IClienteDAO{
	IConexao conexao;
	Connection conn;
	PreparedStatement ps;
	public ClienteDAO()
	{
		conexao=new ConexaoMySql();
	}
	
	@Override
	public int cadastrarCliente(ICliente cliente) 
	{
		if(cliente instanceof ClientePJ)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO clientepj VALUES (?, ?, ?, ?,?);");
				ps.setString(1, cliente.pegaNome());
				ps.setString(2, cliente.pegalogin());
				ps.setString(3, cliente.pegaSobrenome());
				ps.setString(4, cliente.pegaEmail());
				ps.setString(5, cliente.pegaSenha());
				ps.execute();
				conn.close();
				return 1;
			} catch (SQLException e){
				e.printStackTrace();
				return 0;
			}
		}
		else
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO clientepf VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, cliente.pegaNome());
				ps.setString(2, cliente.pegaSobrenome());
				ps.setString(3, cliente.pegalogin());
				ps.setString(4, cliente.pegaEmail());
				ps.setString(5, cliente.pegaSenha());
				ps.setByte(6, cliente.pegaCartao());
				ps.execute();
				conn.close();
				return 1;
			} catch (SQLException e){
				e.printStackTrace();
				return 0;
			}
		}
	}

	@Override
	public void cadastrarEndereco(ICliente cliente) 
	{
		if(cliente instanceof ClientePF)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO enderecoclientepf VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, cliente.pegaRua());
				ps.setString(2, cliente.pegaBairro());
				ps.setString(3, cliente.pegaNumero());
				ps.setString(4, cliente.pegaCidade());
				ps.setString(5, cliente.pegaEstado());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO enderecoclientepj VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, cliente.pegaRua());
				ps.setString(2, cliente.pegaBairro());
				ps.setString(3, cliente.pegaNumero());
				ps.setString(4, cliente.pegaCidade());
				ps.setString(5, cliente.pegaEstado());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ICliente localizarClientePf(String login, String senha) 
	{
		conn = conexao.getConexao();
		ICliente c1=null;
		try {
			ps = conn.prepareStatement("SELECT * FROM clientepf WHERE cpf = ? and senha = ?;");
			ps.setString(1, login);
	        ps.setString(2, senha);
	        ps.execute();
	        ResultSet rs = ps.getResultSet();
	        if(rs.next()) 
            {
	            String nome = rs.getString(1);
	            String sobreNome = rs.getString(2);
	            String cpf = rs.getString(3);
	            String email = rs.getString(4);
	            String senhaa= rs.getString(5);
	            Byte cartao = rs.getByte(6);
	            c1= new ClientePF(nome, sobreNome,cpf, email, senhaa, cartao);
            }
            ps.close();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return c1;
	}

	@Override
	public ICliente localizarClientePj(String login, String senha) 
	{
		Connection conn = conexao.getConexao();
		ICliente c1=null;
		try {
			ps = conn.prepareStatement("SELECT * FROM clientepj WHERE cnpj = ? and senha = ?;");
			ps.setString(1, login);
	        ps.setString(2, senha);
	        ps.execute();
	        ResultSet rs = ps.getResultSet();
	        if(rs.next()) 
            {
	            String nomeFantasia = rs.getString(1);
	            String cnpj = rs.getString(2);
	            String razaoSocial = rs.getString(3);
	            String email = rs.getString(4);
	            String senhaa= rs.getString(5);
	            c1= new ClientePJ(nomeFantasia,cnpj,razaoSocial,email,senhaa);
            }
            ps.close();
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return c1;
	}

	@Override
	public Endereco localizarEndereco(ICliente cliente) 
	{
		conn = conexao.getConexao();
		Endereco end=null;
		if(cliente instanceof ClientePF)
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM enderecoclientepf WHERE cpf_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        if(rs.next()) 
	            {
		            String rua = rs.getString(1);
		            String bairro = rs.getString(2);
		            String numero = rs.getString(3);
		            String cidade = rs.getString(4);
		            String estado= rs.getString(5);
		            end= new Endereco(rua, bairro, numero, cidade, estado);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM enderecoclientepj WHERE cnpj_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        if(rs.next()) 
	            {
		            String rua = rs.getString(1);
		            String bairro = rs.getString(2);
		            String numero = rs.getString(3);
		            String cidade = rs.getString(4);
		            String estado= rs.getString(5);
		            end= new Endereco(rua, bairro, numero, cidade, estado);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return end;
	}

	@Override
	public void cadastrarTelefone(ICliente cliente,String telefone) 
	{
		conn = conexao.getConexao();
		if(cliente instanceof ClientePJ)
		{
			try {
				ps = conn.prepareStatement("INSERT INTO telefonesclientepj VALUES (?, ?);");
				ps.setString(1, telefone);
				ps.setString(2, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				ps = conn.prepareStatement("INSERT INTO telefonesclientepf VALUES (?, ?);");
				ps.setString(1, telefone);
				ps.setString(2, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}