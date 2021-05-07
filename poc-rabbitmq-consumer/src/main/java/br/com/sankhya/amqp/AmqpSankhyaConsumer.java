package br.com.sankhya.amqp;

/**
 * 
 * @author negao
 *
 * @param <T>
 * Interface genérica para consumir as mensagens
 */
public interface AmqpSankhyaConsumer<T> {

	public void consumer(T t);
}
