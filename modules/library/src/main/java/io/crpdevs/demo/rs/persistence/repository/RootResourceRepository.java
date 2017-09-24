package io.crpdevs.demo.rs.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.crpdevs.demo.rs.persistence.entity.root.RootResource;

public interface RootResourceRepository extends MongoRepository<RootResource, String> {}
