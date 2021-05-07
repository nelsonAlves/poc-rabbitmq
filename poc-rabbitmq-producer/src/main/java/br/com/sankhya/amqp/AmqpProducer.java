package br.com.sankhya.amqp;

/**
 * 
 * @author negao
 *
 *Inteface responsável por produzir mensagens em qualquer broker Amqp
 *desta forma todo mundo que utilizar amqp tera que utiliza-la indiferente do broker
 */
public interface AmqpProducer<T> {

	void producer(T t);
}
