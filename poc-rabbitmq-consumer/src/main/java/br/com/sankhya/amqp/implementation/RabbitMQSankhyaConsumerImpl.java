package br.com.sankhya.amqp.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sankhya.amqp.AmqpSankhyaConsumer;
import br.com.sankhya.dto.Message;
import br.com.sankhya.service.ConsumerSankhyaService;

@Component
public class RabbitMQSankhyaConsumerImpl implements AmqpSankhyaConsumer<Message> {

	@Autowired
	private ConsumerSankhyaService service;
	
	@Override
	@RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}") //Aqui é passada a fila que o meu listener vai escutar.
	public void consumer(Message message) {
		
		try {
			
			service.action(message);
		} catch (Exception e) {

			/*
			 * essa exception em especial sabe que se houver um erro 
			 * deve ser lançada na deadletter
			 */
			throw new AmqpRejectAndDontRequeueException(e);
		}

	}

}
