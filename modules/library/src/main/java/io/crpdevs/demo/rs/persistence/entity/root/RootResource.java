package io.crpdevs.demo.rs.persistence.entity.root;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import io.crpdevs.demo.rs.persistence.entity.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "root-resource")
@NoArgsConstructor
@Getter
@Setter
public class RootResource extends GenericEntity {

    private static final int MAX_STRING_SIZE = 255;

    @NotNull
    @NotEmpty
    @Size(max = MAX_STRING_SIZE)
    private String name;
}
