package com.fetme;

import com.fetme.domain.Greeting;
import com.fetme.domain.Names;
import com.fetme.repository.GreetingRepository;
import com.fetme.repository.NamesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Valid;
import java.util.logging.Logger;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@EnableBinding(Sink.class)
public class BootdemoApplication {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BootdemoApplication.class);

    @Autowired
    GreetingRepository greetingRepository;

    @RequestMapping("/")
    public String sayHello(){
        Logger.getGlobal().info("Handling request...");
        return "Hello World, I'm a simple Spring Boot App\n";
    }

    @RequestMapping("/greetings")
    public Iterable<Greeting> greetings() {
        return greetingRepository.findAll();
    }


    @RequestMapping(value="/add", method=RequestMethod.GET)
    public Iterable<Greeting> add(@RequestParam(value="title", required=true) String title) {
        Greeting greeting = new Greeting(title);
        greeting = greetingRepository.save(greeting);
        logger.info("Added greeting " + greeting.getId());
        return greetings();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Greeting update(@RequestBody @Valid Greeting greeting) {
        logger.info("Updating greeting " + greeting.getId());
        return greetingRepository.save(greeting);
    }


//    @RequestMapping(value = "/findById", method = RequestMethod.GET)
//    public Greeting findById(Long id) {
//        logger.info("Getting greeting " + id);
//        return greetingRepository.findOne(id);
//    }

    @ResponseBody
    @RequestMapping(value = "/findByTitle", method = RequestMethod.GET)
    public Greeting findByTitle(String title) {
        logger.info("Getting greeting " + title);
        return greetingRepository.findByTitle(title);
    }

//    @ResponseBody
//    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
//    public void deleteById(Long id) {
//        logger.info("Deleting greeting " + id);
//        Greeting gtodel = greetingRepository.findOne(id);
//        greetingRepository.delete(gtodel);
//    }

    /**
     *
     * Loads the database on startup
     *
     * @param gr
     * @return
     */
    @Bean
    CommandLineRunner loadDatabase(GreetingRepository gr) {
        return args -> {
            logger.debug("loading database..");
            gr.save(new Greeting("Hello"));
            gr.save(new Greeting("Hola"));
            gr.save(new Greeting("Bonjour"));
            logger.debug("record count: {}", gr.count());
            gr.findAll().forEach(x -> logger.debug(x.toString()));
        };

    }

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void process(@Payload String message, @Headers Map<String, Object> headers){
        Greeting greeting = new Greeting(message);
        greeting = greetingRepository.save(greeting);
        logger.info("Added greeting " + greeting.getId());
    }

    // //For SCDF
    // @Bean
    // CommandLineRunner loadscdfDatabase(NamesRepository nr) {
    //     return args -> {
    //         logger.debug("loading database..");
    //         nr.save(new Names("1","Carl"));
    //         logger.debug("record count: {}", nr.count());
    //         nr.findAll().forEach(x -> logger.debug(x.toString()));
    //     };
    //
    // }

    public static void main(String[] args) {
        SpringApplication.run(BootdemoApplication.class, args);
    }
}
