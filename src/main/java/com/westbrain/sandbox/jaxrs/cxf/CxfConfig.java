package com.westbrain.sandbox.jaxrs.cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.spring.SpringComponentScanServer;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class which configures all of the beans and services required for Apache CXF.
 *
 * <p>Creates the CXFServlet, the CXF Bus, and creates a Jackson provider for JSON serialization. Also imports
 * the {@link org.apache.cxf.jaxrs.spring.SpringComponentScanServer} which allows for scanning the application context
 * for resources (@Path) and providers (@Provider).</p>
 *
 * @author Eric Westfall (ewestfal@gmail.com)
 */
@Configuration
@Import(SpringComponentScanServer.class)
public class CxfConfig {

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/api/v1/*");
    }

    @Bean
    public Bus cxf() {
        return new SpringBus();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

}
