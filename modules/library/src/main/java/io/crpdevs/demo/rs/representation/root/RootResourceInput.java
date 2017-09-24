package io.crpdevs.demo.rs.representation.root;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RootResourceInput {

    @JsonProperty
    @NotEmpty
    @NotNull
    private String name;
}
