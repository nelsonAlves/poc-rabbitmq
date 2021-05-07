package br.com.sankhya.service.impl;

import org.springframework.stereotype.Service;

import br.com.sankhya.dto.Message;
import br.com.sankhya.service.ConsumerSankhyaService;

@Service
public class ConsumerSankhyaServiceImpl implements ConsumerSankhyaService {

	@Override
	public void action(Message message) {

		System.out.println(message.getText());
	}

}
