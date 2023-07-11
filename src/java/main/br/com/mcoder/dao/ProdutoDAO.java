package br.com.mcoder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.mcoder.dao.jdbc.ConnectionFactory;

import br.com.mcoder.domain.Produto;

public class ProdutoDAO implements IProdutoDAO{

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO tb_produto (ID, CODIGO, NOME) VALUES (nextval('SQ_produto'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
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
	public Produto consultar(String codigo) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM tb_produto WHERE codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			
			rs = stm.executeQuery();
			
			if(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				
				
			}
			return produto;
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
	public Integer excluir(Produto produto) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM tb_produto WHERE codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
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
	public Produto buscar(String string) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		
		try {
			
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_produto WHERE CODIGO = ?";
			stm.setString(1, produto.getCodigo());
			rs = stm.executeQuery();
			
			 if (rs.next()) {
			    	produto = new Produto();
			    	Long id = rs.getLong("ID");
			    	String nome = rs.getString("NOME");
			    	String cd = rs.getString("CODIGO");
			    	produto.setId(id);
			    	produto.setNome(nome);
			    	produto.setCodigo(cd);
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
		return produto;
	}

	@Override
	public Integer atualizar(Produto produtoBD) throws Exception{
		// TODO Auto-generated method stub
		Connection connection = null;
    	PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_produto SET NOME = ?, CODIGO = ? WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produtoBD.getNome());
			stm.setString(2, produtoBD.getCodigo());
			stm.setLong(3, produtoBD.getId());
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
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
    	PreparedStatement stm = null;
    	ResultSet rs = null;
    	List<Produto> list = new ArrayList<>();
    	Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_produto";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
		    while (rs.next()) {
		    	produto = new Produto();
		    	Long id = rs.getLong("ID");
		    	String nome = rs.getString("NOME");
		    	String cd = rs.getString("CODIGO");
		    	produto.setId(id);
		    	produto.setNome(nome);
		    	produto.setCodigo(cd);
		    	list.add(produto);
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
