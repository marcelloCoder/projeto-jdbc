package br.com.mcoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.mcoder.dao.ClienteDAO;
import br.com.mcoder.dao.IClienteDAO;
import br.com.mcoder.domain.Cliente;

public class ClienteTest {
	

	
	@Test
	public void cadastrarVoid() throws Exception {
		
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Marcello");
		
		Integer quantidade = dao.cadastrar(cliente);
		assertTrue(quantidade == 1);
		
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarTeste() throws Exception{
		IClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente.setCodigo("30");
		cliente.setNome("Marcello");
		Integer qntdCad = clienteDAO.cadastrar(cliente);
		assertTrue(qntdCad == 1);	
		Cliente clienteBD = clienteDAO.buscar("30");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		Integer qntdDel = clienteDAO.excluir(clienteBD);
		assertNotNull(qntdDel);
		
	}
	
	@Test
	public void atualizarTeste() throws Exception{
		
		IClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();	
		cliente.setCodigo("30");
		cliente.setNome("Marcello");
		
		Integer qntdCad = clienteDAO.cadastrar(cliente);
		assertTrue(qntdCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar("30");
		assertNotNull(clienteBD);
		clienteBD.setCodigo("90");
		clienteBD.setNome("Outro nome");
		

		clienteBD.setCodigo("20");
		clienteBD.setNome("Outro nome");
		Integer qntdAtt = clienteDAO.atualizar(clienteBD);
		assertTrue(qntdAtt == 1);
		
		Cliente clienteBD1 = clienteDAO.buscar("30");
		assertNull(clienteBD1);
		
		assertEquals(clienteBD.getId(), clienteBD1.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD1.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD1.getNome());
		
		Cliente clienteBD2 = clienteDAO.buscar("90");
		assertNotNull(clienteBD2);
		
		assertEquals(clienteBD.getId(), clienteBD2.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		List<Cliente> list = clienteDAO.buscarTodos();
		for(Cliente c : list) {
			clienteDAO.excluir(c);
		}
		
		
	}

}
