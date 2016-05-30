package com.pivotal.demo.scdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import com.pivotal.demo.scdf.MyRepository;
import com.pivotal.demo.scdf.MessageStore;

import java.util.Arrays;
import java.util.Collection;


@SpringBootApplication
public class DfSinkJdbcApplication {

    @Autowired
    private MyRepository myRepository;

    public static Logger logger = LoggerFactory.getLogger(DfSinkJdbcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DfSinkJdbcApplication.class, args);
    }

    /**
	   *
	   * Loads the database on startup
	   *
	   * @param gr
	   * @return
	  */
    @Bean
    CommandLineRunner loadDatabase(MyRepository myr) {
		    return args -> {
			    logger.debug("loading database..");
			      myr.save(new MessageStore("This is a Test"));
			      myr.save(new MessageStore("This is Another Test"));
			      myr.save(new MessageStore("This is Yet Another Test"));
			      logger.debug("record count: {}", myr.count());
            //omg... Brixton needs 1.7 backward compatible code. Why!
			      //myr.findAll().forEach(x -> logger.debug(x.toString()));
            logger.debug(Arrays.toString((myr.findAll()).toArray()));
		    };
    }
}
