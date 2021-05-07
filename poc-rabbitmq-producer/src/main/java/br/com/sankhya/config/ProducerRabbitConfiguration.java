package br.com.sankhya.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

/**
 * 
 * @author nelson.oliveira
 *
 *Implementação do que foi configurado no application.yml
 *Com essa imlemantação também ao iniciar a aplicação as queues serão criadas no rabbitmq
 */
@Configuration
public class ProducerRabbitConfiguration {
	
	
	// aqui serão instanciados a exchange, o routing-key e a deadletter
	
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.exchenge.producer}")
    private String exchange;

    @Value("${spring.rabbitmq.request.dead-letter.producer}")
    private String deadLetter;

	@Bean
	DirectExchange exchange() {
		
		return new DirectExchange(exchange);
	}
	
	@Bean
	Queue deadLetter() {
		 
		return new Queue(deadLetter);
	}
	
	@Bean
	Queue queue() {
		
		/**
		 * Deve-se criar um hash map para associar a deadletter criada 
		 * para sinalizar que se hover erro na queue seja acionada a deadletter
		 */
		
		Map<String, Object> args = new HashMap<>();
		
		args.put("x-dead-letter-exchange", exchange);
		args.put("x-dead-letter-routing-key", deadLetter);
		
		return new Queue(queue, true, false, false, args);
	}
	
	@Bean
	public Binding bindingQueue() {
		
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(queue);
	}
	
	@Bean
	public Binding bindingDeadletter() {
		
		return BindingBuilder.bind(deadLetter())
				.to(exchange())
				.with(deadLetter);
	}
	
}
