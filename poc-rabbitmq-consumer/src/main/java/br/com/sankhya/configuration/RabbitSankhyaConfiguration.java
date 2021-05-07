package br.com.sankhya.configuration;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author negao
 *
 *Classe criada para conversão das mensagens enviadas pelo producer
 */

@Configuration
public class RabbitSankhyaConfiguration {

	@Bean
	public MessageConverter jsonConverter() {
		
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory factory(
			ConnectionFactory connection, 
		SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		
		SimpleRabbitListenerContainerFactory  factory = new SimpleRabbitListenerContainerFactory();
		
		configurer.configure(factory, connection);
		factory.setMessageConverter(jsonConverter());
		
		return factory;
	}
}
