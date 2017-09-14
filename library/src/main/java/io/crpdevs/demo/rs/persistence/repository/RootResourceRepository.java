package io.crpdevs.demo.rs.persistence.repository;

import io.crpdevs.demo.rs.persistence.entity.root.RootResource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RootResourceRepository extends MongoRepository<RootResource, String> {}
