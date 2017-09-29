package io.crpdevs.demo.rs.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "oidc-show-case")
@Data
public class OIDCShowcaseProperties {

    @NotNull
    @NotEmpty
    private String remoteAppUrl;

}
