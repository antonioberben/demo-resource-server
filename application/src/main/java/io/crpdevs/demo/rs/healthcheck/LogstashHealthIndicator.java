package io.crpdevs.demo.rs.healthcheck;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.servlet.LogbackServletContextListener;
import ch.qos.logback.core.status.StatusManager;
import io.crpdevs.demo.rs.configuration.LogstashConfiguration;
import io.crpdevs.demo.rs.representation.root.RootResourceOutput;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static java.net.Proxy.Type.HTTP;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Component
public class LogstashHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    LogstashConfiguration logstashConfiguration;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up();

        /*UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(logstashConfiguration.getHost())
                .port(logstashConfiguration.getPort())
                .build();
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(uriComponents.toUriString(), String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            builder.up();
        } else {
            builder.down();
        }*/
    }
}


