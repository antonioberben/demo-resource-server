package io.crpdevs.demo.rs.persistence.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Abstract class to hold common attributes.
 */
@Getter
@Setter
public abstract class GenericEntity {

    @Id
    private String id;

}
