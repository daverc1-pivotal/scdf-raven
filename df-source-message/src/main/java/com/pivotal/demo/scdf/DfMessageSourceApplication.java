package com.pivotal.demo.scdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@SpringBootApplication
@EnableBinding(Source.class)
public class DfMessageSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfMessageSourceApplication.class, args);
	}

	@RequestMapping("/event")
	public String event(String msg) {
		return "event[" + msg + "] placed on streaming bus";
	}

	@InboundChannelAdapter(Source.OUTPUT)
  public String greet() {
    return "hello world ";
  }

}
