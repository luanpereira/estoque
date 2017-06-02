package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.exception.AutorizacaoException;
import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	private ItemDao dao = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "item")
	@ResponseWrapper(localName = "itens")
	@RequestWrapper(localName = "listaItens")
	public List<Item> getItens(@WebParam(name = "filtros") Filtros filtros) {

		System.out.println("Chamando getItens()");
		List<Filtro> lista = filtros.getLista();
		return dao.todosItens(lista);
	}

	@WebMethod(operationName = "CadastrarItem")
	public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario token,
			@WebParam(name = "item") Item item) throws AutorizacaoException {
		System.out.println("Cadastrando " + item + ", " + token);

		boolean valido = new TokenDao().ehValido(token);

		if (!valido) {
			throw new AutorizacaoException("Token invalido");
		}

		//novo
        new ItemValidador(item).validate();
        
		this.dao.cadastrar(item);
		return item;
	}
	
	@WebMethod(operationName = "EnviaDados")
	@Oneway
	public void enviaDados(){
		System.out.println("Dados enviados!");
	}
}
