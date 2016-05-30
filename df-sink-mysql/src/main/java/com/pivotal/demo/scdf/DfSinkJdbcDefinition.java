package com.pivotal.demo.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

import com.pivotal.demo.scdf.MessageStore;
import com.pivotal.demo.scdf.MyRepository;
import java.util.Map;

@EnableBinding(Sink.class)
public class DfSinkJdbcDefinition{

  public static Logger logger = LoggerFactory.getLogger(DfSinkJdbcDefinition.class);

  @Autowired
  private MyRepository myRepository;

  @ServiceActivator(inputChannel=Sink.INPUT)
	public void handleMessage(Object payload) {
		logger.info("Received: " + payload);
    try{
      String message = payload+"";
      myRepository.save(new MessageStore(message));
    }catch(Exception e){
      logger.error("Error :: "+e);
    }
  }
}
