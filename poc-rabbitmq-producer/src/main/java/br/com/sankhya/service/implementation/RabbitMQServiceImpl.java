package br.com.sankhya.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sankhya.amqp.AmqpProducer;
import br.com.sankhya.dto.Message;
import br.com.sankhya.service.AmqpSankhyaService;

/**
 * 
 * @author nelson.oliveira
 *
 *Service criado para que trabalhe como um serviço e fique ainda mais desacoplado 
 *para não trabalhar diretamente com o nosso amqp qual seja a interface 
 *com o cliente que formos utilizar
 *
 */

@Service
public class RabbitMQServiceImpl implements AmqpSankhyaService {

	@Autowired
	private AmqpProducer<Message> amqp;
	
	@Override
	public void sendToConsumer(Message message) {

		//produzindo uma mensagem para um consumidor
		
		amqp.producer(message);
	}

}
