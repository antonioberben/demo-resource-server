package io.crpdevs.demo.rs.healthcheck;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.stereotype.Component;

import io.crpdevs.demo.rs.configuration.LogstashProperties;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class LogstashHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    LogstashProperties logstashProperties;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        TcpNetClientConnectionFactory clientFactory =
            new TcpNetClientConnectionFactory(logstashProperties.getHost(), logstashProperties.getPort());
        try {
            clientFactory.start();
            clientFactory.getConnection();
            builder.up();
        } catch (ConnectException e){
            builder.down(e);
        } finally {
            clientFactory.stop();
        }
    }
}


