package br.com.caelum.estoque.exception;

import java.util.Date;
import br.com.caelum.estoque.exception.InfoFault;
import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault", messageName="AutorizacaoFault")
public class AutorizacaoException extends Exception {
	//esse numero eh relacionado com a serializacao do java.io mas nao importa nesse contexto
	private static final long serialVersionUID = 1L;
	
	public AutorizacaoException(String msg) {
		  super(msg);
	}
	
	public InfoFault getFaultInfo() {
		return new InfoFault("Token invalido" , new Date());
	}
}
