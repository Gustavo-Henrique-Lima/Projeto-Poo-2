package persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import templates.ClientePF;
import templates.ClientePJ;
import templates.ContaCorrente;
import templates.ContaPoupanca;
import templates.ICliente;
import templates.IConta;

public class ContaDAO implements IContaDAO
{
	IConexao conexao;
	Connection conn;
	PreparedStatement ps;
	public ContaDAO()
	{
		conexao=new ConexaoMySql();
	}
	
	@Override
	public void cadastrarConta(ICliente cliente, IConta conta) 
	{
		if(cliente instanceof ClientePF && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO contacorrentepf VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, conta.pegaNumero());
				ps.setString(2, conta.pegaAgencia());
				ps.setBigDecimal(3, conta.pegaSaldo());
				ps.setByte(4, conta.pegaStatus());
				ps.setDate(5, conta.pegaData());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePF && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO contapoupancapf VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, conta.pegaNumero());
				ps.setString(2, conta.pegaAgencia());
				ps.setBigDecimal(3, conta.pegaSaldo());
				ps.setByte(4, conta.pegaStatus());
				ps.setDate(5, conta.pegaData());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePJ && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO contacorrentepj VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, conta.pegaNumero());
				ps.setString(2, conta.pegaAgencia());
				ps.setBigDecimal(3, conta.pegaSaldo());
				ps.setByte(4, conta.pegaStatus());
				ps.setDate(5, conta.pegaData());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePJ && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO contapoupancapj VALUES (?, ?, ?, ?,?,?);");
				ps.setString(1, conta.pegaNumero());
				ps.setString(2, conta.pegaAgencia());
				ps.setBigDecimal(3, conta.pegaSaldo());
				ps.setByte(4, conta.pegaStatus());
				ps.setDate(5, conta.pegaData());
				ps.setString(6, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public IConta localizarContaCc(ICliente cliente) 
	{
		conn = conexao.getConexao();
		IConta conta=null;
		if(cliente instanceof ClientePF)
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM contacorrentepf WHERE cpf_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        while(rs.next()) 
	            {
		        	String numeroconta = rs.getString(1);
		            BigDecimal saldo = rs.getBigDecimal(3);
		            Byte status = rs.getByte(4);
		            conta= new ContaCorrente(numeroconta,saldo,status);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return conta;
		}
		else
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM contacorrentepj WHERE cnpj_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        if(rs.next()) 
	            {
		            String numeroconta = rs.getString(1);
		            BigDecimal saldo = rs.getBigDecimal(3);
		            Byte status = rs.getByte(4);
		            conta= new ContaCorrente(numeroconta,saldo,status);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return conta;
		}
	}

	@Override
	public IConta localizarContaCp(ICliente cliente) 
	{
		conn = conexao.getConexao();
		IConta conta=null;
		if(cliente instanceof ClientePF)
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM contapoupancapf WHERE cpf_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        if(rs.next()) 
	            {
		            String numeroconta = rs.getString(1);
		            BigDecimal saldo = rs.getBigDecimal(3);
		            Byte status = rs.getByte(4);
		            conta= new ContaPoupanca(numeroconta,saldo,status);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return conta;
		}
		else
		{
			try {
				ps = conn.prepareStatement("SELECT * FROM contapoupancapj WHERE cnpj_cliente = ?;");
				ps.setString(1, cliente.pegalogin());
		        ps.execute();
		        ResultSet rs = ps.getResultSet();
		        if(rs.next()) 
	            {
		            String numeroconta = rs.getString(1);
		            BigDecimal saldo = rs.getBigDecimal(3);
		            Byte status = rs.getByte(4);
		            conta= new ContaPoupanca(numeroconta,saldo,status);
	            }
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return conta;
		}
	}

	@Override
	public void sacar(ICliente cliente,IConta conta, BigDecimal valor) 
	{
		if(cliente instanceof ClientePJ && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contapoupancapj set saldo = ? where numeroconta = ?;");
				ps.setBigDecimal(1, valor);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePJ && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contacorrentepj set saldo = ? where numeroconta = ?;");
				ps.setBigDecimal(1, valor);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePF && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contapoupancapf set saldo = ? where numeroconta = ?;");
				ps.setBigDecimal(1, valor);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePF && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contacorrentepf set saldo = ? where numeroconta = ?;");
				ps.setBigDecimal(1, valor);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void desativarConta(IConta conta, ICliente cliente) 
	{
		if(cliente instanceof ClientePJ && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contapoupancapj set status = ? where numeroconta = ?;");
				ps.setByte(1, (byte) 0);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePJ && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contacorrentepj set status = ? where numeroconta = ?;");
				ps.setByte(1, (byte) 0);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePF && conta instanceof ContaPoupanca)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contapoupancapf set status = ? where numeroconta = ?;");
				ps.setByte(1, (byte) 0);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePF && conta instanceof ContaCorrente)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("UPDATE contacorrentepf set status = ? where numeroconta = ?;");
				ps.setByte(1, (byte) 0);
				ps.setString(2, conta.pegaNumero());
		        ps.execute();
	            ps.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}	
	}
	public void salvarMovimentacao(ICliente cliente,BigDecimal valor,Date data,String tipo)
	{
		if(cliente instanceof ClientePF)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO transacaoclientepf VALUES (?,?,?,?,?);");
				ps.setInt(1, 0);
				ps.setBigDecimal(2, valor);
				ps.setDate(3, data);
				ps.setString(4, tipo);
				ps.setString(5, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(cliente instanceof ClientePJ)
		{
			conn = conexao.getConexao();
			try {
				ps = conn.prepareStatement("INSERT INTO transacaoclientepj VALUES (?,?,?,?,?);");
				ps.setInt(1, 0);
				ps.setBigDecimal(2, valor);
				ps.setDate(3, data);
				ps.setString(4, tipo);
				ps.setString(5, cliente.pegalogin());
				ps.execute();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}