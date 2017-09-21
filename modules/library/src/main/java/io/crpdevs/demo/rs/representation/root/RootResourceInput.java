package io.crpdevs.demo.rs.representation.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RootResourceInput {

    @JsonProperty
    @NotEmpty
    @NotNull
    private String name;
}
