package br.com.sankhya.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.sankhya.amqp.AmqpProducer;
import br.com.sankhya.dto.Message;

/**
 * 
 * @author negao
 *
 *Componente que implementará o amqp indiferente do broker
 *
 */

@Component
public class ProducerRabbitImpl implements AmqpProducer<Message>{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.exchenge.producer}")
    private String exchange;
	
	
	/*
	 * 	Aqui precisamos informar ao RabbitTemplate quando 
	 *  formos enviar a mensagem qual é a queue e a exchange
	 */
	@Override
	public void producer(Message message) {
		
		try {
			
			rabbitTemplate.convertAndSend(exchange, queue, message);
			
		} catch (Exception e) {
			 
			/*
			 * essa exception em especial sabe que se houver um erro 
			 * deve ser lançada na deadletter
			 */
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
