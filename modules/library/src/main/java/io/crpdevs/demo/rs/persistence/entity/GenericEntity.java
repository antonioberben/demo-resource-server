package io.crpdevs.demo.rs.persistence.entity;


import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class to hold common attributes.
 */
@Getter
@Setter
public abstract class GenericEntity {

    @Id
    private String id;

}
