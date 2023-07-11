package br.com.mcoder.dao;

import java.util.List;

import br.com.mcoder.domain.Produto;

public interface IProdutoDAO {

	Integer cadastrar(Produto produto) throws Exception;

	Produto consultar(String codigo) throws Exception;

	Integer excluir(Produto produtoBD) throws Exception;

	Produto buscar(String string) throws Exception;

	Integer atualizar(Produto produtoBD) throws Exception;

	List<Produto> buscarTodos() throws Exception;

}
