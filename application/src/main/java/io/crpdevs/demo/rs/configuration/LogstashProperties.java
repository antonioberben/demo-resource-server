package io.crpdevs.demo.rs.configuration;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "logstash")
@Data
public class LogstashProperties {

    @NotNull
    @NotEmpty
    private String host;

    @NotNull
    @Min(0)
    private int port;

}
