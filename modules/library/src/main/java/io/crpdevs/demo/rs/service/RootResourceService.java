package io.crpdevs.demo.rs.service;

import io.crpdevs.demo.rs.persistence.entity.root.RootResource;
import io.crpdevs.demo.rs.persistence.repository.RootResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class RootResourceService {

    @Autowired
    private RootResourceRepository repository;

    public List<RootResource> findAll(){
        return repository.findAll();
    }

    public Optional<RootResource> findOne(String id){
        return Optional.ofNullable(repository.findOne(id));
    }

    public Optional<RootResource> create(RootResource rootResource){
        return Optional.ofNullable(repository.save(rootResource));
    }

    public Optional<RootResource> update(RootResource rootResource){
        return Optional.ofNullable(repository.save(rootResource));
    }
}
