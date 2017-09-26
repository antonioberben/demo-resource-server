package io.crpdevs.demo.rs.healthcheck;

import io.crpdevs.demo.rs.configuration.LogstashProperties;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;


@Component
@Slf4j
public class KeycloakHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        String keycloakUrl = keycloakSpringBootProperties.getAuthServerUrl();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(keycloakUrl, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)){
            builder.up();
        }
        else {
            builder.down();
        }
    }
}


