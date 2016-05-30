package com.pivotal.demo.scdf;

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

    public static void main(String[] args) {
        SpringApplication.run(DfSinkJdbcApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(MyRepository myr) {
        return args -> {
            myr.save(new MessageStore("This is a Test"));
        };
    }
}
