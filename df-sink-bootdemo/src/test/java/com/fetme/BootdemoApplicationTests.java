package com.fetme;

import com.fetme.domain.Greeting;
import com.fetme.repository.GreetingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ActiveProfiles;
import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootdemoApplication.class)
@ActiveProfiles("test")
public class BootdemoApplicationTests {


    protected static final Long ACCOUNT_1 = 1l;
    protected static final String ACCOUNT_1_NAME = "Hello";

    @Autowired
    BootdemoApplication application;

    @Autowired
    GreetingRepository greetingRepository;

//    @Test
//    public void validGreetingId() {
//        Logger.getGlobal().info("Start validGreetingId test");
//        Greeting greeting = application.findById(ACCOUNT_1);

//        Assert.assertNotNull(greeting);
//        Assert.assertEquals(ACCOUNT_1,greeting.getId());
//        Logger.getGlobal().info("End validGreetingId test");
//    }

    @Test
    public void validGreetingText() {
        Logger.getGlobal().info("Start validGreetingText test");
        Greeting greeting = new Greeting("Hello");
        greetingRepository.save(greeting);
        greeting = application.findByTitle("Hello");

        Assert.assertNotNull(greeting);
        Assert.assertEquals(ACCOUNT_1_NAME, greeting.getTitle());
        Logger.getGlobal().info("End validGreetingText test");
    }

//    @Test
//    public void validGreetingPartialText() {
//        Logger.getGlobal().info("Start validGreetingPartialText test");
//        List<Greeting> greetings = application.findByPartialTitle("He");
//
//        Assert.assertNotNull(greetings);
//        Assert.assertTrue(greetings.size()>0);
//        Assert.assertEquals(ACCOUNT_1_NAME, greetings.iterator().next().getText());
//        Logger.getGlobal().info("End validGreetingText test");
//    }

}
