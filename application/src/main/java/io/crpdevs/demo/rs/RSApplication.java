package io.crpdevs.demo.rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;


@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class RSApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSApplication.class, args);
    }
}
