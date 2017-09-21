package io.crpdevs.demo.rs.healthcheck;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class Logstash extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        // TODO implement some check
        boolean running = true;
        if (running) {
            builder.up();
        } else {
            builder.down();
        }
    }
}


