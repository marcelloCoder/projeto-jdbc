package br.com.mcoder.dao;

import java.util.List;

import br.com.mcoder.domain.Cliente;

public interface IClienteDAO {
	
	public Integer cadastrar(Cliente cliente) throws Exception;

	public Cliente consultar(String codigo) throws Exception;

	public Integer excluir(Cliente cliente) throws Exception;

	public Cliente buscar(String string) throws Exception;

	public Integer atualizar(Cliente clienteBD) throws Exception;

	public List<Cliente> buscarTodos() throws Exception;

}
