package io.crpdevs.demo.rs.healthcheck;

import java.net.ConnectException;
import java.util.Enumeration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.integration.ip.tcp.connection.TcpConnectionSupport;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import io.crpdevs.demo.rs.configuration.LogstashProperties;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.appender.LogstashTcpSocketAppender;


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


