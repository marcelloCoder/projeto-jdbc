package br.com.mcoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.mcoder.dao.ProdutoDAO;
import br.com.mcoder.dao.IProdutoDAO;
import br.com.mcoder.domain.Produto;

public class ProdutoTest {
	
	@Test
	public void cadastrarProdutoTeste() throws Exception {
		
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("Objeto");
		
		Integer quantidade = dao.cadastrar(produto);
		assertTrue(quantidade == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarProdutoTeste() throws Exception{
		IProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto.setCodigo("50");
		produto.setNome("Alpha");
		Integer qntdCad = produtoDAO.cadastrar(produto);
		assertTrue(qntdCad == 1);	
		Produto produtoBD = produtoDAO.buscar("50");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		Integer qntdDel = produtoDAO.excluir(produtoBD);
		assertNotNull(qntdDel);
		
	}
	
	@Test
	public void atualizarProdutoTeste() throws Exception{
		
		IProdutoDAO produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();	
		produto.setCodigo("30");
		produto.setNome("Beta");
		
		Integer qntdCad = produtoDAO.cadastrar(produto);
		assertTrue(qntdCad == 1);
		
		Produto produtoBD = produtoDAO.buscar("30");
		assertNotNull(produtoBD);
		produtoBD.setCodigo("90");
		produtoBD.setNome("Outro nome");
		

		produtoBD.setCodigo("20");
		produtoBD.setNome("Outro nome");
		Integer qntdAtt = produtoDAO.atualizar(produtoBD);
		assertTrue(qntdAtt == 1);
		
		Produto produtoBD1 = produtoDAO.buscar("30");
		assertNull(produtoBD1);
		
		assertEquals(produtoBD.getId(), produtoBD1.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD1.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD1.getNome());
		
		Produto produtoBD2 = produtoDAO.buscar("90");
		assertNotNull(produtoBD2);
		
		assertEquals(produtoBD.getId(), produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		
		List<Produto> list = produtoDAO.buscarTodos();
		for(Produto p : list) {
			produtoDAO.excluir(p);
		}
		
		
	}

}
