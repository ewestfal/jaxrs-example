package com.westbrain.sandbox.jaxrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class which starts the application.
 *
 * <p>Uses Spring Boot to get the job done.</p>
 *
 * @author Eric Westfall (ewestfal@gmail.com)
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
