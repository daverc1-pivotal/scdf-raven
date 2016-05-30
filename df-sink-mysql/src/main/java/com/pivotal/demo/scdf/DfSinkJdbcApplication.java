package com.pivotal.demo.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
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


import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@EnableBinding(Sink.class)
@SpringBootApplication
public class DfSinkJdbcApplication {

    public static Logger LOG = LoggerFactory.getLogger(DfSinkJdbcApplication.class);

    @Autowired
    private MyRepository myRepository;

    public static void main(String[] args) {
        SpringApplication.run(DfSinkJdbcApplication.class, args);
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleMessage(@Payload String message, @Headers Map<String, Object> headers) {
			LOG.info(message);
      myRepository.save(new MessageStore(message));
    }

    @Bean
    CommandLineRunner runner(MyRepository myr) {
        return args -> {
            myr.save(new MessageStore("This is a Test"));
        };
    }
}
