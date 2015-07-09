package com.service;

import javax.jms.Destination;

public interface ProducerService {

	public void sendMessage(Destination destination, String string);

}
