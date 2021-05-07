package br.com.sankhya.amqp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sankhya.dto.Message;
import br.com.sankhya.service.AmqpSankhyaService;

/**
 * 
 * @author nelson.oliveira
 *
 *Criado um endpoint rest só para facilitar 
 *os testes e o envio dessas mensagens e 
 *não tenha que ficar parando e startando o projeto
 */

@RestController
public class AmqpSankhyaApi {

	@Autowired
	private AmqpSankhyaService service;
	
	//irá enviar um objeto do tipo Message
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/envio")
	public void sendToConsumer(@RequestBody Message message) {
		
		service.sendToConsumer(message);
	}
}
