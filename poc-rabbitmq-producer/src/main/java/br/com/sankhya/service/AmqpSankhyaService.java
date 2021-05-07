package br.com.sankhya.service;

import br.com.sankhya.dto.Message;

public interface AmqpSankhyaService {

	void sendToConsumer(Message message);
}
