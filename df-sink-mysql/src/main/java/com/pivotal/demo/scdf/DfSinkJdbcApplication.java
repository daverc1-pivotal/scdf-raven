package com.pivotal.demo.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@EnableBinding(Sink.class)
@SpringBootApplication
public class DfSinkJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfSinkJdbcApplication.class, args);
    }
}

@MessageEndpoint
class MyMessageReceiver {
    @Autowired
    private MyRepository myRepository;
    public static Logger LOG = LoggerFactory.getLogger(MyMessageReceiver.class);
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleMessage(@Payload String message, @Headers Map<String, Object> headers) {
			LOG.info(message);
      this.myRepository.save(new MessageStore(message));
    }
}

@RepositoryRestResource
interface MyRepository extends JpaRepository<MessageStore, Long> {
}

@Entity
class MessageStore {

    @Id
    @GeneratedValue
    private Long id;
    private String message;

    MessageStore() {}

    public MessageStore(String message) { this.message = message; }
    public Long getId() { return id; }
    public String getMessage() { return message; }
    @Override
    public String toString() {
        return "MessageStore{" +"id=" + id +", message='" + message + '\'' +'}';
    }
}
