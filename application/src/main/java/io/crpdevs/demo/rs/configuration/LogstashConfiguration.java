package io.crpdevs.demo.rs.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "logstash")
@Data
public class LogstashConfiguration {

    private String host;
    private int port;

}
