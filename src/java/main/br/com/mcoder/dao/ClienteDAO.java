package br.com.mcoder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.mcoder.dao.jdbc.ConnectionFactory;
import br.com.mcoder.domain.Cliente;

public class ClienteDAO implements IClienteDAO{

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO tb_cliente (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Cliente consultar(String codigo) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM tb_cliente WHERE codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			
			rs = stm.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
				
				
			}
			return cliente;
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}

	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM tb_cliente WHERE codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Cliente buscar(String string) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE WHERE CODIGO = ?";
			stm.setString(1, cliente.getCodigo());
			rs = stm.executeQuery();
			
			 if (rs.next()) {
			    	cliente = new Cliente();
			    	Long id = rs.getLong("ID");
			    	String nome = rs.getString("NOME");
			    	String cd = rs.getString("CODIGO");
			    	cliente.setId(id);
			    	cliente.setNome(nome);
			    	cliente.setCodigo(cd);
			    }
			
			
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}finally {
			
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		}
		return cliente;
	}

	@Override
	public Integer atualizar(Cliente clienteBD) throws Exception{
		// TODO Auto-generated method stub
		Connection connection = null;
    	PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_CLIENTE SET NOME = ?, CODIGO = ? WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, clienteBD.getNome());
			stm.setString(2, clienteBD.getCodigo());
			stm.setLong(3, clienteBD.getId());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		}
	}

	@Override
	public List<Cliente> buscarTodos() throws Exception {
		Connection connection = null;
    	PreparedStatement stm = null;
    	ResultSet rs = null;
    	List<Cliente> list = new ArrayList<>();
    	Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
		    while (rs.next()) {
		    	cliente = new Cliente();
		    	Long id = rs.getLong("ID");
		    	String nome = rs.getString("NOME");
		    	String cd = rs.getString("CODIGO");
		    	cliente.setId(id);
		    	cliente.setNome(nome);
		    	cliente.setCodigo(cd);
		    	list.add(cliente);
		    }
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		return list;
	}

}
